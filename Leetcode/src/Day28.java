import java.util.Scanner;

public class Day28{
    //判断是否是守形数
    public static String isNumberOfModel(int n){
        //先将其转换为字符串保存
        String str1 = String.valueOf(n);
        //将它的平方也保存为字符串保存
        String str2 = String.valueOf(n*n);
        //判断str2的尾部是否和str1的相同
        if(str1.equals(str2.substring(str2.length()-str1.length()))){
            return "Yes!";
        }else{
            return "No!";
        }
    }
    public static void main(String[] args){
        //输入流
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt();
            System.out.println(isNumberOfModel(n));
        }
    }
}