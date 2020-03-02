package NowCoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 转换人民币为繁体字 {
    static Map<String,String> down = new HashMap<String,String>();
    static ArrayList<String> up = new ArrayList<String>();
    static {
        down.put("0","零");
        down.put("1","壹");
        down.put("2","贰");
        down.put("3","叁");
        down.put("4","肆");
        down.put("5","伍");
        down.put("6","陆");
        down.put("7","柒");
        down.put("8","捌");
        down.put("9","玖");
        up.add("仟");
        up.add("佰");
        up.add("拾");
        up.add("亿");
        up.add("仟");
        up.add("佰");
        up.add("拾");
        up.add("万");
        up.add("仟");
        up.add("佰");
        up.add("拾");
        up.add("元");
        up.add("角");
        up.add("分");
    }
    //思路：仟 佰 拾 亿 仟 佰 拾 万 仟 佰 拾 元 角 角
    //                     1  5  1  1 2  1  1 5   ，先读下面再读上面，1拾5万1仟1佰。。。再将数字转换为汉字
    //转换
    public static String toRMB(double rmb){
        String s = String.valueOf(rmb);
        StringBuilder sb = new StringBuilder();
        if(String.valueOf(s.charAt(s.length()-1)).equals("0")){
            sb.insert(0,"整");
        }else{
            //分
            sb.insert(0,up.get(up.size()-1));
            sb.insert(0,down.get(String.valueOf(s.charAt(s.length()-1))));

            //角
            sb.insert(0,up.get(up.size()-2));
            sb.insert(0,down.get(String.valueOf(s.charAt(s.length()-2))));
        }
        //接下来在开始添加整数部分
        for(int i = s.indexOf(".")-1,index = up.size()-3;i >= 0;i--,index--){
            if(String.valueOf(s.charAt(i)).equals("0")){
                if(String.valueOf(s.charAt(i+1)).equals("0")){
                    //两个零就什么都不用添加了
                }else{
                    //添加一个零就完事了，单位不用添加
                    sb.insert(0,"零");
                }
            }else{
                sb.insert(0,up.get(index));
                sb.insert(0,down.get(String.valueOf(s.charAt(i))));
            }

        }
        //最后添加一个人民币
        sb.insert(0,"人民币");
        //最后我们将壹拾替换成拾，一般不这么念
        return sb.toString().replace("壹拾","拾");
    }

    //通过了20%的测试用例
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextDouble()){
            double rmb = in.nextDouble();
            System.out.println(toRMB(rmb));
        }
    }
}
