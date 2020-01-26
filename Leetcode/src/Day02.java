public class Day02 {
    //原地删除指定元素
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == val){
                count++;
            }
        }

        int index = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] != val){
                nums[index++] = nums[i];
            }
        }

        return nums.length-count;
    }

    //搜索插入位置
    public static int searchInsert(int[] nums, int target) {
        int index = 0;
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == target){
                index = i;
                break;
            }else if(nums[i] < target){
                index++;
            }
        }
        return index;
    }
    public static void main(String[] args) {
        /*int[] nums = {3,2,2,3};
        int result = removeElement(nums,3);
        System.out.println(result);
        for(int i:nums){
            System.out.println(i);
        }*/



        int[] nums = {1,3,5,6};
        System.out.println(searchInsert(nums,2));

    }
}
