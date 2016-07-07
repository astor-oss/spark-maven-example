import scala.actors._, Actor._

object LoopReatorDemo {
    def main(args: Array[String]):Unit = {
	val actor = Actor.actor {
	    loop {
		react {
		    case x:Int => println("receive Int:" + x)
		    case msg:String =>println("receive String:" + msg)
		    case _ => println("invalid"); exit()
		}
	    }
	}

	actor !! "111111111"
	actor !"222222222222221"
	actor ! 123
	actor ! (123444)
    }
}
