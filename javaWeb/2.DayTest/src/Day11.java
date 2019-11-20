import java.util.*;

public class Day11 {
    //求出个位数
    public static Map cal(String s){
        String[] split = s.split("");
        Map<Integer,Integer> map = new TreeMap<>();
        for (String s1 : split) {
            int i = Integer.parseInt(s1);
            map.put(i,map.getOrDefault(i,0)+1);
        }
        return map;
    }


    //什么是构建数组
    public static int[] multiply(int[] A){
        int[] B = new int[A.length];
        int mul = 1;
        for (int i : A) {
            mul = mul*i;
        }
        for(int i = 0;i < B.length;i++){
            if(i==0){
                B[i] = mul*A[i+1];

            }else if(i==B.length-1){
                B[i] = mul*A[i-1];
            }else{
                B[i] = mul*A[i-1]*A[i+1];
            }
        }

        return B;
    }

    public static void main(String[] args) {
/*        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Map cal = cal(s);
        for (Object o : cal.keySet()) {
            int i = (int)o;
            System.out.println(i+":"+cal.get(i));
        }*/
       int[] A={1,2,3,4,5,6,7,8};
        int[] b = multiply(A);
        for (int i : b) {
            System.out.println(i);
        }
    }
}
