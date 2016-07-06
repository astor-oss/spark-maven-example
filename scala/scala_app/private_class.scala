
// private，定义的类及伴生对象可以访问
class Teacher(var name:String) {
    private def printName(tName: String = ""): Unit = { println(tName) }
    def print(tName:String) = this.printName(tName)
}

object Teacher {
    // ok
    def printName = new Teacher("abc").printName()
}

object appDemo {
    def main(args: Array[String]) = {
	// compile error; for acess failed
	// new Teacher("fdfd").printName()
	// 伴生对象可以访问
	Teacher.printName
	new Teacher("fffff").print("aaaa")
    }
}
