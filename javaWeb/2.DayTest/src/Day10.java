import java.util.*;

public class Day10{
    //返回一个数组，这个数组里有两个数，0位置存放小于N，且离N最近的一个斐波那契数，1位置放大于的
    public static int[] fib(int N){
        int first = 0;
        int second = 1;
        int three = 1;
        int[] a = new int[2];
        while(three < N){
            three = first + second;
            first = second;
            second = three;
        }
        a[0] = first;
        a[1] = three;
        return a;
    }

    //机器人
    public static int count(int x,int y){
        if(x==1||y==1){
            return 1;
        }
        //count(x-1,y)代表向下走一步后所有的情况，count(x,y-1)代表向右走一步后的所有情况
        return count(x-1,y)+count(x,y-1);
    }
    public static void main(String[] args){
/*        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] a = fib(N);
        int steps =(N-a[0])<(a[1]-N)?(N-a[0]):(a[1]-N);
        System.out.println(steps);*/



    }
}