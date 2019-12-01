
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Test2 {
    public static void test(String s){
        char[] chars = s.toCharArray();
        Set<Character> set = new LinkedHashSet<>();
        for (char c:chars) {
            set.add(c);
        }
        for (Character character : set) {
            System.out.print(character);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            test(s);
        }
    }
}
