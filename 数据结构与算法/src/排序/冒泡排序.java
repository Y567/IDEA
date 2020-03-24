package 排序;

import java.util.Arrays;

public class 冒泡排序 {
    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            //记录一个数，如果这个数在这一趟排序中没有加1说明一次排序下来没有一次交换数据，说明已经有序，那么我们就可以退出了
            int swap = 0;
            //外层循环是冒泡排序的次数
            for (int j = 0; j < arr.length - i-1; j++) {
                if(arr[j] > arr[j+1]){
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                    swap++;
                }
            }
            if(swap == 0){
                return;
            }
        }
    }
    public static void main(String[] args) {
        //测试一下排序
        int[] arr = new int[]{9,8,7,6,5,4,3,2,1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
