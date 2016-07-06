
class Outter {
    private var x:Int = 100
    class Inner {
	private var y:Int = 200
	def getOuterX = x
    }
}

object OutterInnerDemo {
    def main(args:Array[String]) = {
	val outter = new Outter()
	val inner = new outter.Inner
	println(inner.getOuterX)
    }
}
