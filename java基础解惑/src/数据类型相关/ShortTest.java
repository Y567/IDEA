package 数据类型相关;

public class ShortTest {
    public static void main(String[] args) {
        short s1 = 1;
        s1 += 1;
        //这里回发生自动转换类型，所以结果需要进行强转
        s1 = (short) (s1 + 1);
    }
}
