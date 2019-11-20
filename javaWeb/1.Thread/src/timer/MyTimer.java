package timer;

import java.util.concurrent.PriorityBlockingQueue;

public class MyTimer {
    PriorityBlockingQueue<MyTimerTask> queue = new PriorityBlockingQueue<>();
    Worker worker;

    private static class Worker extends Thread {
        PriorityBlockingQueue<MyTimerTask> queue;

        Worker(PriorityBlockingQueue<MyTimerTask> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    MyTimerTask task = queue.take();
                    long current = System.currentTimeMillis();
                    if (task.delay <= current) {
                        task.target.run();
                    } else {
                        queue.put(task);
                        Thread.sleep(task.delay - current);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    MyTimer() {
        worker = new Worker(queue);
        worker.start();
    }

    void execute(Runnable target, long delay) {
        queue.put(new MyTimerTask(target, delay));
    }

    public static void main(String[] args) {
        MyTimer timer = new MyTimer();
        timer.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("该起床了");
            }
        }, 2000);
        System.out.println("另一个人");
    }
}
