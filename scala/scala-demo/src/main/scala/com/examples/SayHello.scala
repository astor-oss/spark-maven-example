package com.examples

import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.log4j.Logger


case class Info(name:String, age:Int, addr:String)
object SayHello {
  def main(arg: Array[String]) {
    var logger = Logger.getLogger(this.getClass())

    val jobName = "SayHello"
    val conf = new SparkConf().setAppName(jobName)
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    conf.registerKryoClasses(Array(classOf[Info]))

    val sc = new SparkContext(conf)
    val arr = new ArrayBuffer[Info]()
    val nameArr = Array[String]("xyz", "abc", "efg")
    val addrArr = Array[String]("beijing", "shanghai", "jinan", "tianjin")
    
    for (i <- 1 to 100000) {
	val name = nameArr(Random.nextInt(3))
        val age = Random.nextInt(100)
        val address = addrArr(Random.nextInt(4))
	arr += (Info(name, age, address))
    }

    val rdd = sc.parallelize(arr)
    rdd.persist(StorageLevel.MEMORY_ONLY_SER)
    rdd.count()
    rdd.collect().foreach(println)
  }
}
