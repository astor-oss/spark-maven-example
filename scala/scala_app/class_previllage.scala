
class Student(var name:String, var age:Int) {
    private var sex:Int = 0
    class Course(var cName:String, var gpa:Float) {
	// visit the private member of class
	def getStudentSex(stu:Student) = stu.sex
    }
}

class Class {
    // compile error, because sex is private
    // def getStudentSex(stu: Student) = stu.sex
}

object Student {
    private var studentNo: Int = 0;
    def uniqueStudentNo() = {
	studentNo += 1
	studentNo
    }

    def apply(name:String, age:Int) = new Student(name, age)
    def main(args:Array[String]):Unit = {
	println(Student.uniqueStudentNo())
	val s = new Student("code47", 30)
	// visit private member directly
	println(s.sex)

	val s1 = Student("code48", 31)
	println(s1.name + "\t" + s1.age)
    }
}
