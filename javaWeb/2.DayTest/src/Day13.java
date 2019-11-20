import java.math.BigInteger;
import java.util.Scanner;

public class Day13 {
    //绘制正方形
    public static void print(int a,String s){
        int begin = 1;
        for(int i = 1;i < Math.round(a/2.0);i++){
            if(begin == 1){
                for(int j = 1;j <= a;j++){
                    System.out.print(s);
                }
                System.out.println();
                begin++;
            }
            if(i!=Math.round(a/2.0)-1){
                System.out.print(s);
                for(int j = 1;j <= a-2;j++){
                    System.out.print(" ");
                }
                System.out.print(s);
            }else{
                for(int j = 1;j <= a;j++){
                    System.out.print(s);
                }
            }
            System.out.println();

        }
    }

/*    //大数相加
    public static String AddLongInteger(String addend,String augend){
        String[] s1 = addend.split("");
        String[] s2 = augend.split("");
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        int count = s1.length < s2.length?s1.length:s2.length;
        for(int i = count-1;i>=0;i++){
           int j = Integer.parseInt(s1[i])+Integer.parseInt(s2[i])+temp;
           if(j>9){
               temp = 1;
               String in = String.valueOf(j-10);
               sb.insert(0,in);
           }else{
               temp = 0;
               String in = String.valueOf(j);
               sb.insert(0,in);
           }
        }
        if(s1.length>s2.length){
            if(temp==1){

            }else{

            }
        }else{
            if(temp==1){

            }else{

            }
        }
    }*/
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
/*        String[] s = a.split(" ");
        print(Integer.parseInt(s[0]),s[1]);*/

        String b = in.nextLine();
/*        String s = AddLongInteger(a,b);*/
/*        System.out.println(s);*/
    }
}
