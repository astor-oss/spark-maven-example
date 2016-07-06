

abstract class Animal {
    var height:Int
    def eat:Unit
}

class Person(var height:Int) extends Animal {
    def eat() = {
	println("eat by mouth")
    }
}

object Person extends App {
	new Person(6).eat()
}
