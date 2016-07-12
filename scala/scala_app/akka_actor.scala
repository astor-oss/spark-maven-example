

object ActorDemo extends App {
    import akka.actor.Actor
    import akka.event.Logging
    import akka.actor.ActorSystem
    import akka.actor.Props

    class MyActor extends Actor {
        val log = Logging(context.system, this)
        def receive = {
	     case "test" => log.info("receive test")
	     case _ => log.info("receive unkown message")
        }
    }

    val system = ActorSystem("actorDemoSystem")
    val systemLog = system.log
    val actor = system.actorOf(Props[MyActor], name = "myactor")
    systemLog.info("starting send actor message ...")
    actor !"test"
    actor !123

    system.shutdown
}

