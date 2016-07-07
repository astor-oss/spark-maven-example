
class Person(val name:String) {
      override def toString() = name
}

class Student(name:String) extends Person(name)
class Teacher(name:String) extends Person(name)

class Pair[T](val first:T, val second:T) {
      override def toString() = "first:" + first + "\tsecond" + second;
}

object TypeMatch {
      def main(args:Array[String]):Unit = {
	//Pair的类型参数限定为[_<:Person]，即输入的类为Person及其子类
	//类型通配符和一般的泛型定义不一样，泛型在类定义时使用，而类型能配符号在使用类时使用
	def makeFriends(p : Pair[_ <: Person]) = {
		println(p.first + " is making friend with " + p.second)
	}
	
	makeFriends(new Pair(new Student("student"), new Teacher("teacher")))
      }
}
