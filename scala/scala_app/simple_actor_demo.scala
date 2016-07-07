
import scala.actors.Actor

class SimpleActor extends Actor {
      def act() {
	  while (true) {
		// receive从邮箱中获取一条消息
		// 然后传递给它的参数
		// 该参数是一个偏函数
		receive {
		    case "actorDemo" => println("receive actor demo request...")
		}
	  }
      }
}


object SimpleActorDemo {
    def main(args:Array[String]):Unit = {
	val actor = new SimpleActor
	actor.start()
	for ( i <- 1 until 20) {
	    actor!"actorDemo"
	}
    }
}
