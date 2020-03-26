package 美团;

import java.util.Scanner;

public class 第二题 {
    //求圆的面积
    public static double circle(int n,int[] r){
        //n表示环数，r表示半径的数组
        double result = 0;
        if((n & 1) == 0){
            //偶数
            double m1;  //前一个环的面积
            double m2;  //后一个环的面积
            for (int i = 1; i <= n; i++) {
                if((i & 1)==0){
                    //碰到偶数环了
                    m2 = Math.PI * r[i] * r[i];
                    m1 = Math.PI * r[i-1] * r[i-1];
                    result += (m2 - m1);
                }
            }
            return result;
        }else{
            //奇数
            double m1;  //前一个环的面积
            double m2;  //后一个环的面积
            result += Math.PI * r[1] * r[1]; //先加上最里面的环
            for (int i = 3; i <= n; i++) {
                if((i & 1)==1){
                    m2 = Math.PI * r[i] * r[i];
                    m1 = Math.PI * r[i-1] * r[i-1];
                    result += (m2 - m1);
                }
            }
            return result;
        }
   }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();  //环数
            int[] r = new int[n+1];
            for (int i = 1; i < r.length; i++) {
                r[i] = sc.nextInt();
            }
            //传入参数得到返回值
            double result = circle(n,r);
            System.out.printf("%.5f",result);
        }
    }
}
