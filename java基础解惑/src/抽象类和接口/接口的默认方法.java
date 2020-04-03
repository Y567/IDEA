package 抽象类和接口;

interface 接口的默认方法 {
    default void test1() {
        System.out.println("111111111默认方法");
    }
    static void test2(){
        System.out.println("111111111静态方法");
    }

}
interface 接口的默认方法2 {
    default void test1() {
        System.out.println("22222222222默认方法");
    }
    static void test2(){
        System.out.println("22222222222静态方法");
    }

}
