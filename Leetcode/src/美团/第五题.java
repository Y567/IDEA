package 美团;

import java.util.Scanner;

public class 第五题 {
    //套娃
    public static long getSum(int K,int N,long[] arr){
        long[][] sum = new long[K+1][N+1];  //用于存储矩阵
        //第一行的数是arr
        for (int i = 1; i < sum[0].length; i++) {
            //赋初值
            sum[0][i] = arr[i-1];
        }

        //接下来遍历二维矩阵进行计算值
        for (int i = 1; i < sum.length; i++) {
            //i表示行数
            for (int j = 1; j < sum[i].length; j++) {
                //j表示列数
                for (int k = 1; k <= j; k++) {
                    sum[i][j] += sum[i-1][k];
                }
            }
        }
        //出来后表示矩阵计算完毕
        return sum[K][N];

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int N = sc.nextInt();
            int K = sc.nextInt();
            long[] arr = new long[N];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = sc.nextLong();
            }
            //得到答案
            System.out.println(getSum(K,N,arr));
        }
    }
}
