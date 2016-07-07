import java.util.ArrayList;
//引入下面这条语句后便可以调用scala集合中的方法，如foreach,map等
import scala.collection.JavaConversions._

object RevokeJavaCollections{
  def getList = {
    val list = new ArrayList[String]()
    list.add("hzxjtx")
    list.add("code48")
    list
  }
  def main(args: Array[String]) {
    // case 1. implicit conversion
    val list = getList
    //现在可以调用scala集合中的foreach等方法了
    list.foreach(println)
    val list2 = list.map(x=>x*2)
    println(list2)
  }
}
