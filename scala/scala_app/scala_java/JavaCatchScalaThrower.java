import java.io.IOException;
class ScalaThrower {
  //Scala利用注解@throws声明抛出异常
  @throws(classOf[Exception])
  def exceptionThrower {
    throw new Exception("Exception!")
  }
}

//Java中调用ScalaThrower(Scala类），然后捕获其抛出的异常
public class JavaCatchScalaThrower {
    public static void main(String[] args){
        ScalaThrower st=new ScalaThrower();
        try{
            st.exceptionThrower();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
