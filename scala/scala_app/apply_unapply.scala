object Email {
     // apply方法用于无new构造对象
     def apply(user:String, domain:String) = user + "@" + domain
     // unapply方法用于在模式匹配中充当extractor
     def unapply(str:String) : Option[(String, String)] = {
	val parts = str.split("@")
	if (parts.length == 2) Some(parts(0), parts(1)) else None
     }
}


object ApplyDemo {
    def main(args:Array[String]):Unit = {
	val email = Email("hzxjtx", "126.com")
	//下面的匹配会导致调用EMail.unapply(email)
	email match {
              case Email(user, domain) => { println("user=" + user + ", domain=" + domain) }
              case _ => println("invalid")
	}
	
	def patterMatch(str: String) = str match {
	    case Email(user, name) => println("User=" + user + " Name=" + name)
	    case _ => println("invalid")
	}
	patterMatch("hzxjtx@126.com")
	patterMatch(email)

	// case 2.
	class Person(val firstName:String,val secondName:String)
	//在伴生对象中定义apply方法和unapply方法
	object Person {
	    def apply(firstName: String, secondName: String) = new Person(firstName,secondName)
	    def unapply(person: Person):Option[(String,String)] = {
		if(person != null) Some(person.firstName,person.secondName)
		else None
	    }
	}
	val p = Person("spark", "is good")
	p match {
	     case Person(firstName,secondName) => println("firstName="+firstName+" secondName="+secondName)
	     case _ => println("invalid")
	}

	// case 3.
	// 下面的代码相当于执行了下面这条语句:UpperCase.unapply(Twich.unapply(EMail.unapply(s)))
	// 其执行顺序是先调用EMail的unapply方法，然后再调用Twice中的unapply方法，
	// 最后调用UpperCase的unapply方法，如果返回true，则将Twice 中返回的字符串赋值给x
	// case EMail(Twice(x @ UpperCase()), domain) => "match: " + x + " in domain " + domain

	// case 4
	val list = List(List(1,2,3),List(2,3,4))
	list match {
	    // _*表示匹配列表中的其它元素
	    case List(List(one,two,three), _*) => println(one + two + three)
	    case _ => println("invalid")
	}

	list match {
	   // _表示匹配列表中的第一个元素
	   // _*表示匹配List中的其它多个元素
           // 这里采用的变量绑定的方式
	   case List(_, x@List(_*), _*) => println(x) 
	   case _ => println("invalid")
        }
	
    }
}
