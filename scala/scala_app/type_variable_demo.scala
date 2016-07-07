class TypeVariableBound {
    //下面的类编译通不过
    //因为泛型T在编译的时候不能确定其具体类型
    //即并不是所有的类中都存在compareTo方法
    /*
      def compare[T](first:T,second:T) = {
         if (first.compareTo(second)>0) 
            first 
         else 
            second
      }
    */
    // 采用<:进行类型变量界定, 该语法的意思是泛型T必须是实现了Comparable 接口的类型
    def compare[T <: Comparable[T]](first:T, second:T):T = {
	if (first.compareTo(second) > 0)
	    first
        else second
    }
}

object TypeVariableBound{
  case class Person(var name:String, var age:Int) extends Comparable[Person]{
	def compareTo(dest: Person):Int = {
	    if (this.age > dest.age) 1
	    else if (this.age == dest.age) 0
	    else -1
	}
  }

  case class Student[S, T <: AnyVal](name:S, age:T)

  def main(args: Array[String]): Unit = {
      val tvb=new TypeVariableBound
      // case1: has compare func
      println(tvb.compare("A", "B")) 
      // case2: user define compareTo
      println(tvb.compare(new Person("spark", 10), new Person("hadoop", 100)))

      // case 3. type_variable constrain
      var stu1 = new Student[String, Int]("spark", 10)
      // compile error:下面这条语句是不合法的，因为String类型不属于AnyVal类层次结构  
      // var stu2 = new Student("spark", "100")

      // case 4. view bind
      case class Teacher[S, T <: Comparable[T]](name:S, height:T)
      val tch1 = Teacher("john","170")
      //下面这条语句不合法，这是因为Int类型没有实现Comparable接口
      // var tch2 = Teacher("john", 180)

      case class Teacher1[S, T <% Comparable[T]](name:S, height:T)
      var tch2 = Teacher1("john", 180)
      var tch3 = Teacher1("john", "180")
  }
}
