package 关键字相关;

public class FinalTest {
    public static void main(String[] args) {
        final StringBuffer sb = new StringBuffer("sdew");
        //引用不可以变，但是里面的内容可以变
        sb.append("dscds");
        System.out.println(sb);
    }
}
