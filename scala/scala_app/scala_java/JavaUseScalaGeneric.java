
public class JavaUseScalaGeneric {
    public static void main(String[] args){
        Student<String,Integer> student=new Student<String,Integer>("小李",18);
        //Scala版本的getter方法
        System.out.println(student.name());
        //JavaBean版本的getter方法
        System.out.println(student.getName());
    }
}
