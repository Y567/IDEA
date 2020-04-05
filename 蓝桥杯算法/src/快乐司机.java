
import java.util.*;

public class 快乐司机 {
    static class Data implements Comparable<Data> {
        double gi,pi;    //重量和价值
        double value; //这是价值比

        public Data(double gi,double pi){
            this.gi = gi;
            this.pi = pi;
            this.value = pi * 1.0 / gi;
        }
        @Override
        public int compareTo(Data o) {
            if(this.value > o.value){
                return -1;  //故意写反实现倒排序
            }else if(this.value == o.value){
                return 0;
            }else{
                return 1;
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();   //物品个数
        double w = in.nextInt();   //车的承载量
        double value = 0;       //最后的结果

        Data[] datas = new Data[n];
        for (int i = 0; i < n; i++) {
            double gi = in.nextDouble();
            double pi = in.nextDouble();
            Data data = new Data(gi, pi);
            datas[i] = data;
        }
        Arrays.sort(datas);
        for (int i = 0; i < n; i++) {
            if(w <= 0){
                //装不下了就退出
                break;
            }else if(w >= datas[i].gi){
                w -= datas[i].gi;
                value += datas[i].pi;
            }else{
                //看看后面有没有可以装下的了
                continue;
            }
        }
        System.out.printf("%.1f",value);
    }
}
