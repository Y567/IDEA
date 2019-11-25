import java.util.*;

public class Day14 {
    //发现数学规律哎虚浮
    public static String GetSequeOddNum(int m){
        int[] result = new int[m];
        if((m*m)%2==1){
            int count = 1;
            for(int i = 0;i < m-1;count++){
                result[i++]=m*m-2*count;
                result[i++]=m*m+2*count;
            }
            result[m-1]=m*m;
        }else{
            int count = 0;
            for(int i = 0;i < m;count++){
                result[i++]=(m*m-1)-2*count;
                result[i++]=(m*m+1)+2*count;
            }
        }
        Arrays.sort(result);
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < result.length;i++){
            if(i==result.length-1){
                sb.append(result[i]);
            }else{
                sb.append(result[i]).append("+");
            }
        }
        return sb.toString();
    }
    public static void main(String[] args){
/*        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int m = in.nextInt();
            System.out.println(GetSequeOddNum(m));
        }*/

/*
        Queue<Integer> que = new LinkedList<>();
        que.add(1);
        que.add(2);
        que.add(3);
        System.out.println(que.remove());
        System.out.println(que.remove());
*/

        for(int i = 0;i < 1000000000;i++){
            if((i^2)==i){
                System.out.println(i);
            }
        }
        System.out.println();
    }
}
