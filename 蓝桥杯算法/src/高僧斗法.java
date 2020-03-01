import java.util.Scanner;

public class 高僧斗法 {
    //判断是否为必败局势，返回0说明对方是必败局势，因为由我方将局势变为了所有异或结果为0的情况。这时候轮到了对方走且
    //局势是必败局势，所以返回true（也就是0）说明我方赢了
    public static boolean isZero(int[] a){
        /*
         *a数据记录的是小和尚的位置，我们需要判断的两两小和尚之间的台阶差的异或结果
         *最后（最高台阶的那个）一个小和尚看作一个人一个堆。相当于直接异或的结果为0，只需要判断前面的就可以
         *i+2是因为小和尚是两两扎堆。1，2和3，4  不是1，2和2，3
         */
        int result = 0;
        for(int i = 0;i < a.length - 1;i+=2){
            result ^= (a[i+1]-a[i]-1); // 减1（5和4相差的是5-4-1阶台阶）
        }
        return result==0;
    }


    //循环遍历小和尚的数组，从第一个开始穷举所以情况，找到使得局势变为对方必败的走法
    public static void nimGame(int[] a){
        for(int i = 0; i < a.length-1;i++){
            //先试试让小和尚走一步看能不能构成对方必败的局面,step
            for(int pos = a[i]+1;pos < a[i+1];pos++){
                //先记录住当前的小和尚的位置，如果不能成功需要将位置恢复原位
                int temp = a[i];
                //改变数组然后去判断当前局面是否为对方必败
                a[i] = pos;
                if(isZero(a)){
                    //是对方必败局面，那么直接输出，由于是从低台阶遍历，所以只要找到答案就输出，也满足题意(多答案输出小的)
                    //记得这里输出的是原位置不是移动后的位置，所以得输出temp
                    System.out.println(temp+" "+pos);
                    return;
                }else{
                    //不是我方想要的，归一吧
                    a[i] = temp;
                }
            }
        }
        //遍历完都没找到，我方没有赢面
        System.out.println("-1");
    }

    public static void main(String[] args){
        //获取数据
        Scanner in = new Scanner(System.in);
        //读取小和尚的位置
        String string = in.nextLine();
        //变换为数字
        String[] strings = string.split(" ");
        int[] a = new int[strings.length];
        for(int i = 0;i < a.length;i++){
            a[i] = Integer.parseInt(strings[i]);
        }
        //调用方法
        nimGame(a);
    }
}
