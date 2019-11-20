import java.util.LinkedList;
import java.util.Scanner;

public class Day07 {
    //第一题
    public static int total(int[][] a,int row,int col){

        return 0;
    }

    //第二题
    //求出最小公倍数并返回
    public static int min(int a,int b){
        int max = a>b?a:b;
        int min = a<b?a:b;
        int test = max;
        while(test%min!=0||test%max!=0){
            test+=max;
        }
        return test;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        String s = in.nextLine();
//        String[] s1 = s.split(" ");
//        int[][] a = new int[Integer.parseInt(s1[0])+4][Integer.parseInt(s1[1])+4];
//        int count=0;
//        for(int i = 2;i < a.length-2;i++){
//            for(int j = 2;j < a[i].length-2;j++){
//                if(a[i+2][j]==0&&a[i][j+2]==0&&a[i][j-2]==0&&a[i-2][j]==0){
//                    count++;
//                    a[i][j]=1;
//                }
//            }
//        }
//        System.out.println(count);
/*
        int a = in.nextInt();
        int b = in.nextInt();
        int result = min(a,b);
        System.out.println(result);*/

        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        int num = in.nextInt();
        num = num>1000?1000:num;
        for(int i = 0;i<num;i++){
            linkedList.add(i);
        }
        int deleteCount = 0;
        int index = 0;
        while(deleteCount<num-1){
            index=(index+2)%linkedList.size();
            linkedList.remove(index);
            deleteCount++;
        }
        Integer result = linkedList.get(0);
        System.out.println(result);
    }
}
