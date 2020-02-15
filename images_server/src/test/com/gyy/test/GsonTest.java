package com.gyy.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class GsonTest {
    public static void main(String[] args) {
        //将一个map集合转换为json对象
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("狗子","你好");
        //创建Gson对象
        Gson gson = new GsonBuilder().create();
        //调用方法转换
        String s = gson.toJson(hashMap);
        System.out.println(s);
    }
}
