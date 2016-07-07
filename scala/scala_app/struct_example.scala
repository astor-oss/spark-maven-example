//抽象Person类
abstract class Person(var name:String,var age:Int){

  def walk():Unit
  //talkTo方法，参数为Person类型
  def talkTo(p:Person):Unit
}

class Student(name:String,age:Int) extends Person(name,age){
  private var studentNo:Int=0
  def walk()=println("walk like a elegant swan")
  //重写父类的talkTo方法
  def talkTo(p:Person)={
    println("talkTo() method in Student")
    println(this.name+" is talking to "+p.name)
  }
}

class Teacher(name:String,age:Int) extends Person(name,age){
  private var teacherNo:Int=0

  def walk()=println("walk like a elegant swan")

   //重写父类的talkTo方法
  def talkTo(p:Person)={
    println("talkTo() method in Teacher")
    println(this.name+" is talking to "+p.name)
  }
}

object demo{
  def main(args: Array[String]): Unit = {

     //下面的两行代码演示了多态的使用
     //Person类的引用可以指向Person类的任何子类
     val p1:Person=new Teacher("albert",38)
     val p2:Person=new Student("john",38)

     //下面的两行代码演示了动态绑定
     //talkTo方法参数类型为Person类型
     //p1.talkTo(p2)传入的实际类型是Student
     //p2.talkTo(p1)传入的实际类型是Teacher
     //程序会根据实际类型调用对应的不同子类中的talkTo()方法
     p1.talkTo(p2)
     p2.talkTo(p1)
  }
}
