package 多线程;

public class 线程停止问题 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 50000000; i++) {
            if(Thread.interrupted()){
                //如果是停止状态，就break
                System.out.println("线程停止了，我要退出来");
                break;
            }
            System.out.println("第"+i+"次运行");
        }
    }

    public static void main(String[] args) {
//        Thread t1 = new 线程停止问题();
//        t1.start();
//        try {
//            Thread.sleep(2000);
//            t1.interrupt();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.out.println("========================================================");
//        }
        //this.interrupted()测试当前线程是否已经中断,这个方法会有清除状态

        //this.isInterrupted():测试线程是否已经中断,不会清除状态，停止就是停止了
        线程停止问题 t1 = new 线程停止问题();
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        t1.interrupt();
/*        Thread.currentThread().interrupt();
//        System.out.println(Thread.interrupted());  //这一次会产生true，随后就清除状态了
//        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());
        System.out.println(Thread.currentThread().isInterrupted());*/
    }
}
