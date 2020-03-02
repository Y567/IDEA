package 类加载;

import java.lang.String;

public class Student {
    public void study(){
        System.out.println("正在学习");
    }

    public static void main(String[] args) {
        ClassLoader classLoader = new Student().getClass().getClassLoader();
        System.out.println("加载该类的加载器是"+classLoader);
        System.out.println("appClassLoader的父类加载器"+classLoader.getParent());  //扩展类加载器
        System.out.println("appClassLoader的父类的父类加载器"+classLoader.getParent().getParent());//启动类加载器没权限获取到

    }

}
