object ClassObjectDemo {
   class Person {
	var name:String = null
   }

   class PersonP {
	private var privateName:String = null
	def name = privateName
	def name_=(name:String) = {
		privateName = name
	}
   }

   class PersonPB(val name:String, val age:Int) {
	var sex:Int  = 0
	println("PersonPB construct is doing")
	override def toString() = name + ":" + age
	def this(name:String, age:Int, sex:Int) = {
		this(name, age)
		this.sex = sex
	}
   }

   def main(args:Array[String]):Unit = {
	// case 1. person
	val person = new Person
	person.name_=("hello_world")
	println("person name is:" + person.name)
	person.name=("hello_world_1")
	println("person name is:" + person.name)

	// case 2. PersonP
	var p = new PersonP
	p.name = "new name"
	println("person new name is:"+ p.name)

	// case 3. PersonPB
	var pb = new PersonPB("xxxxxxxx", 20)
	println("person new name is:"+ pb.name + " age:"+ pb.age)
	println("person toString is called:" + pb.toString)

	var pb1 = new PersonPB("yyyy", 20, 1)
	println("====>PB1 name:[" + pb1.name +"], age:[" + pb1.age + "], sex:[" + pb1.sex +"]")
   }
}
