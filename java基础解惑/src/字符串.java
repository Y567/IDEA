public class 字符串 {
    public static void main(String[] args) {
        String s = "abcdefgh";
        String str = "bcd";
        if(s.contains(str)){
            int i = s.indexOf(str);
            System.out.println(i);
        }
    }
}
