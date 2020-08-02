package 每日一题;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class 找出数组种大于一半个数的数 {

    //找到大于一半长度的数字
    public static int search(int[] arr,int length){
        if(arr == null || length == 0){
            return -1;
        }
        //采取最简单的暴力吧，还有一种排序的思路，但是也得排序复杂度可能更高，暂时没想到其他
        Map<Integer, Integer> miao = new LinkedHashMap<>();
        for (int i = 0; i < length; i++) {
            miao.put(arr[i],miao.getOrDefault(arr[i],0)+1);
        }
        //统计完毕，遍历
        Set<Integer> keys = miao.keySet();
        for(Integer key : keys){
            if(miao.get(key) > length / 2){
                return key;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s_length = in.nextLine();
            String s_arr = in.nextLine();
            String[] s = s_arr.split(" ");
            int[] arr = new int[s.length];
            for (int i = 0; i < s.length; i++) {
                arr[i] = Integer.valueOf(s[i]);
            }
            int length = Integer.valueOf(s_length);
            System.out.println(search(arr,length));
        }
    }
}
