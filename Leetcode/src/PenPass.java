import java.util.Scanner;

public class PenPass {

    /**
     * 第一道输出最长的连续数字串
     */
    public static String print(String s){
        String sb = "";
        int count = 0;
        for(int i = 0;i < s.length();i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                //遇到第一个数字
                int begin = i;
                int end = 0;
                char c = s.charAt(i);
                while(c >= '0' && c <= '9'){
                    end = i;
                    count++;
                    i++;
                    if(i >= s.length()){
                        break;
                    }else{
                        c = s.charAt(i);
                    }
                }
                //出来的话就是记录完毕了
                if(count > sb.length()){
                    sb = s.substring(begin,end+1);
                }
                //重新清零
                count = 0;
            }
        }
        return sb;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(print("abcd12345ed125ss123456789"));
    }
}
