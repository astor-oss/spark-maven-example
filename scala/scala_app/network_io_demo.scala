import java.net.{ URL, URLEncoder }
import scala.io.Source.fromURL

object NetworkIO {
    def main(args: Array[String]):Unit = {
	println(fromURL(new URL("http://www.sina.com")).mkString)
    }
}
