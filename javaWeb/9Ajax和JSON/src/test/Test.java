package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;

import java.io.FileWriter;
import java.io.IOException;

public class Test {

    @org.junit.Test
    public void test1() throws IOException {
        //1.0创建一个User对象
        User user = new User();
        user.setUsername("gyy");
        user.setAge(23);

        //1.0创建一个核心对象ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        //2.0调用方法实现对象转换为json对象
        //String json = objectMapper.writeValueAsString(user);

        //将其输出到文件中
        objectMapper.writeValue(new FileWriter("jsonTest.txt"),user);

        //3.0输出
//        System.out.println(json);

    }
}
