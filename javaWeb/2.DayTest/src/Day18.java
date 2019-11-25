import java.util.Scanner;

public class Day18 {

    //第一题方法
    public static String subStringOfDNA(String s,int l){
        String res = "";
        int total = 0;
        for(int i = 0;i < s.length()-l+1;i++){
//            System.out.println("索引的值："+i);
            String t = s.substring(i,i+l);
            String[] split = t.split("");
            int b = 0;
            for(int j = 0;j < split.length;j++){
                if(split[j].equals("C") || split[j].equals("G")){
                    b++;
                }
            }
            if(b > total){
                total = b;
                res=t;
            }
        }
        return res;
    }

    //算钱
    public static void calMoney(){
        long rich = 0;
        int unknown = 300;
        for (int i = 0; i < 30; i++) {
            rich = rich + (long)Math.pow(2,i);
        }
        System.out.println(unknown+" "+rich);
    }
    public static void main(String[] args) {
/*        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            int l = in.nextInt();
            String res = subStringOfDNA(s,l);
            System.out.println(res);
        }*/
        calMoney();
    }
}
