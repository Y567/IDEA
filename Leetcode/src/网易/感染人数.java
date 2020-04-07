package 网易;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class 感染人数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();   //城市的人数
            HashSet<Integer> set = new HashSet<>(n); //存储携带病毒的人,最多也就城市的人数了
            int m = sc.nextInt();   //家庭聚会的次数
            int f = sc.nextInt();   //初始携带者
            set.add(f);             //将初号病毒携带者放入集合
            for (int i = 0; i < m; i++) {
                int first = sc.nextInt();  //聚会有几个人
                int[] arr = new int[first]; //存储参加聚会的人的编号
                for (int j = 0; j < first; j++) {
                    arr[j] = sc.nextInt();
                }
                //走到这里说明参加聚会的人已确定
                for (int j = 0; j < arr.length; j++) {
                    //遍历看这些人中有没有携带病毒的，只要有一个，全员加入集合（都中毒了）
                    if(set.contains(arr[j])){
                        //集合中有人来参加聚会了（害人）
                        for (int k = 0; k < arr.length; k++) {
                            set.add(arr[k]);
                        }
                        break; //不需要遍历了，全感染了
                    }
                }
            }
            //这里走下来就是读取键盘输入流结束了
            System.out.println(set.size());  //这就是感染人数
        }
    }
}
