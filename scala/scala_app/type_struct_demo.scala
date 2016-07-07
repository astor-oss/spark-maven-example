

class File {
    def close():Unit = println("class file is close")
}

object File {
    def close():Unit = println("object file is close")
}

object StructType {
    def releaseMem(res : { def close():Unit }) = res.close

    def main(args:Array[String]):Unit = {
	releaseMem( new { def close():Unit = println("close is called") })

	type X = { def close():Unit }
	def releaseMem2(x: X) = x.close
	releaseMem2(new { def close():Unit = println("close is called")})

	releaseMem2(File)
	releaseMem(new File)
    }
}
