package 抽象类和接口;

import java.util.HashMap;

class abstractClass {
    static void fun(){
        System.out.println("抽象类的方法被执行了");
    }
}
public class AbstractClass extends abstractClass{
    public static void main(String[] args) {
        fun();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    }
}
