//抽象的Person类
abstract class Person(name:String,age:Int){ 
  def walk():Unit
}


object demo{
  def main(args: Array[String]): Unit = {
     //下面的代码定义了一个匿名类，并且进行了实例化
     //直接new Person("john",18)，后面跟的是类的内容
     //我们知道，Person是一个抽象类，它是不能被实例化的
     //这里能够直接new操作是因为我们扩展了Person类，只不
     //过这个类是匿名的，只能使用一次而已
     val s=new Person("john",18){
       override def walk()={
         println("Walk like a normal Person")
       }
     }
     s.walk()   
  }
}
