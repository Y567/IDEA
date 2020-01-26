import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class Day03 {
    //第一道题
    //赎金信
    public static boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> ransoms = new HashMap<>();
        HashMap<Character, Integer> magazines = new HashMap<>();
        for(int i = 0;i < ransomNote.length();i++){
            ransoms.put(ransomNote.charAt(i),ransoms.getOrDefault(ransomNote.charAt(i),0)+1);
            System.out.println(ransomNote.charAt(i));
        }

        for(int i = 0;i < magazine.length();i++){
            magazines.put(magazine.charAt(i),magazines.getOrDefault(magazine.charAt(i),0)+1);
        }


        //判断ransoms中的字符magazines里有没有，并且数量够不
        Set<Character> src = ransoms.keySet();
        for(char c:src){
            if(!magazines.containsKey(c) || magazines.get(c) < ransoms.get(c)){
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(int x) {
        Stack<Character> stack = new Stack<Character>();
        String s = String.valueOf(x);
        for(int i = 0;i < s.length();i++){
            stack.push(s.charAt(i));
        }

        for(int i = 0;i < s.length();i++){
            if(stack.pop() != s.charAt(i)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(canConstruct("aab","ab"));
    }
}
