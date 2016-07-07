import java.util.List
import scala.collection.JavaConversions._

class ScalaExistTypeToJavaWildcardGeneric1 {
  //采用Scala中的存在类型与Java中的能匹符泛型进行互操作
  def printList(list: List[T] forSome {type T}):Unit={
    //因为我们引入了import scala.collection.JavaConversions._
    //所以可以直接调用foreach方法
    list.foreach(println)
  }
  //上面的函数与下面的等同
  def printList2(list: List[_]):Unit={
    list.foreach(println)
  }

}

object ScalaMain {
  def main(args:Array[String]) = {
      val s = new ScalaExistTypeToJavaWildcardGeneric1
      s.printList(JavaWildcardGeneric.getList)
      s.printList2(JavaWildcardGeneric.getList)
  }
}
