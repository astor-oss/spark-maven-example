
import scala.actors.Actor._


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

	System.out.println(ct + " sending msg")
	val r1 = consumer !? "Mares eat oats"
	println(ct + " receive replyed message[" + r1 + "]")
	System.out.println(ct + " sending msg")
	val r2 = consumer !? "DONE"
	println(ct + " receive replyed message[" + r2 + "]")
    } // main
}
