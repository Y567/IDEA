public class Day19 {
    //判断b是否为a的子串
    public static boolean subStr(String a,String b){
        if(b.length() > a.length()){
            return false;
        }
        for(int i = 0;i < a.length()-b.length()+1;i++){
            String subStr = a.substring(i,i+b.length());
            if(subStr.equals(b)){
                return true;
            }
        }
        return false;
    }
    public static boolean[] chkSubStr(String[] p, int n, String s) {
        // write code here
        boolean[] res = new boolean[n];
        for (int i = 0; i < n; i++) {
            res[i] = subStr(s,p[i]);
        }
        return res;
    }


    public static void main(String[] args) {
        String[] p = {"a","b","c","d"};
        boolean[] res = chkSubStr(p,4,"abc");
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);

        }
    }

}
