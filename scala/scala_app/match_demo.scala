
import scala.collection.mutable

object MatchDemo {
    def main(args: Array[String]):Unit = {
	// case 1. match example
	def show(x: Option[Int]) = x match {
		case Some(x) => println("good type:" + x)
		case None => println("invalid type")
	}
	var hashMap = new mutable.HashMap[String, Int]()
	hashMap.put("Spark", 100)
	hashMap.put("Hive", 101)

        show(hashMap.get("Spark"))
	show(hashMap.get("spark")) 

	// case 2. queue demo
	var queue = mutable.Queue(1,2,3,4,5)
	queue += 6
	queue ++= List(100, 101, 102, 103)

	// case 3. stack demo
	var stack = new mutable.Stack[Int]()
	stack.push(100)
	stack.push(200)
	println("Stack top is:" + stack.top)

	// case 4. func
	println(Array(10,11,12,13).map(_+1).mkString(","))
	val increase = (x:Int) => x + 1
	println(Array(10,11,12,13).map(increase).mkString(","))
	println(Array(10,11,12,13).map(x => x+1).mkString(","))
	val increase1 = 1 + (_:Int)
	println(Array(10,11,12,13).map(increase1).mkString(","))
	val increase2:(Int) => Int = 1 + _
	println(Array(10,11,12,13).map(increase2).mkString(","))

	// case 5. multi func
	def multiplyBy(factor:Double) = (x: Double) => factor * x
	val multi = multiplyBy(100)
	println("multiply func result:" + multi(20))

	// case 6. closure
	var sum:Int = 0
	val list = List(10, 11, 12, 13, 14, 15)
	list.foreach(sum += _)
	println("closure func get sum value:" + sum)

    }
}
