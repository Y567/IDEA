
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("我是第一个线程");
                }
            }
        });
        pool.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("我是第二个线程");
                }
            }
        });
    }
}
