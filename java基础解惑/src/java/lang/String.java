package java.lang;

public class String {
    public String toString(){
        System.out.println("我是自定义的String类");
        return "自定义String";
    }

    //1.类加载器收到加载请求
    //2.将这个请求向上委托给父类加载器，一直向上委托，直到启动加载器，即根加载器
    //3.如果跟加载器可以加载，那么就加载，否则就抛出异常向下加载，直到有可以加载的。
    public static void main(String[] args) {
        String s = new String();
        System.out.println(s.toString());
        System.out.println("加载该类的加载器是"+s.getClass().getClassLoader());
    }
}
