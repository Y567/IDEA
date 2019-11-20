import java.util.Scanner;

public class Day12 {
    //格雷码
    public static String[] getGray(int n) {
        // write code here
/*        if(n==1){
            String[] s = {"0","1"};
            return s;
        }
        if(n==2){
            String[] s = {"0","1","0"};
            return s;
        }*/
        String[] result = new String[n + 1];
        for (int i = 0; i < result.length; i++) {
            if (i % 2 == 1) {
                result[i] = "1";
            } else {
                result[i] = "0";
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //测试
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        String[] s = getGray(i);
        for (String s1 : s) {
            System.out.println(s1);
        }
    }
}
