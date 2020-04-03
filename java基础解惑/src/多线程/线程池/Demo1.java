package 多线程.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo1 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Executors.newCachedThreadPool();
    }
}
