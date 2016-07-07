import java.io.File
import java.io.ObjectOutputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.FileInputStream

//@serializable
class Person{
  // @transient注解声明后，成员变量不会被充列化
  @transient var name:String="zzh"
  var age:Int=0
  override def toString()="name="+name+" age="+age
}

object Serialize {
  def main(args: Array[String]): Unit = {
     val file = new File("person.out")
     val oout = new ObjectOutputStream(new FileOutputStream(file)) 
     val person = new Person 
     oout.writeObject(person)  
     oout.close()

     val oin = new ObjectInputStream(new FileInputStream(file)) 
     val newPerson = oin.readObject()
     oin.close();  
     println(newPerson)
     //println(newPerson.name + " " + newPerson.age)
  }
}
