import java.util.ArrayList;
import java.util.Arrays;

public class PenPass2 {
    /**
     * 输出前K个最小的数字
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        if(input == null || k > input.length){
            return new ArrayList<>();
        }
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(input);
        for(int i = 0;i < k;i++){
            list.add(input[i]);
        }
        return list;
    }
    public static void main(String[] args) {
        int[] input = {4,5,1,6,2,7,3,8};
        System.out.println(GetLeastNumbers_Solution(input,10));
    }
}
