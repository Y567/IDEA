import java.util.Arrays;

public class Day08 {
    //加一
    public int[] plusOne(int[] digits) {
        for(int i = digits.length-1;i >= 0;i--){
            digits[i]++;
            digits[i] = digits[i] % 10;
            if(digits[i] != 0){
                return digits;
            }
        }
        //走到这里说明已经是全部999999........的情况了
        digits = new int[digits.length+1];
        digits[0] = 1;
        return digits;
    }

    //第三大的数
    public static int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            if (count == 2) {
                if(nums[i] < nums[i+1]){
                    return nums[i];
                }
            } else {
                if (i > 0 && nums[i - 1] < nums[i]) {
                    count++;
                }
            }
        }
        return nums[nums.length-1];
    }
    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6};
        int[] B = new int[A.length+1];
        System.arraycopy(A,0,B,1,A.length);
        for(int i:B){
            System.out.println(i);
        }
    }
}
