
object ManifestExample {
    def printFun[T](x : List[T])(implicit m : Manifest[T]) = {
	if (m <:< manifest[String]) 
		println("this is a string")
	else 
		println("this is not a string")
    }

    def main(args : Array[String]): Unit = {
	printFun(List("abc", "efg"))
	printFun(List(123, 122))
	printFun(List(123, "abc"))
    }
}
