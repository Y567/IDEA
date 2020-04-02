package NowCoder;

import java.util.ArrayList;
import java.util.Scanner;

public class 倒转句子 {
    //寻找单词的方法
    public static ArrayList findWords(String words){
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        int j = 0;
        while(i < words.length() && j < words.length()){
            while(i < words.length() && !('A'<=words.charAt(i)&& words.charAt(i)<='Z' || 'a'<= words.charAt(i) && words.charAt(i)<='z')){
                i++;
            }
            j = i;//j和i是一起的
            //开始找单词
            if(j >= words.length()){
                break;
            }
            char c = words.charAt(j);
            while(('A'<=c && c <= 'Z' || 'a' <= c && c <= 'z')){
                j++;
                if(j >= words.length()){
                    break;
                }
                c = words.charAt(j);
            }
            //走出来有两种情况，一种是遇到分割符了，一种是j到头了
            if(!(j > words.length())){
                //找到一个单词
                list.add(words.substring(i,j));
                //改变i，j的值
                i = j;
            }
        }
        return list;
    }
    //实现反转单词的方法
    public static String reverseWords(String words){
        //遍历一遍栈，取出元素拼接成字符串
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = findWords(words);
        for(int i = 0;i < list.size();i++){
            if(i == list.size()-1){
                sb.insert(0,list.get(i));
            }else{
                sb.insert(0,list.get(i));
                sb.insert(0," ");
            }
        }
        return sb.toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println(reverseWords(sc.nextLine()));
    }
}
