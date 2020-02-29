package 异常;

public class RuntimeException {
    public static void main(String[] args) {
        //测试一下运行时异常可以捕获吗
        int i = 0;
        try {
            i = 10;
            i = i / 0;
        } catch (Exception e) {
            System.out.println("出现异常了");
        } finally {
            System.out.println("最终的结果是"+i);
        }
    }
}
