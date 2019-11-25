import java.util.Arrays;
import java.util.Scanner;

public class Day17 {

    //自己的错误的版本,这好像只是实现了一个全排列，并未判断
    //交换方法
    public static void swap(int[] a,int i,int j){
        a[i]^=a[j];
        a[j]^=a[i];
        a[i]^=a[j];
    }
    //字典序
    public static boolean nextDic(int[] a){
        int i = a.length-1;
        while(i>0){
            if(a[i-1]<a[i]){
                int j = a.length-1;
                while(j>(i-1)){
                    if(a[j]>a[i-1]){
                        swap(a,i-1,j);
                        //有字典序列
                        //将i-1后面的数字排序
                        Arrays.sort(a,i,a.length);
                        //接下来就是输出了
                        for (int i1 : a) {
                            System.out.print(i1 + " ");
                        }
                        System.out.println();//换行
                        return true;
                    }
                    j--;
                }
            }
            i--;
        }
        return false;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
        }
        for (int i:a) {
            System.out.print(i+" ");
        }
        System.out.println();
        while(nextDic(a)){
            nextDic(a);
        }
    }
}
