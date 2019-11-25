import java.math.BigInteger;

public class Day15 {
    public static int add(int a,int b){
        return b==0?a:add(a^b,(a&b)<<1);

    }
    public static void main(String[] args){
        System.out.println("年代为南非");
    }
}
