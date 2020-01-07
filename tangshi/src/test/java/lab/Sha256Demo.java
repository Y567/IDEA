package lab;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Demo {
    /**
     * MD5      短
     * SHA-256  长
     */
    @Test
    public void sha256Test() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //求一个字符串的哈希变换后的值
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String s = "你好小博哥";
        byte[] bytes = s.getBytes("UTF-8");
        messageDigest.update(bytes);
        byte[] result = messageDigest.digest();
        System.out.println(result.length);
        for(byte b:result){
            //不足两位补0
            System.out.printf("%02x",b);
        }
        System.out.println();

    }
}
