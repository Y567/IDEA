import java.util.Arrays;
import java.util.Stack;

public class Day06 {
    //返回数组的平方
    public static int[] sortedSquares(int[] A) {
        int[] B = new int[A.length];
        for(int i = 0;i < B.length;i++){
            B[i] = A[i] * A[i];
        }
        Arrays.sort(B);
        return B;
    }

    public static String reverseOnlyLetters(String S) {
        Stack<Character> stack = new Stack<Character>();
        StringBuffer sb = new StringBuffer();
        char[] chars = S.toCharArray();
        for(int i = 0; i < S.length();i++){
            char c = chars[i];
            if(('a'<=c && c <='z') || (c>='A' && c <= 'Z')){
                stack.push(c);
            }
        }
        for(int i = 0; i < S.length();i++){
            char c = chars[i];
            if(('a'<=c && c <='z') || (c>='A' && c <= 'Z')){
                sb.append(stack.pop());
            }else{
                sb.append(c);
            }
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(reverseOnlyLetters("ab-cd"));
    }
}
