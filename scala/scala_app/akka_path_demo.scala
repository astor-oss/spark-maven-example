object Example_08 extends App{
  import akka.actor.Actor
  import akka.actor.ActorLogging
  import akka.actor.ActorSystem
  import akka.actor.Props
  import akka.actor._

  class FirstActor extends Actor with ActorLogging{
    //通过context.actorOf方法创建Actor
    var child:ActorRef = _
    override def preStart(): Unit ={
      log.info("preStart() in FirstActor")
      //通过context上下文创建Actor
      child = context.actorOf(Props[MyActor], name = "myActor")
    }
    def receive = {
      //向MyActor发送消息
      case x => child ! x;log.info("received "+x)
    }
  }

  class MyActor extends Actor with ActorLogging{
    def receive = {
      case "test" => log.info("received test");
      case _      => log.info("received unknown message");
    }

  }
  val system = ActorSystem("MyActorSystem")
  val systemLog=system.log

  //创建FirstActor对象
  val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

  //获取ActorPath
  val firstActorPath=system.child("firstActor")
  systemLog.info("firstActorPath--->{}",firstActorPath)


  //通过system.actorSelection方法获取ActorRef
  val myActor1=system.actorSelection(firstActorPath)

  //直接指定其路径
  val myActor2=system.actorSelection("/user/firstActor")
  //使用相对路径
  val myActor3=system.actorSelection("../firstActor")


  systemLog.info("准备向myactor发送消息")
  //向myActor1发送消息
  myActor2!"test"
  myActor2! 123
  myActor3!"test"
  myActor3! 123456
  firstactor! 123456
  firstactor! "test"
  Thread.sleep(5000)
  //关闭ActorSystem，停止程序的运行
  system.shutdown()
}
