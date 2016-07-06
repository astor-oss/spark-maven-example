import java.util.{ HashMap => _,_ }
import scala.collection.mutable.HashMap

object ImportPackageDemoImplicit {
    def main(args: Array[String]) = {
	//这样的话,HashMap便无歧义地指向scala.collection.mutable.HashMap
	val scalaHashMap = new HashMap[String, String]()
	scalaHashMap.put("spark", "perfect")
	scalaHashMap.put("hadoop", "good")
	scalaHashMap.foreach { e =>
		val (key, value) = e
		println(key + ":" + value)
	}
    }
}
