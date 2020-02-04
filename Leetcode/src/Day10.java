import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {
    //转换字符串为int型数字

    /**
     * ^[\\+\\-]?\\d+
     *
     * ^ 表示匹配字符串开头，我们匹配的就是 '+'  '-'  号
     *
     * [] 表示匹配包含的任一字符，比如[0-9]就是匹配数字字符 0 - 9 中的一个
     *
     * ? 表示前面一个字符出现零次或者一次，这里用 ? 是因为 '+' 号可以省略
     *
     *  \\d 表示一个数字 0 - 9 范围
     *
     * + 表示前面一个字符出现一次或者多次，\\d+ 合一起就能匹配一连串数字了
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        //去掉开头的空字符
        String trim = str.trim();
        //创建正则表达式
        Pattern pattern = Pattern.compile("^[\\+\\-]?\\d+");
        Matcher matcher = pattern.matcher(trim);
        int value = 0;
        if(matcher.find()){
            //匹配成功则执行
            try{
                value = Integer.valueOf(trim.substring(matcher.start(),matcher.end()));
            }catch (Exception e){
                //走到这里说明超范围不能转换的异常.因为有的正数不带+所以判断有-
                value =  trim.charAt(0)=='-'?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
        }
        return value;
    }

    //给定一个升序的数组找出给定目标值再数组的开始位置和结束位置
    public static int[] searchRange(int[] nums, int target) {
        int[] value = new int[]{-1, -1};
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            while(left <= right && nums[left] < target){
                left++;
            }
            if(left <= right && nums[left] == target){
                if(-1 == value[0]){
                    value[0] = left;
                }
            }
            while(left <= right && nums[right] > target){
                right--;
            }
            if(left <= right && nums[right] == target){
                if(-1 == value[1]){
                    value[1] = right;
                }
            }
            left++;
            right--;
        }
        return value;
    }
    public static void main(String[] args) {
        myAtoi("usdbcdshcb182361783sdhcdsucdsb");
        int[] i = new int[]{5,7,7,8,8,10};
        int[] ints = searchRange(i, 8);
        for(int ii:ints){
            System.out.println(ii);
        }

    }
}
