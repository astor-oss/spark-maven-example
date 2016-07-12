package com.examples

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object BroadcastExample {
    def main(args: Array[String]) {
        val blockSize = "4096"

	val sparkConf = new SparkConf().setAppName("BroadCastExample").set("spark.broadcase.blockSize", blockSize)
        val sc = new SparkContext(sparkConf)
	val slices = 2 
	val num = 1000000

	val arr1 = (0 until num).toArray
	for (i <- 0 until 10) {
 	    println("========Iterator:" +i + "===========")
	    val startTime = System.nanoTime
	    val barr1 = sc.broadcast(arr1)
	    val observerSizes = sc.parallelize(1 to 10, slices).map(_ => barr1.value.length)
	    observerSizes.collect().foreach(println)
	    println("Iteration %d took %.0f millisecond".format(i, (System.nanoTime - startTime) / 1E6))
	}
	sc.stop()
    }
}
