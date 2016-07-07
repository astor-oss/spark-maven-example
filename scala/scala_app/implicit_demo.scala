
import java.io.File
import scala.io.Source

class RichFile(val file:File) {
    def read = Source.fromFile(file).getLines().mkString("\n")
}

class OutputFormat(var first:String, var second:String)
class Student(var name:String) {
      //利用柯里化函数的定义方式，将函数的参数利用
      //implicit关键字标识, 这样的话，在使用的时候可以不给出implicit对应的参数
      def formatStudent()(implicit outFormat:OutputFormat) = {
	 outFormat.first + " " + this.name + " " + outFormat.second 
      }
}

object ImplicitDemo {
    def main(args: Array[String]):Unit = {
	// case1. double -> Int
	implicit def double2Int(x: Double) = x.toInt
	var x:Int = 3.5

       // case2. File -> RichFile
       // File类的对象并不存在read方法，此时便会发生隐式转换
       // 将File类转换成RichFile
       implicit def file2RichFile(file:File) = new RichFile(file)
       val file = new File("a.scala").read
       println(file)

       // case 3. implicit param
       implicit var format = new OutputFormat("<", ">")
       println(new Student("spark").formatStudent()(format))
       println(new Student("hadoop").formatStudent())

       // case 4. implicit func
       //下面代码中的(implicit order:T=>Ordered[T])
       //给函数compare指定了一个隐式参数
       //该隐式参数是一个隐式转换
       def compare[T](first:T, second:T)(implicit order:T => Ordered[T]) = {
	     if (first > second) 
		first
	     else 
		second
       }
       println("case4 compare example:" + compare("aaaa", "abcd"))

       // case 5. implicit param
       //implicit关键字在下面的函数中只能出现一次
       //它作用于两个参数x,y，也即x,y都是隐式参数
       def sum(implicit x: Int, y: Int) = x + y
       //下面的函数不合法，函数如果没有柯里化，不能期望
       //implicit关键字会作用于其中一个参数
       //def sum(x: Int, implicit y: Int) = x + y
       //def sum(implicit x: Int, implicit y: Int) = x + y
       //只能指定一个隐式值
       //例如下面下定义的x会自动对应maxFunc中的
       //参数x,y即x=3,y=3，从而得到的结果是6
        implicit val y:Int=3
       //不能定义两个隐式值
       //implicit val y:Int=4
       println(sum)
    }
}
