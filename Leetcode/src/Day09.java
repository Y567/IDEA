
import java.util.HashMap;
import java.util.Map;

public class Day09 {
    //求一个数组中的两个数和为指定值的下标
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target-nums[i]),i};
            }
            map.put(nums[i],i);    //将其加入到map集合
        }
        return null;   //走到这里说明没有找到
    }


    //返回两个二进制串相加后的二进制字符串
    public static String addBinary(String a, String b) {
        StringBuffer aa = new StringBuffer(a);
        StringBuffer bb = new StringBuffer(b);
        StringBuffer re = new StringBuffer();
        int x = 0;
        for (int i = aa.length()-1, j = bb.length()-1; i >= 0 && j >= 0; i--, j--) {
            int s = (aa.charAt(i) - '0') + (bb.charAt(j) - '0') + x;
            if (s > 1) {
                if(s > 2){    //存在1+1+进位1的情况
                    re.insert(0,1);
                }else{
                    re.insert(0, 0);
                }
                x = 1;   //将进位置为一
            } else{
                re.insert(0,s);
                x = 0;   //将进位置为0
            }
        }

        if(re.length() == aa.length()){
            //继续和bb相加，因为aa已经算完了
            for(int i = bb.length() - 1 - re.length();i >= 0;i--){
                if(x == 1){
                    int s = (bb.charAt(i) - '0') + x;  //加上进位的值
                    if(s > 1){    //这里最多1+1
                        re.insert(0,0);
                        x = 1;
                    }else{
                        re.insert(0, s);
                        re.insert(0,bb.substring(0,i));
                        x = 0;// 没有进位了
                        break;
                    }
                }else{
                    re.insert(0,bb.substring(0,i+1));  //直接全部加上
                    //没有了进位
                    x = 0;
                    break;
                }
            }
        }else{
            //继续和aa相加，因为bb完了
            for(int i = aa.length() - 1 - re.length();i >= 0;i--){
                if(x == 1){
                    int s = (aa.charAt(i) - '0') + x;  //加上进位的值
                    if(s > 1){    //这里最多1+1
                        re.insert(0,0);
                        x = 1;
                    }else{
                        re.insert(0, s);
                        re.insert(0,aa.substring(0,i));
                        x = 0;// 没有进位了
                        break;
                    }
                }else{
                    re.insert(0,aa.substring(0,i+1));  //直接把剩余的全部加上
                    //没有了进位
                    x = 0;
                    break;
                }
            }
        }
        if(x == 1){
            re.insert(0,1);
        }
        return re.toString();
    }
    public static void main(String[] args) {
//        System.out.println('1' - '0' + '0' - '0' + 0);
        String s = addBinary("110010", "100");
        System.out.println(s);
    }
}
