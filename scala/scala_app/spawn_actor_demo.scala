
import scala.actors._, Actor._

object ActorObjectDemo {
    import scala.concurrent.ops._
    def main(args: Array[String]):Unit = {
	def ct = "Thread: " + Thread.currentThread().getName() + ":"
	val consumer = actor {
	    var done = false
	    while (! done) {
		receive {
		    case msg =>
			println(ct + "\t receive " + msg)
			done = (msg == "DONE")
			reply(ct + "\t reply " + msg)
		}
	    }
	}

	spawn {
	    val messages:Array[String] = Array(
		"this is scala demo",
		"this is spark demo",
		"this is hadoop demo",
		"this is hivee demo",
		"this is scala demo");
	    messages.foreach((msg) => consumer !? msg)
	}

    } // main
}
