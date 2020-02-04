import java.util.*;

public class Day11 {
    public static boolean isPalindrome(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        Stack<Character> stack = new Stack<Character>();
        char[] chars = s.toCharArray();
        for(char c: chars){
            if((c>= '0' && c<= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
                stack.push(c);
            }
        }

        for(char c: chars){
            if((c>= '0' && c<= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
                char t = stack.pop();
                if(!(String.valueOf(t).equalsIgnoreCase(String.valueOf(c)))) {
                    return false;
                }
            }
        }
        return true;
    }


    //压缩字符串
    public static int compress(char[] chars) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        int left = 0;
        int right = 0;
        while(right < chars.length){
            while(right < chars.length && chars[left] == chars[right]){
                right++;
                count++;
            }
            //这里需要加上等号的原因是因为要append上最后的数据，当right指针超出范围时要处理最后一组字符
            if(right <= chars.length){
                //说明遇到不等的情况了
                sb.append(chars[left]);
                if(count > 1){
                    sb.append(count);
                }
                //记录归零
                count = 0;
                //将left移到下一组字符的开头处，即right所在的位置
                left = right;
            }
        }
        char[] re = sb.toString().toCharArray();
        //将re赋给原数组的，re的长度就是最后的结果
        System.arraycopy(re, 0, chars, 0, re.length);
        return re.length;
    }

    public static void main(String[] args) {
//        System.out.println(isPalindrome("`l;`` 1o1 ??;l`"));


    }
}
