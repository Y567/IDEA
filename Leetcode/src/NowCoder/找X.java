package NowCoder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class 找X{
    public static void main(String[] args){
        //输入
        Scanner in = new Scanner(System.in);
        //循环输入测试用例
        while(in.hasNextInt()){
            //利用HashSet存储数据
            Map<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
            int n = in.nextInt();
            for(int i = 0;i < n;i++){
                map.put(in.nextInt(),i);
            }
            int x = in.nextInt();
            if(map.containsKey(x)){
                System.out.println(map.get(x));
            }else{
                System.out.println("-1");
            }
        }
    }
}