package 每日一题;

public class 第一个单独字符 {

    //找出字符串第一个单独的字符，没有的话就输出-1
    public static String firstChar(String s){
        if(s == null || "".equals(s)){
            return "-1";
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //得到去掉当前位置的字符串
            StringBuilder sb = new StringBuilder(s);
            String test = sb.delete(i, i + 1).toString();
            if(!test.contains(String.valueOf(c))){
                //后续字符串不包含这个字符
                return String.valueOf(c);
            }else{
                //包含，如果到末尾了说明没有了
                if(i == s.length() - 1){
                    return "-1";
                }
            }
        }
        return "-1";
    }

    public static void main(String[] args) {

    }
}
