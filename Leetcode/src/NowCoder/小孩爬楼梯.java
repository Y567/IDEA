package NowCoder;


public class 小孩爬楼梯 {
    //计算
    public static int getCounts(int n){
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }else if(n == 3){
            return 4;
        }else {
            int first = 1;
            int second = 2;
            int third = 4;
            int forth = 0;
            while (n - 3 > 0) {
                forth = ((first + second)%1000000007+third%1000000007)%1000000007;
                first = second;
                second = third;
                third = forth;
                n--;
            }
            return forth;
        }
    }
    //递归写法
    public static int getCount(int n){
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }else if(n == 3){
            return 4;
        }else {
            return getCount(n-1)+getCount(n-2)+getCount(n-3);
        }
    }
    public static int countWays(int n) {
        // write code here
        return getCount(n);
    }

    public static void main(String[] args) {
        System.out.println(countWays(4));
    }
}