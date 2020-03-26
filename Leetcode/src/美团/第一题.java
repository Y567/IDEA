package 美团;

import java.util.Scanner;

public class 第一题 {
    //转换数字
    public static String replace(String s1,String s2){
        //先将s2去掉空格
        s2 = s2.replace(" ","");
        //s1为i，s2为a_i
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        //j表示a_i开始的下标
        for (int j = 0; j < chars2.length; j++) {
            char t = chars2[j];
            for (int i = 0; i < chars1.length; i++) {
                if(Integer.parseInt(String.valueOf(chars1[i])) == (j+1)){
                    chars1[i] = t;
                }
            }

        }
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append(chars1).toString();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s1 = in.nextLine();
            String s2 = in.nextLine();
            System.out.println(replace(s1,s2));
        }
    }
}
