public class JconsoleDemo {
    private static Object object = new Object();
    private static class MyThread extends Thread{
        @Override
        public void run() {
            synchronized (object){
                try {
                    Thread.sleep(100000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (object){
            System.out.println("抢到锁了");
        }
    }
}
