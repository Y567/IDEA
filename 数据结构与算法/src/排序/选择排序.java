package 排序;

import java.util.Arrays;

public class 选择排序 {

    public static void slectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            //i最多走到倒数第二个
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    //说明arr[j]比较小，交换到最前面
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
    }


    public static void main(String[] args) {
        //测试一下选择排序
        int[] arr = {9,8,7,6,5,4,3,2,1};
        slectSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
