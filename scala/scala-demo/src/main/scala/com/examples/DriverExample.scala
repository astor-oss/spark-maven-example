package com.examples

//import scala.collection.JavaConverters._
//import org.apache.spark.util.Utils
import scala.collection.JavaConverters._
import org.apache.spark.util.Utils

object DriverSubmissionTest {
    def main(args: Array[String]):Unit = {
	if (args.length < 1) {
	   System.err.println("Usage: DriverSubmissionTest <seconds-to-sleep>")
	   System.exit(-1)
	}

	val numSeconds = args(0).toInt

	println("Enviroment variables contains:")
	val env = System.getenv()
	//val properties = Utils.getSystemProperties
	env.asScala.filter { case (k, _) => k.contains("SPARK") }.foreach(println)
	
	println("System properties contains spar:")
	//properties.filter { case (k, _) => k.toString.contains("spark") }.foreach(println)

	for (i <- 1 until numSeconds) {
		println(s"Alive for $i out of $numSeconds seconds")
		Thread.sleep(1000)
	}
    }
}
