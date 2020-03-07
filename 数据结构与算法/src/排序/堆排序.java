package 排序;

public class 堆排序 {
    /**
     * 调整大堆的方法，自顶向下调整
     * @param i       表示要调整的局部堆的根节点的下标表示
     * @param arr     待调整的数组（即堆）
     * @param length  length表示数组的长度（堆的元素个数）
     */

    //注意：该方法是调整堆，前提arr数组是堆（只要i位置的元素不满足堆的性质），
    //我们可以通过该方法调整i下标的元素的位置，让堆满足堆的定义
    public static void adjustHeap(int i,int[] arr,int length){
        //k则是i的左孩子的下标,k = i*2+1表示下一步的调整从k的左孩子开始
        for(int k = i * 2 + 1;k < length;k = i * 2 +1){
            if(k+1 < length && arr[k] < arr[k+1]){
                //先将k移动到左右孩子中大的一方
                k = k + 1;
            }
            if(arr[i] < arr[k]){
                //之后再比较父子节点的大小
                arr[i] = arr[i] ^ arr[k];
                arr[k] = arr[i] ^ arr[k];
                arr[i] = arr[i] ^ arr[k];
                //这里有一个回溯，就是当上层的节点将小的数换下来后，会重新调整下面的子堆
                i = k;  //将父节点的下标移动到子节点，因为经过交换后可能子节点的堆不满足堆的给定义了
            }else{
                //说明调整完毕了
                break;
            }
        }
    }

    /**
     * 堆排序
     * 思路：1.将数组调整为大顶堆，然后将最大的元素放到数组的最后
     *      2.将数组前length-1数再调整为大顶堆，将次大的数放在数组的倒数第二个位置
     *      3.重复类似的操作
     * @param arr   待排序的数组
     */
    public static void heapSort(int[] arr){
        //1.首先我们将arr调整为大顶堆,自底向上
        for (int i = arr.length / 2 -1; i >= 0 ; i--) {
            adjustHeap(i,arr,arr.length);
        }

        for (int i : arr) {
            System.out.println(i+" ");
        }
        //i是按堆的结构来说，从底向上的第一个非叶子节点
        for(int i = arr.length -1;i > 0;i--){
            //2.交换到后面
            arr[0] = arr[0] ^ arr[i];
            arr[i] = arr[0] ^ arr[i];
            arr[0] = arr[0] ^ arr[i];
            //将交换上去的小值调整下来
            adjustHeap(0,arr,i);
        }
    }
    public static void main(String[] args) {
        int[] arr = {9,7,6,4,2,45,75,23,21};
        heapSort(arr);
//        adjustHeap(2,arr,arr.length);
        for (int i : arr) {
            System.out.println(i+" ");
        }
    }
}
