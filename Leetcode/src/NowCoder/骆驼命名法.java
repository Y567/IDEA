package NowCoder;

import java.util.Scanner;

public class 骆驼命名法 {
    public static void main(String[] args){
        //首先得到标准输入流
        Scanner sc = new Scanner(System.in);
        //多组输入数据，那么需要用到循环
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            if(s == null || 0 == s.length()){
                System.out.println();
            }else{
                String[] ss = s.split("_");
                StringBuilder sb = new StringBuilder(ss[0]);
                //从第二个开始改变大写字母
                for(int i = 1;i < ss.length;i++){
                    String temp = ss[i];
                    //获得首字母
                    char c = ss[i].charAt(0);
                    if('a'<= c && c <= 'z'){
                        //说明是小写,转换为大写
                        char t = (char)(c - 32);
                        sb.append(t);
                        sb.append(temp.substring(1));
                    }else{
                        //直接加
                        sb.append(temp);
                    }
                }
                System.out.println(sb.toString());
            }
        }

    }
}
