package 多线程.线程同步;

class ThreadA extends Thread{
    private String inital;
    public ThreadA(String inital){
        super();
        this.inital = inital;
    }

    @Override
    public void run() {
        System.out.println(inital+"线程成功运行");
    }
}
public class Synchroized {
    public static void main(String[] args) {
        ThreadA threadA = new ThreadA("阿狗");
        threadA.start();
    }

}
