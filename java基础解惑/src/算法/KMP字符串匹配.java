package 算法;

import java.io.InputStream;
import java.util.Scanner;

public class KMP字符串匹配 {

    /**
     * 获取字符串匹配表的方法
     * @param sonString
     */
    public static int[] getNext(String sonString){
        //这个数组的长度是和子字符串一样长的，从0开始记录字符串的匹配值
        int[] next = new int[sonString.length()];
        //首先如果字符串长度为1那么必然匹配值为0，比如A 它的前缀和后缀都为空所以为0咯
        next[0] = 0;

        for(int i = 1,j = 0; i < sonString.length();i++){
            //如果遇到不等的情况，那么就一直向后移动,这个while循环是核心
            while(j > 0 && sonString.charAt(i) != sonString.charAt(j)){
                //不相等，那么j要向前回溯，找到相等的情况，然后退出循环执行下面的if语句，这也是为什么先写下面的
                //将循环放在if语句之上
                j = next[j-1];  //这里请回想印度小哥的视频,为什么要回溯？即使没找到，那么退出来也是j的值也是0，走到下面匹配值会置为0
            }

            if(sonString.charAt(i) == sonString.charAt(j)){
                //说明遇到相同的了，那么匹配值就是j的值加1，比如j==0,此时当前字符到开头这一段字符串的的匹配值为1
                j++;
            }

            //注意j的移动次数一般比i小,因为它是记录开头的下标
            next[i] = j;
        }
        return next;
    }


    //匹配算法
    public static int kmpSearch(String fatherString,String sonString){
        //我们先获取到字符串匹配表
        int[] next = getNext(sonString);

        //i表示的是父字符串的下标,j表示的是字串的下标
        for(int i = 0,j = 0;i < fatherString.length() && j < sonString.length();i++){

            while(j > 0 && fatherString.charAt(i) != sonString.charAt(j)){
                //这时候就要用到字符串匹配表了，修改子串的指针。因为匹配值表示的是相同且最长的前后缀
                //那么我们就可以将子串移动到后缀的位置，这时候从后缀的下一个字符开始比较.下面的操作相等于移动子串
                j = next[j-1];
            }

            if(fatherString.charAt(i) == sonString.charAt(j)){
                if(j == sonString.length() - 1){
                    //已经走到了最后,而且相等
                    return i - j;  //（i所处下标减去j所处下标，j其实就是子串长度）==其实相当于将i向前移动到子串和父串刚匹配的字符的位置。答案
                }
                //子串向后移动
                j++;
            }
        }
        return -1;  //没找到
    }
    public static void main(String[] args) {
        String str1 = "aaaaabaab";
        String str2 = "aaab";

        System.out.println(kmpSearch(str1,str2));

    }
}
