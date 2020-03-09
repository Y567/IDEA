package 排序;

import java.util.Random;

public class 归并排序巩固 {
    //合并的方法
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        //接受下标
        int i = left;
        int t = 0;       //表示临时数组的下标
        int j = mid+1;   //右边数组的下标
        while(i <= mid && j <= right){
            //可以进行比较并且拷贝数据到temp中
            if(arr[i] < arr[j]){
                //左边的小
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        //已经将数组合并成功，需要判断左右两边的数组是否都遍历完毕
        while(i <= mid){
            //左边数据没有遍历完毕
            temp[t++] = arr[i++];
        }
        while(j <= right){
            //左边数据没有遍历完毕
            temp[t++] = arr[j++];
        }

        //最后将合并好的数组temp拷贝回原数组
        t = 0;
        int tempLeft = left;
        //这里必须得等于才行，因为传进来的参数是闭合下标
        while(tempLeft <= right){
            arr[tempLeft++] = temp[t++];
        }
    }

    //排序
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        //判断
        if(left < right){
            int mid = (left + right)/2;
            //左递归进行分解
            mergeSort(arr,left,mid,temp);
            //有递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
        //排序成功
    }
    public static void main(String[] args) {
        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];

        //我们测试一下速度
        for (int i = 0; i < 8000000; i++) {
            arr[i] = new Random().nextInt(800000);
        }
        //排序
        System.out.println(System.currentTimeMillis());
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(System.currentTimeMillis());
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]+" ");
//
//        }
    }
}
