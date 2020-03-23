package 多线程.线程优先级;
class ThreadTest extends Thread{
    @Override
    public void run() {
        System.out.println("子线程优先级为"+this.getPriority());
    }
}
public class Priority {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest thread = new ThreadTest();
        System.out.println("main方法的线程优先级是"+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
//        Thread.sleep(10000);
        /*Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("子线程优先级为"+this.getPriority());
            }
        };*/
        thread.start();
    }
}
