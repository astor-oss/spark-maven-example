
object RegexMatchDemo {
    def main(args: Array[String]):Unit = {
	// case 1. email
	val mailRegx = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$".r
	for (matchStr <- mailRegx.findAllIn("hzxjtx@172.com")) {
		println(matchStr)
	}

	// case 2. network address
	val webRegx = "^[a-zA-Z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\s*)?$".r
	for (matchStr <- webRegx.findAllIn("http://www.baidu.com"))
		println(matchStr)
    }
}
