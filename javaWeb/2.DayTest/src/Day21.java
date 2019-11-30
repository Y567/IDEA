import java.util.*;

public class Day21 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        HashMap<Character,Character> map = new HashMap<>();
        map.put('A','V');
        map.put('B','W');
        map.put('C','X');
        map.put('D','Y');
        map.put('E','Z');
        map.put('F','A');
        map.put('G','B');
        map.put('H','C');
        map.put('I','D');
        map.put('J','E');
        map.put('K','F');
        map.put('L','G');
        map.put('M','H');
        map.put('N','I');
        map.put('O','J');
        map.put('P','K');
        map.put('Q','L');
        map.put('R','M');
        map.put('S','N');
        map.put('T','O');
        map.put('U','P');
        map.put('V','Q');
        map.put('W','R');
        map.put('X','S');
        map.put('Y','T');
        map.put('Z','U');
        map.put(' ',' ');
        while(in.hasNext()){
            String s = in.nextLine();
            char[] c = s.toCharArray();
            for(int i = 0;i < c.length;i++){
                c[i]=map.get(c[i]);
            }
            StringBuffer sb = new StringBuffer();
            sb.append(c);
            System.out.println(sb.toString());
        }
    }
}
