package com.examples
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object StreamingWordCnt {
    def main(args:Array[String]):Unit = {
	if (args.length < 1) {
	     println("Usage: StreamingWordCnt <directory>")
	     System.exit(-1)
	}

        val sparkConf = new SparkConf().setAppName("StreamingWordCnt")
	val ssc = new StreamingContext(sparkConf, Seconds(1))
	
	val lines = ssc.textFileStream(args(0))
	val words = lines.split(" ")
	val count = words.map((_,1)).reduceByKey(_+_)

	count.print()

	ssc.start()
	ssc.awaitTermination()
    }
}
