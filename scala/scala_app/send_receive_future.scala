import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorLogging
import scala.concurrent.Future
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.pipe
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global


object SendReceiveDemo extends App {
    case class BasicInfo(id:Int, val name:String, age:Int)
    case class InterestInfo(id:Int, val interest:String)
    case class Person(basicInfo:BasicInfo, interestInfo:InterestInfo)

    class BasicInfoActor extends Actor with ActorLogging {
        def receive = {
		case id:Int => log.info("id=" + id); sender !new BasicInfo(id, "join", 19)
		case _ => println("basicActor received unknown message")
        }
    }

    class InterestInfoActor extends Actor with ActorLogging {
       def receive = {
		case id:Int => log.info("id=" +id); sender!new InterestInfo(id, "football")
		case _ => log.info("interestActor receive unknown message")
       }
    }

    class PersonActor extends Actor with ActorLogging {
        def receive = {
		case person:Person => log.info("Person=" + person)
		case _ => log.info("person receive unknown message")
        }
    }

    class CombineActor extends Actor with ActorLogging {
	implicit val time = Timeout(5 seconds)
        val basicActor = context.actorOf(Props[BasicInfoActor], name = "BasicInfoActor")
        val interestActor = context.actorOf(Props[InterestInfoActor], name  = "InterestInfoActor")
        val personActor = context.actorOf(Props[PersonActor], name = "PersonActor")
	def receive = {
		case id:Int =>
			val combineResult: Future[Person] = 
                        for {
			     basicInfo <- ask(basicActor,id).mapTo[BasicInfo]
			     interestInfo <- ask(interestActor, id).mapTo[InterestInfo]
			} yield Person(basicInfo, interestInfo)
 			
			pipe(combineResult).to(personActor)
	}
    }

    val system = ActorSystem("send-and-receive-actor")
    val combineActor = system.actorOf(Props[CombineActor], name = "CombineActor")
    combineActor ! 12345
    Thread.sleep(5000)
    system.shutdown()
}
