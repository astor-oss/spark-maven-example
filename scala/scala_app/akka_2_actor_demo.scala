
object TwoActorDemo extends App {
    import akka.actor.Actor
    import akka.actor.ActorLogging
    import akka.actor.ActorSystem
    import akka.actor.Props

    class MyActor extends Actor with ActorLogging {
          def receive = {
               case "test" => log.info("receive test log...")
               case _ => log.info("unkown actor message")
          }
    }

    class FirstActor extends Actor with ActorLogging {
	val child = context.actorOf(Props[MyActor], "myChild")
        def receive = {
		case x =>
			child !x; log.info("receive " + x + ", sending to child")
	}
    }

    val system = ActorSystem("TwoActorSystem")
    val systemLog = system.log

    val first_actor = system.actorOf(Props[FirstActor], "firstactor")
    systemLog.info("starting send actor message .........")
    first_actor !"test"
    first_actor !"test12222"
    Thread.sleep(1000)
    system.shutdown()
   
}
