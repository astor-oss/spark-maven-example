import scala.collection.mutable._

object CaseDemo {
    def main(args: Array[String]):Unit = {
	// case 1. match & case 
	var list = new ArrayBuffer[Int]()
	for (i <- 1 to 100) {
	    i match {
		case 10 => println("10")
		case 50 => println("50")
		case  _ if (i % 3 == 0) => list.append(i)
		case  _ => 
	    }
	}

	println("all these values below can div 3[1,100]:")
	println(list.mkString(","))

	// case 2. class match & case
	abstract class Person
	case class Student(name:String, age:Int, stuNo:Int) extends Person
	case class Teacher(name:String, age:Int, tchNo:Int) extends Person
	case class Nobody(name:String) extends Person
	case class SchoolClass(desc:String, persons:Person*)

	var p1: Person = Student("spark", 5, 1)
	var p2: Person = new Teacher("sparkT", 5, 1)
	var p3: Person = Nobody("nobody")
	var s1 = Student("student", 10, 111)
	val p4 = s1.copy()
	val p5 = s1.copy("code47", 100, 5)
	var pList = new ArrayBuffer[Person]()
	pList.append(p1)
	pList.append(p2)
	pList.append(p3)
	pList.append(p4)
	pList.append(p5)
	for (p <- pList) {
		p match {
		    case Student(name, age, stuNo) => println("Student:" + name + ":" + age + ":" + stuNo)
		    case Teacher(name, age, tchNo) => println("Teacher:" + name + ":" + age + ":" + tchNo)
		    case Nobody(name) => println("Nobody:" + name)
		    case _ =>
	        }
	}

	var school = SchoolClass("a school", Teacher("teacher", 27, 1000), Student("student", 10, 1000))
	def multiMatch(school : Any) = school match {
	    case SchoolClass(_, _, Student(name, age, stuNo)) => ("student be matched:" + name + ":" + age)
            case _ => ("invalid match case")
	}
	println("case2 multi match:" + multiMatch(school));

	// case 3: case seq
	var lList = List("spark", "hadoop", "hive")
	def listMatch(lList:List[String]) = lList match {
	    case List(_, second, _*) =>  second
            case _ => "invalid case"
	}
	println("case3 list match:" + listMatch(lList))

	// case 4: case tuple
	val tuple = ("spark", "hadoop", "hive")
	def tupleMatch(var1: Any) =  var1 match {
	    case (_, t2, t3) => t2 + " " + t3
            case _ => "invalid"
	}

	println("case4 tuple match:" + tupleMatch(tuple))

	// case 5. para match
	def paraMatch(para: Any) = para match {
	    case para:String => "String:" + para
	    case para:Int    => "Int:" + para
	    case _ => "invalid"
	}
	println("case5 para match:" + paraMatch("1000"))

	// case6. para bind match
	var tList = List(List(1,2,3), List(2,3,4))
	def varBindMatch(t:Any) = t match {
	    //变量绑定，采用变量名（这里是e)
	    //与@符号，如果后面的模式匹配成功，则将整体匹配结果作为返回
	    case List(_, e@List(_, _, _)) => e
	    case _ => Nil
	}
        println("case6 bind para match:" + varBindMatch(tList))
        println("case6 bind para match:" + varBindMatch(tList).mkString("[",",","]"))

	// case 7. for match case
	val ipRegex="(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)".r
	for (ipRegex(var1, var2, var3, var4) <- ipRegex.findAllIn("123.12.13.14")) {
		println(s"case7 for match case: $var1.$var2.$var3.$var4")
	}
	var localMap = scala.collection.immutable.Map[String, String]("bj"-> "beijing", "sh" -> "shanghai")
	for ((var1, var2) <- localMap) {
		println(s"case7 for match case: $var1 -> $var2")
		println("case7 for match case: %s -> %s".format(var1, var2))
	}
	
	// case8. option match demo
	var hashMap = scala.collection.mutable.HashMap[String, Int]("spark"-> 100, "hadoop"->80)
	def optionMatch(str:String) = hashMap.get(str) match {
		case Some(str) => str
		case None => "None"
	}

	println("case8 option match:" + optionMatch("spark"))
	println("case8 option match:" + optionMatch("spark1"))
    }
}
