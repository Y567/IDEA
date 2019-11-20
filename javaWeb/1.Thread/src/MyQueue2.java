public class MyQueue2 {
    private int[] array = new int[2];
    private volatile int size;
    private int front;
    private int rear;

    private Object full = new Object();
    private Object empty = new Object();

    public void put(int message) throws InterruptedException {
        while (size == array.length) {
            synchronized (full) {
                full.wait();
            }
        }

        synchronized (this) {
            array[rear] = message;
            rear = (rear + 1) % array.length;
            size++;
        }

        synchronized (empty) {
            empty.notify();
        }
    }

    public synchronized int take() throws InterruptedException {
        while (size == 0) {
            synchronized (empty) {
                empty.wait();
            }
        }

        int message;
        synchronized (this) {
            message = array[front];
            front = (front + 1) % array.length;
            size--;
        }

        synchronized (full) {
            full.notify();
        }

        return message;
    }
}
