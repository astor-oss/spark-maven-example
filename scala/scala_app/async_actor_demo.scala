
import scala.actors._, Actor._

object ActorObjectDemo {
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

	System.out.println("Sending....")
	val replyFuture = consumer !! "this is async message"
	val replymsg = replyFuture()
	System.out.println("receive async reply message:" + replymsg)
	System.out.println("Sending....")
	consumer !! "Does eat oats"
	System.out.println("Sending....")
	consumer !! "Little lambs eat ivy"
	System.out.println("Sending....")
	consumer !! "Kids eat ivy too"
	System.out.println("Sending....")
	consumer !! "DONE"      
    } // main
}
