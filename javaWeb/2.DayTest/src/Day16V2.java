import java.util.Scanner;

public class Day16V2 {
    //洗牌题
    public static String clean(String p) {
        String leftHand = p.substring(0, p.length() / 2);
        String rightHand = p.substring(p.length() / 2);
/*        System.out.println(leftHand);
        System.out.println(rightHand);*/
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < p.length() / 2; i++) {
            sb.append(leftHand.charAt(i));
            sb.append(rightHand.charAt(i));
        }
        return sb.toString();
    }

/*    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        int[] second = new int[2];
        for (int i = 0; i < T; i++) {
            second[0] = in.nextInt();
            second[1] = in.nextInt();
            in.nextLine();
            String p = in.nextLine();
            String res = p.replace(" ","");
            for (int j = 0; j < second[1]; j++) {
                res = clean(res);
            }
            System.out.println(res);
        }*/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        int N=in.nextInt();
        if(N==0){
            return;
        }
        in.nextLine();  //吃个空格
        String scores = in.nextLine();
        int score = in.nextInt();

        String[] s=scores.split(" ");
        for(String t:s){
            int i = Integer.parseInt(t);
            if(i==score){
                count++;
            }
        }
        System.out.println(count);
    }
}