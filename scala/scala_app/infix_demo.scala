
case class Person[S,T](name:S, age:T)

object InfixDemo {
     def main(args:Array[String]):Unit = {
	// case1. infix demo
	// var p:Person[String, Int]
	val p:String Person Int = Person("spark", 19)
	p match {
	     case "spark" Person 19 => println("match is ok")
	     case name Person age => println(name + " " + age)
	     case _ => println("mismatch")
	}

	// case2. exist demo
	def print(x:Array[_])=println(x.mkString(","))
	def print1(x:Array[T] forSome {type T})= println(x.mkString(","))
	def print2(x:Map[_,_]) = x.foreach(e => println(e._1 + "\t" + e._2))

	print(Array(1,2,3,4))
	print(Array("abc", "efg"))
	print1(Array(1,2,3,4))
	print1(Array("abc", "efg"))
	print2(Map("abc"-> 1000, "efg"-> 2000))

	// case3
	//通过Funtion2定义一个输入参数为两个整型
	//返回类型为Int的函数,这里是通过new创建创建函数
	//而这个类正是Function2，它是函数类型类
	val func2 = new Function2[Int, Int, Int] {
		def apply(x:Int, y:Int):Int = if (x > y) x else y;
	}
	def max(x:Int, y:Int):Int = if (x > y) x else y
	println(max(1,10) == func2(10, 1))
	var fun1 = new Function1[Int, Int] {
		def apply(x:Int):Int = x + 1
	}
	println(fun1(10))

	// case 4. abstract type
	//象类型是指在类或特质中利用type关键字定义一个没有确定类型的标识
	
	abstract class Person1 {
		type IdentityType;
		def getIdentityType():IdentityType
	}
	
	class Student1 extends Person1 {
		type IdentityType = String
		def getIdentityType() = "123"
	}

	class Student2 extends Person1 {
		type IdentityType = Int
		def getIdentityType() = 123
	}

	println(new Student2().getIdentityType())
     }
}
