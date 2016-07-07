import scala.xml._

object XmlMatchDemo {
    def main(args: Array[String]) = {
        def xmlMatching(node: Node) = {
	    node match {
	        case <persons>{subelements @ _*}</persons> =>
		     for (elem <- subelements) println((elem \ "name").text)
	        case _ => println("no matching")
	    }
        }

        xmlMatching(<persons><person><name>摇摆少年梦</name></person></persons>)
	val nodes = <persons><person><name>xxxx1</name></person><person><name>xxxx2</name></person></persons>
	xmlMatching(nodes)
    }
}
