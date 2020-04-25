
package 类加载;

class Parent {
    public static int t = parentStaticMethod2();

    {
        System.out.println("父类非静态初始化块");
    }

    static {
        System.out.println("父类静态初始化块");
    }

    public Parent() {
        System.out.println("父类的构造方法");
    }

    public static int parentStaticMethod() {
        System.out.println("父类类的静态方法");
        return 10;
    }

    public static int parentStaticMethod2() {
        System.out.println("父类的静态方法2");
        return 9;
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
        System.out.println("销毁父类");
    }

}
class Child extends Parent {
    {
        System.out.println("子类非静态初始化块");
    }
    static
    {
        System.out.println("子类静态初始化块");
    }
    public Child()
    {
        System.out.println("子类的构造方法");
    }
    public static int childStaticMethod()
    {
        System.out.println("子类的静态方法");
        return 1000;
    }
    @Override
    protected void finalize() throws Throwable
    {
        // TODO Auto-generated method stub
        super.finalize();
        System.out.println("销毁子类");
    }
}

public class 执行顺序{
    public static void main(String[] args) {
//        Parent.parentStaticMethod();
        Child child = new Child();
    }
}