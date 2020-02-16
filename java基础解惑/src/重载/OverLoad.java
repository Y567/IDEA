package 重载;

import java.util.ArrayList;

public class OverLoad {
    public static void test1(int i){
        System.out.println(i);
        System.out.println("整形");
    }
    public static void test1(float i){
        System.out.println(i);
        System.out.println("float型");
    }
    public static void test1(double i){
        System.out.println(i);
        System.out.println("double型");
    }


    public static void main(String[] args) {
        double i = 2;
        test1(i);
    }
}

