import java.io._
import scala.io._

object ScalaFileDemo {
   def main(args: Array[String]):Unit = {
	val fileWriter = new FileWriter("local.txt")
	for (i <- 0 until 100) {
            fileWriter.write("this is a scala file demo\n")
	}
	fileWriter.flush()
	fileWriter.close()

	val fileReader = Source.fromFile("./local.txt")
	val lines = fileReader.getLines()
	var index:Int = 0
	for (i <- lines) {
	     index = index + 1
	     println(index + "\t" + i)
        }
	fileReader.close()
   }
}
