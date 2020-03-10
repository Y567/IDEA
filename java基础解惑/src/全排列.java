import java.util.Arrays;

public class 全排列 {
    public static void main(String[] args) {
        String str = "xafdvs";
        char[] arr1 = str.toCharArray();
        char[] arr2 = Arrays.copyOf(arr1,arr1.length);
        for(int i=0;i<arr1.length-1;i++)
        {
            for(int j = i+1;j<arr2.length;j++){
                System.out.println(arr1[i] + "," + arr2[j]);
            }
        }
    }
}
