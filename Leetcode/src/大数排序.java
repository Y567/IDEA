import java.util.TreeMap;
import java.util.Set;
import java.util.Scanner;
//思路：实现Comparable接口自定义一个比较方法，然后利用TreeMap加入自动排序，最后我们获取出Map的keys即可
class BigNumber implements Comparable<BigNumber> {

    private String s;

    public BigNumber(String s){
        this.s = s;
    }

    public int compareTo(BigNumber number){
        //1.先按长度比较
        if(s.length() < number.s.length()){
            return -1;
        }else if(s.length() > number.s.length()){
            return 1;
        }else{
            //长度相同，接下来我们得好好比较了
            //思虑：从头开始遍历字符串，模拟字符串的比较规则
            return s.compareTo(number.s);
        }
    }

    public String getS(){
        return s;
    }
}
public class 大数排序 {
    public static void main(String[] args){
        //输入流
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String n = in.nextLine();  //行数,为了避免nextInt()和nextLine()全按字符串处理
            //我们定义一个Map
            TreeMap<BigNumber, String> map = new TreeMap<BigNumber, String>();
            int len = Integer.parseInt(n);
            //输入数字
            for (int i = 0; i < len; i++) {
                String s = in.nextLine();
                map.put(new BigNumber(s), null);
            }

            //添加完毕,获取map集合的keys并输出
            Set<BigNumber> keys = map.keySet();
            for (BigNumber key : keys) {
                System.out.println(key.getS());
            }
        }
    }
}