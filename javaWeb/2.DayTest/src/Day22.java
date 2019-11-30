import java.util.Scanner;

public class Day22 {
    public static void test(String a,String b){
        int aLen = a.length();
        char[] chars = b.toCharArray();
        int count = 0; //缺的数目
        for (int i = 0; i < chars.length; i++) {
            if(!a.contains(String.valueOf(chars[i]))){
                count++;
            }else{
                StringBuffer t = new StringBuffer(a);
                a = t.deleteCharAt(a.indexOf(String.valueOf(chars[i]))).toString();
            }
        }

        if(count==0){
            System.out.println("Yes "+(aLen-b.length()));
        }else{
            System.out.println("No "+count);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String a = in.nextLine();
            String b = in.nextLine();
            test(a,b);
        }
    }
}
