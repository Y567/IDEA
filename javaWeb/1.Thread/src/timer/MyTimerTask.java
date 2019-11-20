package timer;

public class MyTimerTask implements Comparable<MyTimerTask> {
    Runnable target;
    long delay;

    MyTimerTask(Runnable target, long delay) {
        this.target = target;
        this.delay = System.currentTimeMillis() + delay;
    }

    @Override
    public int compareTo(MyTimerTask o) {
        if (delay == o.delay) {
            return 0;
        } else if (delay < o.delay) {
            return -1;
        } else {
            return 1;
        }
    }
}
