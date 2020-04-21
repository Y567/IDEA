package 度小满;

import java.util.Scanner;

public class 城市跳转 {
    //城市跳转
    public static int toNCity(int[] a,int N,int A,int B,int C){
        //先判断当前城市是否满足
        if(a[1]==N){
            //说明可以到达，那么就花费A就好了
            return A;
        }
        //遍历数组，找到最接近N的那个城市，并得到它的下标
        int min = N;
        int index = 0;
        for (int i = 1; i < a.length; i++) {
            int t = a[i] - N;
            if(t < 0) {
                //取绝对值，也就是找出离N最近的，不管是左还是右
                t = -t;
            }
            //将下标得到
            if(t < min){
                //比之前得到的小
                min = t;
                index = i;
            }
        }

        //最后需要花费的代价
        int value = 0;

        //找到最接近N的数字以及它的下标后，试着花最小的代价接近它
        //我们需要知道城市1可以去的城市
        int oneToCity = a[1];
        if(oneToCity > index){
            if(oneToCity - N < 0){
                value -= B * (oneToCity - N);
            }else{
                value += B * (oneToCity - N);
            }
            //跳过去
            value += A;
        }else if(oneToCity < index){
            if(N - oneToCity < 0){
                value -= B * (N - oneToCity);
            }else{
                value += B * (N - oneToCity);
            }
            //跳过去
            value += A;
        }else{
            //相等可以一步跳过去
            value += A;
            //一步到达后，将a[index]变为N
            if(a[index] < N){
                value += C * (N - a[index]);
            }else if(a[index] > N){
                value += B * (a[index] - N);
            }else{
                return value;
            }
        }
        return value;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int N = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            int[] a = new int[N+1];
            for (int i = 1; i < a.length; i++) {
                a[i] = sc.nextInt();
            }
            System.out.println(toNCity(a,N,A,B,C));
        }
    }
}
