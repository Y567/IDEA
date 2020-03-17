package com.gyy.util;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class ImageUtil {
    private static String ImageToBase64(String imgPath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(Objects.requireNonNull(data));
    }
    public static void main(String[] args) {
        /*String word0 = "C:\\Users\\可乐yue\\Desktop\\word1.png";
//        System.out.println("第一张图片的bas64码"+ImageToBase64(word0));
        Scanner in = new Scanner(ImageToBase64(word0));
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()){
            sb.append(in.nextLine());
        }
        System.out.println(sb.toString());*/
        int count = 0;
        for (int i = 0; i <= 38; i++) {{
            for (int j = i+1; j <= 39; j++) {
                count++;
            }
        }

        }
        System.out.println(count);
    }
}
