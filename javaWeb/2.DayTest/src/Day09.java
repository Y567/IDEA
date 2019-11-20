import java.math.BigInteger;
import java.util.Scanner;

public class Day09 {
    //求阶乘的方法
    public static BigInteger fun(BigInteger n){
        BigInteger result = n;
        while(n.compareTo(new BigInteger("1"))>0){
            BigInteger temp = n.subtract(new BigInteger("1"));
            result = result.multiply(temp);
            n = n.subtract(new BigInteger("1"));
        }
        return result;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        BigInteger n = in.nextBigInteger();
        BigInteger result = fun(n);
        String temp = String.valueOf(result);
        String[] strings = temp.split("");
        int count = 0;
        for(int i = strings.length-1;i>=0;i--){
            if(!strings[i].equals("0")){
                break;
            }
            count++;
        }
        System.out.println(count);
    }
}
