
class Teacher(var name:String) {
    private[this] def printName(tName: String = ""): Unit = { println(tName) }
    def print(tName:String) = this.printName(tName)
}

object Teacher {
    // compile error, because teacher cannot access private[this]
    // def printName = new Teacher("abc").printName()
}

object appDemo {
    def main(args: Array[String]) = {
	// compile error; for acess failed
	// new Teacher("fdfd").printName()
	new Teacher("fffff").print("aaaa")
    }
}
