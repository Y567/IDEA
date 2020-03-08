package 排序;

public class 归并排序 {
    /**
     * 归并排序，你需要传入5个参数
     * @param arr    表示待合并的子数组
     * @param left   左边的下标
     * @param mid    中间的下标
     * @param right  右边的下标（最右边）
     * @param temp   存放合并好的子数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;   //要合并的左边的数据的开始下标
        int j = mid+1;  //要合并的右边的数据的开始下标
        int t = 0;//表示临时数组的开始下标

        //1.遍历数据将数据顺序放在temp里面
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                //说明左边的数据小
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }

        //2.拷贝剩余的数据到temp里面，当跳出循环后会出现两种情况，不是左边数据先遍历完，就是右边数据先遍历完
        while(i <= mid){
            //将剩余的左边数据赋值给temp
            temp[t++] = arr[i++];
        }
        while(j <= right){
            //将剩余的右边数据赋值给temp
            temp[t++] = arr[j++];
        }

        //3.将合并好后的temp数据赋值给原数组arr,注意这里拷贝原数组arr是从left下标开始的而temp是从0开始的，临时数组只是个存储东西的
        t = 0;
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }

    //分而治之
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            //可以继续分下去
            int mid = (left + right) /2;
            //分左边
            mergeSort(arr,left,mid,temp);
            //分右边
            mergeSort(arr,mid+1,right,temp);
            //最后合并
            merge(arr,left,mid,right,temp);
        }
    }
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        int[] temp = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
