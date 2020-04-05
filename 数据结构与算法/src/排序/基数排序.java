package 排序;

import java.util.Arrays;

public class 基数排序 {

    //基数排序
    private static void radixSort(int[] arr){
         //创建二维数组的一个桶，用来存储数据
        int[][] bucket = new int[10][arr.length];

        //创建一个一维数组来存储桶中的元素个数,为什么是10个呢？是因为0~9一共就最多10个桶
        int[] bucketCounts = new int[10];

        //先要找到最大的那个数字，得到他的最长位数
        int max = 0;
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
        }

        //这是求一个正整数的位数，可能这就是编程之美吧
        int maxLength = (max + "").length();

        //长度就是需要循环的次数,n表示求个位，十位，百位的一个中间变量,n每次变大10倍
        for (int i = 1,n = 1; i <= maxLength; i++,n = n * 10) {
            for (int j = 0; j < arr.length; j++) {
                //求出对应的位数
                int temp = arr[j] / n % 10;
                //将这个元素赋值给对应的桶内
                bucket[temp][bucketCounts[temp]] = arr[j];
                //存入的元素多了一个那么加1
                bucketCounts[temp]++;
            }

            //接下来遍历桶然后将桶内的元素赋值给原数组
            int index = 0;
            for (int j = 0; j < 10; j++) {
                //j == 0是第一个桶,bucketCounts[j]表示这个桶内的元素个数
                for (int k = 0; k < bucketCounts[j]; k++) {
                    int elements = bucket[j][k];
                    arr[index++] = elements;
                }
                //遍历完一个桶后就可以卸磨杀猪了
                bucketCounts[j] = 0;
            }
        }
    }



    public static void main(String[] args) {
        //测试一下排序算法
        int[] arr = {34,231,565,23,123,124,56,67};
        radixSort(arr);
        //输出结果
        System.out.println(Arrays.toString(arr));
    }
}
