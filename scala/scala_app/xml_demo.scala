import scala.xml._

class Person(var name:String, var age:Int) {
    def toXML() = {
	<person>
	    <name>{name}</name>
	    <age>{age}</age>
        </person>
    }

    def fromXML(xml : Elem): Person = {
        new Person((xml \ "name").text, ((xml \ "age").text.toInt))
    }
    override def toString() = "[Name = " + name + " age=" + age + "]"
}


object XmlDemo {
   def main(args:Array[String]):Unit = {
	val p = new Person("hzxjtx", 30)
	val xmlPerson = p.toXML
	scala.xml.XML.save("./person.xml", xmlPerson, "UTF-8", true, null)

	val loadxml = scala.xml.XML.loadFile("./person.xml")
	val p2 = p.fromXML(loadxml)
	println("Person:" + p2)
   }
}
