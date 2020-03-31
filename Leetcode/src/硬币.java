import java.util.Scanner;

public class 硬币 {
    //颠倒面
    public static void reverse(byte[] arr,int left,int right){
        for (int i = left; i <= right; i++) {
            if(arr[i]==0){
                arr[i]=1;
            }else{
                arr[i]=0;
            }
        }
    }

    public static int first(byte[] arr,int x,int y){
        int smallValue = 0;
        int left = 1;
        int right = 1;
        while(left <= right && right < arr.length){
            while(left < arr.length && arr[left]==1){
                //如果是1那么一直向后移动
                left++;
            }
            //出来有两种情况，一种right大于等于长度
            if(left > arr.length){
                break;
            }
            //走到这里说明找到第一个0了
            right = left;
            while(right < arr.length && arr[right]==0){
                //开始找连续的0
                right++;
            }
            //同样有两种情况
            if(right > arr.length){
                break;
            }
            //将left到right-1的位置进行反转
            reverse(arr,left,right-1);
            //计算代价
            smallValue += (x <= y ? x:y);
        }
        return smallValue;
    }


    public static int second(byte[] arr,int x,int y){
        //先整体翻转一遍一遍，因为可能0占的位置多
        reverse(arr,1,arr.length-1);
        //这时候要加的就是y了
        int smallValue = x;
        //之后再调用第一种方法
        smallValue += first(arr,x,y);
        return smallValue;
    }
    //找到代价最小
    public static int findSearchSmallValue(byte[] arr,int x,int y){
        //注意这里arr改变了
        byte[] arrTemp = new byte[arr.length];
        System.arraycopy(arr,1,arrTemp,1,arr.length-1);
        //第一种策略得到的值
        int first = first(arr,x,y);
        //第二种策略得到的值
        int second = second(arrTemp,x,y);

        return first < second ? first : second;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int N = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            byte[] arr = new byte[N+1];
            for (int i = 1; i <= arr.length-1; i++) {
                arr[i] = sc.nextByte();
            }
            System.out.println(findSearchSmallValue(arr,x,y));
        }
    }
}
