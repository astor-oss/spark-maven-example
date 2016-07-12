package com.examples

import java.io.File
import scala.io.Source._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._

object RWLoaclFS {
    private var localFilePath: File = new File(".")
    private var dfsDirPath: String = ""
    private var NPARAMS = 2

    private def readFile(filename: String): List[String] = {
	val lineIter: Iterator[String] = fromFile(filename).getLines()	
        val lineList: List[String] = lineIter.toList
	lineList
    }

    private def printUsage(): Unit = {
	val usage: String = "DFS Read-Write Test\n"
        println(usage)
    }
    private def parseArgs(args: Array[String]): Unit = {
	if (args.length != NPARAMS) {
	   printUsage()
	   System.exit(1)
	}
	var i = 0
        localFilePath = new File(args(i))
        // check is exist or not
        if (!localFilePath.exists) {
           System.err.println("Given path(" + args(i) + "does not exist.\n")
           printUsage()
           System.exit(1)
        }

	// check is file or not
	if (!localFilePath.isFile) {
	   System.err.println("Given path(" + args(i) + "is not a file.\n")
	   printUsage()
           System.exit(1)
	}

        i += 1
        dfsDirPath = args(i)
    }

    def runLocalWordCount(fileContents: List[String]): Int = {
	fileContents.flatMap(_.split(" "))
		.flatMap(_.split("\t"))
		.filter(_.nonEmpty)
		.groupBy(w => w)
		.mapValues(_.size)
		.values
		.sum
    }

    def main(args: Array[String]): Unit = {
	parseArgs(args)
	println("Perform local world cnt")
	val fileContents = readFile(localFilePath.toString())
	val localWordCnt = runLocalWordCount(fileContents)
	println("=================>(" + fileContents.size + "," + localWordCnt + ")")

	val sparkConf = new SparkConf().setAppName("ReadWriteLocalFileSystemDemo")
	val sc = new SparkContext(sparkConf)

	val destFileName = dfsDirPath + "/dfs_read_write_test"
	val fileRDD = sc.parallelize(fileContents)
	fileRDD.saveAsTextFile(destFileName)
	
	println("Reading file from dfs and running world cnt")
	val readFileRDD = sc.textFile(destFileName)
	val dfsWordCnt = readFileRDD.flatMap(_.split(" "))
		.flatMap(_.split("\t"))
		.filter(_.nonEmpty)
		.map(w => (w, 1))
		.countByKey()
		.values
		.sum
	sc.stop()

	if (localWordCnt == dfsWordCnt) {
	   println(s"Sucess! local word cnt ($localWordCnt)" + s"(and DFS word cnt($dfsWordCnt) agree")
	} else {
	   println(s"Failure !!!")
	}
}

}
