class Person{
 private var name:String=null
 private var age:Int=0
 //this.type返回实际类型
 def setName(name:String):this.type={
     this.name=name
     this
 }
 def setAge(age:Int):this.type={
     this.age=age
     this
 }
 override def toString()="name:"+name+" age:"+age
}

class Student extends Person{
  private var studentNo:String=null
  def setStudentNo(no:String)={
    this.studentNo=no
    this
  }
  override def toString()=super.toString()+" studetNo:"+studentNo
}

object ThisType {
    def main(args:Array[String]) = {
	println(new Student().setName("student").setAge(12).setStudentNo("11111"))
    }
}
