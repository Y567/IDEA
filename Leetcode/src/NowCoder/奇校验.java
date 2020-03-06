package NowCoder;

import java.util.Scanner;

public class 奇校验 {
        //处理的方法
        public static void process(String s){
            char[] chars = s.toCharArray();
            for(int i = 0;i < chars.length;i++){
                //将字符转换为ASCII码值
                int a = chars[i] - 0;
                //获得二进制串
                StringBuilder binary = new StringBuilder(Integer.toBinaryString(a));
                if(binary.length() < 8){
                    int len = 8 - binary.length();
                    for(int j = 0;j < len;j++){
                        binary.insert(0,"0");
                    }
                }
                //统计字符串中的1的个数
                int count = 0;
                for(int j = 0;j < binary.length();j++){
                    if(binary.charAt(j) == '1'){
                        count++;
                    }
                }
                if(0 == count % 2){
                    //偶数需要将最高位填个1
                    System.out.println("1"+binary.substring(1));
                }else{
                    System.out.println(binary);
                }
            }
        }

        //主方法
        public static void main(String[] args){
            //输入流
            Scanner in = new Scanner(System.in);
            while(in.hasNextLine()){
                String s = in.nextLine();
                process(s);
            }
        }
}
