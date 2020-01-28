import java.util.ArrayList;
import java.util.Arrays;

public class Day05 {
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0;i < nums.length - 1;i++){
            if(nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;

    }

    //长按键入
    public static boolean isLongPressedName(String name, String typed) {
        for(int i = 0;i < name.length()-1;i++){
            if(!typed.contains(name.substring(i,i+2))){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isLongPressedName("fanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuqkwamvdsi",
                "faanyrqqqmttggxxhyyyycclttllnusyeyqqyggwuuppccaaaggtkkuuqkwwamvvddsii"));
    }
}
