import java.util.*;

public class Day12 {
    //最短无序的子数组
    public static int findUnsortedSubarray(int[] nums) {
        int[] copy = new int[nums.length];
        System.arraycopy(nums,0,copy,0,nums.length);
        Arrays.sort(copy);
        int start = -1;
        int end = -1;
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            while(left <= right && nums[left] == copy[left]){
                left++;
            }
            if(left <= right){
                //找到了左边不等的情况的下标
                if(start == -1){
                    start = left;
                }
            }
            while(left <= right && nums[right] == copy[right]){
                right--;
            }
            if(left <= right){
                //找到了右边不等的情况
                if(end == -1){
                    end = right;
                }
            }
            left++;
            right--;
        }
        if(start == -1 && end == -1){
            return 0;
        }else{
            return end - start + 1;
        }
    }

    //求逆波兰表达式（后缀表达式）的值
    public static int evalRPN(String[] tokens) {
        Stack<Integer> numbers = new Stack<Integer>();
//        Stack<Character> opers = new Stack<Character>();
        for (int i = 0; i < tokens.length; i++) {
            String c = tokens[i];
            int first = 0;
            int second = 0;
            switch (c) {
                case "+":
                    first = numbers.pop();
                    second = numbers.pop();
                    numbers.push(second + first);
                    break;
                case "-":
                    first = numbers.pop();
                    second = numbers.pop();
                    numbers.push(second - first);
                    break;
                case "*":
                    first = numbers.pop();
                    second = numbers.pop();
                    numbers.push(second * first);
                    break;
                case "/":
                    first = numbers.pop();
                    second = numbers.pop();
                    numbers.push(second / first);
                    break;
                default:
                    //说明是数字
                    numbers.push(Integer.valueOf(c));
                    break;
            }
        }
        return numbers.pop();
    }




    public static void main(String[] args) {
//        double rad = Math.cos(30);
//        System.out.println(Integer.valueOf('9'-'0'));
//        System.out.println(rad);

    }
}
