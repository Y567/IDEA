import java.util.Scanner;

public class 字符串转换 {
    /**
     * 转换字符串,单纯用来转换
     */
    public static String moveToTail(String s,int index){
        StringBuilder sb = new StringBuilder();
        char c = sb.charAt(index);
        sb.replace(index,index+1,"");
        sb.append(c);
        return sb.toString();
    }

    public static String fun(String s1,String s2){
        int count = 0;  //循环次数
        while(count < s1.length()){
            int i = 0;
            int j = 0;
            if(s1.charAt(i)!=s1.charAt(j)){
                moveToTail(s1,i);
            }
//            if(s1.equals(s2)){
//                return result;
//            }
            count++;
        }
        return s1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
        }
    }
}
