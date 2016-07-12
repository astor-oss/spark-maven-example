
object ComplexActor extends App {
   import akka.actor.Actor
   import akka.actor.ActorLogging
   import akka.actor.ActorSystem
   import akka.actor.Props
   import akka.actor._

   class FirstActor extends Actor with ActorLogging {
       var child: ActorRef = _
       override def preStart():Unit = {
	   log.info("preStart in first actor")
           child = context.actorOf(Props[MyActor], "mychild")
       }

       def receive = {
	   case x => child !x; log.info("receive xxx message" + x);
       }

       override def postStop():Unit = {
	   log.info("postStop() in FirstActor")
       }
   }

   class MyActor extends Actor with ActorLogging {
       override def preStart():Unit = {
	   log.info("preStart() in MyActor")
       }

       def receive = {
	   case "test" => log.info("received test")
       }
 
       override def unhandled(message:Any):Unit = {
	   log.info("unhandled message is {}", message)
       }

       override def postStop():Unit = {
	  log.info("postStop in myActor")
       }
   }

   val system = ActorSystem()
   val systemLog = system.log

   systemLog.info("starting.................")
   val actor = system.actorOf(Props[FirstActor], "firstactor")
   actor !"test"
   actor !123
   system.shutdown()
}
