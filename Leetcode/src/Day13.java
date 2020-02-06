import java.util.ArrayList;
import java.util.List;

public class Day13 {
    //子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        for(int i = 0;i < Math.pow(2,nums.length);i++){
            StringBuffer binary = new StringBuffer(Integer.toBinaryString(i));
            int len = binary.length();
            if(len < nums.length){
                //将二进制数补充到与nums的长度相同
                for(int t = 1; t <= (nums.length - len);t++){
                    binary.insert(0,"0");
                }
            }
            System.out.println(binary);
            //开始遍历二进制字符串来添加元素到list集合中
            List<Integer> list = new ArrayList<>();
            for(int j = 0;j < binary.length();j++){
                if(binary.charAt(j) == '1'){
                    list.add(nums[j]);
                }
            }
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {
        int[] i = {1,2,3};
        System.out.println(subsets(i));
    }
}
