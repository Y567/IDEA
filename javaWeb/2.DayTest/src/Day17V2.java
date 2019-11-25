import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Day17V2 {

    //交换方法
    public static void swap(int[] a,int i,int j){
        a[i]^=a[j];
        a[j]^=a[i];
        a[i]^=a[j];
    }
    //a是入站序列，b是拷贝
    public static int[] test(int[] a){
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
                        return a;   //a是字典排序后的数组
                    }
                    j--;
                }
            }
            i--;
        }
        return a;
    }
    //判断b，是否为a的出栈队列
    public static boolean isPopOrder(int[] a,int[] b){
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for(int i = 0;i < a.length;i++){
            stack.push(a[i]);
            //注意栈里面放的是a的元素，即1 2 3
            while(!stack.isEmpty() && stack.peek()==b[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();  //为空说明是反之不是
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int N = in.nextInt();
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = in.nextInt();
            }
            int[] b = a.clone();
            //自己一定是自己的出栈队列所以直接输出
            for (int i:b) {
                System.out.print(i+" ");
            }
            System.out.println();

        }
    }
}
