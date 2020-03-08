package 排序;

public class 堆排序巩固 {
    //调整堆的方法
    public static void adjustHeap(int i,int[] arr,int length){
        for(int k = i * 2 + 1;k < length;k = i * 2 + 1){
            if(k+1 < length && arr[k] < arr[k+1]){
                //右孩子大，所以将k指向右孩子
                k = k+1;
            }
            if(arr[i] < arr[k]){
                //和父节点比较，父节点小了和子节点交换下来
                arr[i] = arr[i] ^ arr[k];
                arr[k] = arr[i] ^ arr[k];
                arr[i] = arr[i] ^ arr[k];
                //让i再指向k进行下一步调整
                i = k;
            }else{
                //调整堆结束了
                break;
            }
        }
    }


    //堆排序的方法
    public static void heapSort(int[] arr){
        //先将arr数组调整为堆,从第一个非叶子节点调整，自底向上
        for (int i = arr.length/2-1; i >= 0 ; i--) {
            adjustHeap(i,arr,arr.length);
        }
//        for(int i: arr){
//            System.out.println(i);
//        }


        //将大顶堆的最大元素换到数组的最后方，重复建堆交换的操作
        for (int i = arr.length - 1; i > 0; i--) {
            //先交换一次，因为目前堆已是大顶堆
            arr[0] = arr[0] ^ arr[i];
            arr[i] = arr[0] ^ arr[i];
            arr[0] = arr[0] ^ arr[i];
            //将根节点调整一次
            adjustHeap(0,arr,i);
        }
    }


    public static void main(String[] args) {
        //测试一哈堆排序
        int[] arr = {9,8,7,6,5,4,32,56,87};
        heapSort(arr);
        for(int i: arr){
            System.out.println(i);
        }
    }
}
