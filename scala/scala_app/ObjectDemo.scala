
class Student(var name:String,var age:Int){
  private var sex:Int=0
  //直接访问伴生对象的私有成员
  def printCompanionObject()=println(Student.studentNo)
}

object Student {
  private var studentNo:Int=0;
  def uniqueStudentNo()={
    studentNo+=1
    studentNo
  }

  def apply(name:String, age:Int) = new Student(name, age)
  def main(args: Array[String]): Unit = {
    println(Student.uniqueStudentNo())

    // new class and visit obj
    val s = new Student("john",29)
    println(s.sex)
    // new object and visit
    val s1 = Student("john", 20)
    println(s1.name)
    println(s1.age)
  }
}
