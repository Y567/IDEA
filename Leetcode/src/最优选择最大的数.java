import java.util.Scanner;

public class 最优选择最大的数 {
    //先写一个减去分数的方法
    public static void sub(int[] a,int[] b,int length){
        for (int i = 0; i < length; i++) {
            a[i] -= b[i];
        }
    }

    /**
     * 得到最优
     * 思路:贪心算法去求，每次取的数应该是b[i]/a[i]最大的那个，这样先把减的快的拿走，到最后拿到的最多
     * @param a  ai
     * @param b  bi
     * @param m  回合数
     * @param n  元素个数
     * @return  最优的数字
     */
    public static int getBestScore(int[] a,int[] b,int m,int n){
        int result = 0;
        for (int i = 1; i <= m; i++) {
            int take = 0;
            int takeIndex = 0;
            for (int j = 0; j < n; j++) {
                //将拿走的数组元素改为-1;不干扰后序计算
                if(a[j] == 0){
                    continue;
                }
                int temp = b[j]/a[j];
                if(take < temp){
                    //记录拿走的下标
                    take = a[j];
                    takeIndex = j;
                }
            }
            result += take;
            //进行减去数字操作
            sub(a,b,n);
            //将下标位置置为-1
            a[takeIndex] = -1;
            b[takeIndex] = -1;
        }
        //返回值
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                b[i] = sc.nextInt();
            }
            //输出
            System.out.println(getBestScore(a,b,m,n));

        }
    }
}