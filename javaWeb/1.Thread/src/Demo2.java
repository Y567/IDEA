import java.time.temporal.Temporal;

public class Demo2 {
    public static class Foo{
        private int n = 1;
        public void one(){
            synchronized (this){
                if(n!=1){
                    return;
                }
                n=2;
                System.out.println("one");
            }
        }
        public void two(){
            synchronized (this){
                if(n != 2){
                    return;
                }
                n=3;
                System.out.println("two");
            }
        }
        public void three(){
            while(true){
                if(n!=3){
                    return;
                }
                n=1;
                System.out.println("Three");
            }
        }
    }



    private static Foo foo = new Foo();
    private static class OneThread extends Thread{
        @Override
        public void run() {
            while(true){
                foo.one();
            }
        }
    }

    private static class TwoThread extends Thread{
        @Override
        public void run() {
            while (true){
                foo.two();
            }
        }
    }

    private static class ThreeThread extends Thread{
        @Override
        public void run() {
            while(true){
                foo.three();
            }
        }
    }
    public static void main(String[] args) {
        Thread oneThread = new OneThread();
        Thread twoThread = new TwoThread();
        Thread threeThread = new ThreeThread();

        oneThread.start();
        twoThread.start();
        threeThread.start();
    }
}
