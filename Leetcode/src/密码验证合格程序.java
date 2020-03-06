import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class 密码验证合格程序 {
        //校验方法
        public static String isSuccess(String password){
            //1.第一关，长度必须大于8
            if(password == null || password.length() <= 8){
                return "NG";
            }
            //2.第二关，需要包括至少三种类型的字母
            int count = 0;
            boolean number = true;
            boolean little = true;
            boolean big = true;
            boolean others = true;
            //2.1思路：遍历字符串出现一种字符计数+1
            for(int i = 0;i < password.length();i++){
                char c = password.charAt(i);
                if(true == number && c>='0'&&c<='9'){
                    count++;
                    number = false;  //含有数字了所以后面就不进行判断了，设为false
                }else if(true == little && c >= 'a' && c <= 'z'){
                    count++;
                    little = false;
                }else if(true == big && c >= 'A' && c <= 'Z'){
                    count++;
                    big = false;
                }else{
                    //说明是其他特殊字符
                    if(true == others){
                        count++;
                        others = false;
                    }
                }
                //提高效率,当count的值大于等于3以后就不需要判断了，直接退出即可
                if(count >= 3){
                    break;
                }
            }

            //如果count<3直接返回NG
            if(count < 3){
                return "NG";
            }

            //3.第三关，思路：超过2长度可以重复，那么低于2可以重复我们截取字符串进行添加Map集合就截取长度3的
            //因为4个重复的话那么3个必然重复
            //利用Map集合判断
            Map<String,String> map = new HashMap<String,String>();
            for(int i = 0;i < password.length()-3;i++){
                //其实这算暴力法
                String s = password.substring(i,i+3);
                if(map.containsKey(s)){
                    //包含说明重复了，
                    return "NG";
                }else{
                    //不包含
                    map.put(s,null);  //我们不需要value，所以加个null就好了
                }
            }

            //走到这里也是不容易，说明通过了
            return "OK";
        }
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            while(in.hasNextLine()){
                String password = in.nextLine();
                System.out.println(isSuccess(password));
            }
        }
}
