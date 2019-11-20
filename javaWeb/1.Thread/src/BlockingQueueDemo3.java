import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo3 {
    //临界缓冲区
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
    // new PriorityBlockingQueue<>();
    // new LinkedBlockingQueue<>();
    // new ArrayBlockingQueue<>(10);

    private static class Producer extends Thread {
        @Override
        public void run() {
            Random random = new Random(20191116);
            while (true) {
                try {
                    int message = random.nextInt(100);
                    queue.put(String.valueOf(message));
                    System.out.println("放入消息: " + message);
                    Thread.sleep(random.nextInt(3) * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Customer extends Thread {
        @Override
        public void run() {
            Random random = new Random(20191116);
            while (true) {
                try {
                    String message = queue.take();
                    System.out.println("收到消息: " + message);
                    Thread.sleep(random.nextInt(3) * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producer = new Producer();
        Thread customer = new Customer();
        producer.start();
        customer.start();
    }
}
