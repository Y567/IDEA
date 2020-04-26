import java.util.Scanner;

public class Web测验 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int i = 0;i < arr.length;i++){
                arr[i] = in.nextInt();
            }
            //输入数据完毕后，进行处理
            int left = 0;
            int right = left+1;
            //和数组的元素一样，一个字符串就是输出的一行
            String[] result = new String[n];
            for (int i = 0; i < n; i++) {
                result[i] = "";
            }
            for(int i = 0;i < n;i++){
                while(left >= 0 && arr[left] >= arr[i]){
                    //向左查询
                    left--;
                }
                //出来有两种可能
                if(left < 0){
                    //没找到
                    result[i] += "-1";
                }else{
                    //说明找到了
                    result[i] += String.valueOf(left);
                }

                //加上一个空格
                result[i] += " ";

                //开始找右边
                while(right <= n-1 && arr[right] >= arr[i]){
                    //向左查询
                    right++;
                }
                //出来有两种可能
                if(right > n - 1){
                    //没找到
                    result[i] += "-1";
                }else{
                    //说明找到了
                    result[i] += String.valueOf(right);
                }
                //一个result完毕，接着后面的
                left = i;
                right = i + 2;
            }
            for (int i = 0; i < n; i++) {
                System.out.println(result[i]);
            }
        }
    }
}