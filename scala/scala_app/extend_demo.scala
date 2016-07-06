
abstract class Person(name:String, age:Int) {
    def walk(): Unit
}

class Student(name:String, age:Int, var stuNo:String) extends Person(name, age) {
    def walk() = println("walk line a elegant swan")
}

object demo {
   def main(args: Array[String]): Unit = {
	val s = new Student("john", 20, "1024")
	s.walk()
   }
}
