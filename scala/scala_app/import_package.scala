
import java.util.{ HashMap => JavaHashMap }
import scala.collection.mutable.HashMap

object ImportPackageDemo {
    def main(args: Array[String]):Unit = {
	val javaHashMap = new JavaHashMap[String, String]()
	javaHashMap.put("spark", "perfect")
	javaHashMap.put("hadoop", "good")
	
	for (key <- javaHashMap.keySet().toArray) {
            println(key + ":" + javaHashMap.get(key))
	}

	val scalaHashMap = new HashMap[String, String]()
	scalaHashMap.put("spark", "perfect")
	scalaHashMap.put("hadoop", "good")
	scalaHashMap.foreach(s => println(s._1 + ":" + s._2))
    }
}
