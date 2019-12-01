import java.util.Scanner;

public class Day23 {
    public static String[] test(String s){
        String[] s1 = s.split(" ");
        int A1 = 0;
        int A2 = 0;
        int A3 = 0;
        double A4;
        int sum = 0; //这是A4用到的
        int count = 0; //这是A4用到的
        int A5 = 0;
        for (int i = 1,j = 1; i < s1.length; i++) {
            int atom = Integer.parseInt(s1[i]);
            int select = atom%5;
            switch (select){
                case 0:
                    if(atom%2==0){
                        A1+=atom;
                    }
                    break;
                case 1:
                    A2 += j*atom;
                    j=-j;    //控制正负号
                    break;
                case 2:
                    A3++;
                    break;
                case 3:
                    sum+=atom;  //和
                    count++;   //个数
                    break;
                case 4:
                    if(A5<atom){
                        A5=atom;
                    }
                    break;
                default:
                    break;
            }
        }
        String[] res = new String[5];
        if(A1==0){
            res[0]="N";
        }else{
            res[0] = String.valueOf(A1);
        }
        if(A2==0){
            res[1]="N";
        }else{
            res[1] = String.valueOf(A2);
        }
        if(A3==0){
            res[2]="N";
        }else{
            res[2] = String.valueOf(A3);
        }
        if(count==0){
            res[3]="N";
        }else{
            A4 = sum*1.0/count;
            res[3] = String.format("%.1f",A4);
        }
        if(A5==0){
            res[4]="N";
        }else{
            res[4] = String.valueOf(A5);
        }
        return res;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] res = test(s);
        for (int i = 0; i < res.length; i++) {
            if(i==res.length-1){
                System.out.print(res[i]);
            }else{
                System.out.print(res[i]+" ");
            }
        }
    }
}
