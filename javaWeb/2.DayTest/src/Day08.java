import java.math.BigInteger;

public class Day08 {

    //那个排序输出前K
    //求出删除的节点的最后一个原始下标

    //进制转换
    private static char[] array = "0123456789ABCDEF"
            .toCharArray();

    public static String myDec(int number, int n) {
        // String 是不可变的，每次改变都要新建一个Strng，很浪费时间。
        // StringBuilder是‘可变的String’ ,
        StringBuilder result = new StringBuilder();
        // 模拟计算进制的过程
        while (number > 0) {
            result.insert(0, array[number % n]);
            number /= n;
        }
        return result.toString();
    }


    //杨辉三角
    public static void printTriangle(int row) {
        // 在循环中依次保存当前行上一行每个元素的值
        int prev = 1;
        int[] arr = new int[row];
        for( int i = 0; i < row; i++) {
            for( int j = 0; j <= i; j++) {
                // 将上一行当前下标元素的值保存
                int cur = arr[j];
                // 当前元素等于上一行当前元素和上一行当前元素前一个元素之和
                arr[j] = prev+cur;
                // 对于下一圈循环，prev保存了当前行上一行元素前一个元素，即prev与cur保存的值一前一后
                prev = cur;
                System.out.print(arr[j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
/*        BigInteger bigInteger;
        //小数据转换为大数据
        bigInteger = BigInteger.valueOf(123456787);
        BigInteger test = bigInteger.gcd(BigInteger.valueOf(1232411));
        int i = Integer.parseInt(test.toString(10));
        System.out.println(i);

        //转换为对应的进制
        String s = bigInteger.toString(16);
        System.out.println(s);*//*



        BigInteger a = new BigInteger("123456789");
        BigInteger b = new BigInteger("223456789");

        BigInteger gcd = a.remainder(b);
        String s = gcd.toString();
        System.out.println(s);

        String result = myDec(1998,8);
        System.out.println(result);

        printTriangle(10);
        String string = "sdwvs";
        System.out.println();
        String replace = string.replace("sd", "wy");
        System.out.println(replace);
        StringBuffer sb = new StringBuffer("1234567");*/
//        double random = Math.random()*1000;
////        System.out.println(random);

        String s = "d:/sadaf/dswwe/afew/efew/Y.java";
        int i = s.lastIndexOf("/");
        System.out.println(i);

    }
}
