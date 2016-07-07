
import java.util.ArrayList;
import java.util.List;

public class JavaWildcardGeneric {
    //Java的通配符类型，要接受任何类型
    public static List<?> getList(){
           List<String> listStr=new ArrayList<String>();
           listStr.add("xxxx1");
           listStr.add("xxxx2");
           return listStr;
    }
}
