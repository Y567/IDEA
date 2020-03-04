package NowCoder;

import java.util.*;

public class IP地址转换{
    //ip地址转换为十进制数
    public static void toNumber(String ip){
        String[] ips = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        for(String s:ips){
            //每个二进制数
            String binary = Integer.toBinaryString(Integer.parseInt(s));
            if(binary.length() < 8){
                int count = binary.length();
                //小于8说明需要补零
                for(int i = 1;i <= 8 - count;i++){
                    binary = "0" + binary;
                }
            }
            sb.append(binary);
        }
        String s = sb.toString();
        System.out.println(Long.parseLong(s,2));
    }

    //将十进制数字转换为ip地址
    public static void toIP(Long l){
        //将Long型数据转换为二进制数据然后四位四位转换为十进制
        String binary = Long.toBinaryString(l);
        if(binary.length() < 32){
            int count = binary.length();
            //小于32位说明需要补零
            for(int i = 1;i <= 32 - count;i++){
                binary = "0" + binary;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i <= 24;i+=8){
            //截取四位二进制转换为十进制
            String s = binary.substring(i,i+8);
            if(i <= 16){
                sb.append(Integer.parseInt(s,2));
                sb.append("."); //添加一个点
            }else{
                sb.append(Integer.parseInt(s,2));
            }
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String s1 = in.nextLine();
            Long s2 = in.nextLong();
            toNumber(s1);
            toIP(s2);
            in.nextLine();
        }
    }
}