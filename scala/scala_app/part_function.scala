
object partFuncDemo {
    def main(args: Array[String]) : Unit = {
	def sum(x:Int, y:Int, z:Int) = x + y + z
	
	// case 1. supply zero para
	val s1 = sum _
	println("part1:" + s1(1,2,3))

	// case 2. supply two para
	var s2 = sum(1, _:Int, 10)
	println("part2:" + s2(2))

	// case 3. supply one para
	var s3 = sum(1, _:Int, _:Int)
	println("part3:" + s3(2,3))

	def multiply(var1: Double)(var2: Double) = var1 * var2
	var multi1 = multiply(10)_
	println("part4:" + multi1(20))
    }
}
