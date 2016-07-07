import scala.actors._

object SimpleReactorDemo extends Actor {
    import java.net.{ InetAddress, UnknownHostException }
    def act() = {
	react {
	    case (host:String, actor:Actor) =>
		println("=======> case1:" + host)
		//向actor发送解析后的IP地址信息, 于本例中传进来的actor就是NameResolver自身, 也即自己给自己发送消息，并存入将消息存入邮箱
		actor ! getIP(host)
		act()
	    case "EXIT" =>
		println("now receive exit signal")
	    case msg =>
		println("-------> case2:" + msg)
		act()
	}
    }
    
    def getIP(host:String):Option[InetAddress] = {
        try {
	    val address = InetAddress.getByName(host)
	    println("GetIp:" + address)
	    Some(address)
	} catch {
	    case _: UnknownHostException => None
	}
    }
}


object SimpleRectorMain {
    def main(args:Array[String]):Unit = {
	SimpleReactorDemo.start()
	SimpleReactorDemo ! ("www.baidu.com", SimpleReactorDemo)
    }
}
