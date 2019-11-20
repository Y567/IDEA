import java.util.Random;

public class MyQueue {
    private int[] array = new int[2];
    private int front = 0;
    private int rear = 0;
    private int size = 0;

    public synchronized void put(int message) throws InterruptedException {
        while (size == array.length) {
            wait();
        }

        array[rear] = message;
        rear = (rear + 1) % array.length;
        size++;
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (size == 0) {
            wait();
        }

        int message = array[front];
        front = (front + 1) % array.length;
        size--;
        notifyAll();
        return message;
    }

    //用的是一个缓冲区
    private static final MyQueue queue = new MyQueue();

    private static class Producer extends Thread {
        @Override
        public void run() {
            Random random = new Random();
            while (true) {
                try {
                    queue.put(random.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Customer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread p1 = new Producer();
        Thread p2 = new Producer();
        Thread p3 = new Producer();

        Thread c1 = new Customer();
        Thread c2 = new Customer();
        Thread c3 = new Customer();
        Thread c4 = new Customer();
        Thread c5 = new Customer();
        Thread c6 = new Customer();
        Thread c7 = new Customer();
        Thread c8 = new Customer();
        Thread c9 = new Customer();
        Thread c10 = new Customer();

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
        c8.start();
        c9.start();
        c10.start();
    }
}
