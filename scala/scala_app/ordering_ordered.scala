
case class Student(name:String) extends Ordered[Student] {
     override def compare(that:Student): Int = {
	if (this.name == that.name)
	    1
        else 
           -1
     }
}


class Pair1[T <: Ordered[T]] (first:T, second:T) {
     //比较的时候直接使用<符号进行对象间的比较
     def smaller() = {
	if (first < second) first else second
     }
}

object OrderDemo {
     def main(args:Array[String]):Unit = {
         val p = new Pair1(new Student("spark"), new Student("hadoop"))
         println(p.smaller)	
     }
}
