package 网易;

import java.util.Arrays;
import java.util.Scanner;

public class 牛牛的等差数列 {

    //先写一个方法：找到一个数组中所有数的最大公约数
    public static int getD(int[] arr){
        //先找出最大的那个数，好决定需要循环的次数
        Arrays.sort(arr);
        int max = arr[arr.length-1];
        int result = -1;
        boolean flag = true;
        for (int i = 2; i <= max / 2; i++) {
            //暴力解决
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] % i != 0) {
                    //有一个数不能除尽，我们直接进行下一个数的判决
                    flag = false;
                    break;
                }
            }
            //走到这里说明i满足,把大的赋值给result
            if(flag){
                result = result < i ? i : result;
            }
        }

        return result;
    }

    //接下来写接受数据的方法
    public static int getResult(int[] a){
        //我们要先求出等差数列，比如
        //1 3 7 15  ---->   2,4,8
        if(a.length < 3){
            return -1;
        }
        int[] arr = new int[a.length-2];
        for (int i = 1; i < a.length - 1; i++) {
            arr[i-1] = a[i+1] - a[i];
        }
        return getD(arr);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            //输入数组长度
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            System.out.println(getResult(a));
        }
    }
}
