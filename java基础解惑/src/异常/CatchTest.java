package 异常;

public class CatchTest {
    /**
     * 测试try里面可不可以抛异常
     */
    public static void test(){
        try{
            System.out.println("异常测试");
            throw new RuntimeException("异常发生了");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        /*try {
            int i = 0;
            for (int j = 0; j < 10; j++) {
                i += j;
            }
            i = i / 0;
        } catch (Exception e) {
            e.printStackTrace();
            //发生异常后没这句话会继续执行，JVM不会结束
            System.out.println("异常之后的语句");
        }
        System.out.println("代码的最后");*/

        test();
    }
}
