package 多线程.Java多线程技能;

import java.util.concurrent.CopyOnWriteArrayList;

public class Demo1 extends Thread {
    @Override
    public void run() {
        System.out.println("子线程运行了");
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.start();
        System.out.println("主线程运行了");
        Thread.currentThread();
        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        String s = new String();
    }
}
