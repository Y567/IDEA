import java.util.*;

public class 学分绩点{
    //求总平均绩点
    public static double toGPA(int[] a,int[] b,int n){
        //a表示学分，b表示相应的得分,n表示是课程数
        //我们要求出该学生的所有学科绩点之和
        double sumPoint = 0.0;
        int sumA = 0;      //学分之和
        for(int i = 0;i < n;i++){
            if(b[i] < 60){
                sumPoint = sumPoint + (0.0*a[i]);
            }else if(b[i] < 64){
                sumPoint = sumPoint + (1.0*a[i]);
            }else if(b[i] < 68){
                sumPoint = sumPoint + (1.5*a[i]);
            }else if(b[i] < 72){
                sumPoint = sumPoint + (2.0*a[i]);
            }else if(b[i] < 75){
                sumPoint = sumPoint + (2.3*a[i]);
            }else if(b[i] < 78){
                sumPoint = sumPoint + (2.7*a[i]);
            }else if(b[i] < 82){
                sumPoint = sumPoint + (3.0*a[i]);
            }else if(b[i] < 85){
                sumPoint = sumPoint + (3.3*a[i]);
            }else if(b[i] < 90){
                sumPoint = sumPoint + (3.7*a[i]);
            }else{
                sumPoint = sumPoint + (4.0*a[i]);
            }
            sumA += a[i];
        }
        //求出答案返回
        return sumPoint/sumA;
    }
    public static void main(String[] args){
        //获取数据
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i = 0;i < n;i++){
            a[i] = in.nextInt();
        }
        for(int i = 0;i < n;i++){
            b[i] = in.nextInt();
        }
        //传入函数
        double GPA = toGPA(a,b,n);
        System.out.printf("%.2f",GPA);
    }
}