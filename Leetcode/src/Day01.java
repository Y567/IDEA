import java.util.ArrayList;
import java.util.Arrays;

public class Day01 {
    public static String toLowerCase(String str) {
        String[] words = {"a","b","c","d","e","f","g","h","i","g","k","l","m","n","o","p","q","r",
        "s","t","u","v","w","x","y","z"};
        if(str == null || str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for(char c:chars){
            if('a' <= c && c <= 'z'){
                sb.append(c);
            }else if('A' <= c && c <= 'Z'){
                sb.append(words[c%'A']);
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }


    //转移数组元素
    public static void rotate(int[] nums, int k) {
        int index = k % nums.length;
        if(index==0){
            return;
        }
        int[] copy = new int[nums.length];
        int beginCopy = nums.length - index;
        for(int i = 0;i < copy.length;i++){
            copy[i] = nums[beginCopy];
            beginCopy = (beginCopy + 1) % nums.length;
        }
        for(int i = 0;i < nums.length;i++){
            nums[i] = copy[i];
        }
    }

    public static void main(String[] args) {
        /*System.out.println(toLowerCase("PiTAs"));*/

        int[] i = {1,2,3,4,5,6,7};
        rotate(i,2);
        for(int t:i){
            System.out.println(t);
        }

    }
}
