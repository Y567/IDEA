package 抽象类和接口;

public class 接口的实现类 implements 接口的默认方法,接口的默认方法2{
    public static void main(String[] args) {
//        接口的默认方法.test2();
        new 接口的实现类().test1();
    }

    @Override
    public void test1() {
        //如果实现类实现的两个接口有相同名字的默认方法需要重写这个default方法
        System.out.println("重写了重名的default方法");

    }
}
