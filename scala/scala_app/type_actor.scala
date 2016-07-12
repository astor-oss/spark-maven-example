import akka.actor.ActorSystem
import akka.event.Logging
import scala.concurrent.{ Promise, Future }
import akka.actor.TypedProps
import scala.concurrent.duration._

object TypedActor extends App {
    trait Squarer {
        // fire-and-forget消息
        def squareNotCare(i:Int): Unit
        //非阻塞send-request-reply消息
        def square(i:Int): Future[Int]
        // 阻塞式的send-request-reply消息
        def squareNowPlease(i:Int):Option[Int]
        // 阻塞式的send-request-reply消息
        def squareNow(i:Int):Int
    }
    
    class SquareImpl(val name:String) extends Squarer {
        def this() = this("DefaultSquareImpl")
        def squareNotCare(i:Int):Unit = i * i
        def square(i:Int): Future[Int] = Promise.successful(i*i).future
        def squareNowPlease(i:Int):Option[Int] = Some(i*i)
        def squareNow(i:Int):Int = i*i
    }
    
    
    val system = ActorSystem("typedActorSystem")
    val log = Logging(system, this.getClass)
    val mySquare: Squarer = TypedActor(system).typedActorOf(TypedProps[SquareImpl](), "mySquare")
    val other:    Squarer = TypedActor(system).typedActorOf(TypedProps(classOf[Squarer], new SquareImpl("SquarerImpl")), "otherSquarer")
}
