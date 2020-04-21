package com.gyy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                        //添加getter,setter,toString
@AllArgsConstructor          //添加满参数的构造方法
@NoArgsConstructor           //添加无参构造方法
public class User {
    private String name;
    private String sex;
    private int age;
}
