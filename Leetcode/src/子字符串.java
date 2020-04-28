import java.util.Scanner;

public class 子字符串 {

    public static int getMax(String s1,String s2){
        //s2是子字符串,s1是父字符串,而且两个字符串是反过来的,倒着找
        int pre = 0; //记录前一个坐标
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            int i1 = s1.indexOf(String.valueOf(c),pre);
            if(i1 == -1){
                //没找到，直接返回-1
                return -1;
            }
            //找到了
            pre = i1 + 1;
            if(i==s2.length()-1){
                //最后一个了
                return s1.length()-(i+1);
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        //思路是从后向前找，将原字符串反转
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            String[] ss = s.split(" ");
            StringBuilder sb1 = new StringBuilder(ss[0]);
            StringBuilder sb2 = new StringBuilder(ss[1]);
            System.out.println(getMax(sb1.reverse().toString(),sb2.reverse().toString()));
        }
    }
}
