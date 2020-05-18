import java.util.Scanner;

public class 古诗文排 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            String n = in.nextLine();   //题中的N就是古风排序的行数
            String str = in.nextLine();
            int N = Integer.valueOf(n);
            int len = str.length();  //字符串长度
            if (len % N != 0)   //若字符串古风排序后不是矩形，则把字符串补空格
            {
                int temp = N - len % N;
                for (int i = 0; i < temp; i++) {
                    str += " ";
                }
                len = str.length();   //更新字符串长度
            }
            String[] s = new String[N];
            for (int i = 0; i < len; i++) {
                int temp = i % N;
                s[temp] = str.charAt(i) + s[temp];
            }
            for (int i = 0; i < s.length; i++) System.out.println(s[i].replace("null", ""));
        }
    }
}
