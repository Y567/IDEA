
import java.util.LinkedList;
import java.util.List;

public class 最长无重复子串 {

    //判断一个字符串中是否有重复元素
    public static boolean isUnqiue(String s){
        List<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!list.contains(c)){
                list.add(c);
            }else{
                return false;
            }
        }
        //是一个无重复元素的子字符串
        return true;
    }

    //解决问题方法
    public static String findLongestUnique(String s){
        //字符串的长度
        int n = s.length();
        String result = "";
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j <= n; j++) {
                String temp = s.substring(i,j);
                if(isUnqiue(temp)){
                    if(result.length() < temp.length()){
                        result = temp;
                    }
                }else{
                    //子字符串有重复，继续下一轮
                    break;
                }
            }
        }

        return result;

    }
    public static void main(String[] args) {
        String s = "pswwekd";
        System.out.println(findLongestUnique(s));
    }
}
