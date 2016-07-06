
object CurryDemo {
    def main(args: Array[String]):Unit = {
	def multiply1(var1: Double)(var2: Double) = var1 * var2
	def multiply2(var1: Double) = (var2: Double) => var1 * var2
	println("case1 (10, 20)=" + multiply1(10)(20))
	val multi1 = multiply1(10)_
	println("case2 (10, 20)=" + multi1(20))
	
	val multi2 = multiply2(10)
	println("case2 (10, 20)=" + multi2(20))
    }
}
