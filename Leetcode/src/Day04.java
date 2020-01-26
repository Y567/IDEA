import java.util.Arrays;

public class Day04 {
    //输出最后一个单词
    public static int lengthOfLastWord(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        String[] ss = s.split(" ");
        if(ss == null || ss.length == 0){
            return 0;
        }
        return ss[ss.length-1].length();
    }

    //合并两个有序数组
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i = 0;i < n;i++){
            nums1[m++] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("scnddeubw dsjcbds saducbds"));
    }
}
