package com.gyy.test;

/**
 * 使用junit单元测试测试配置
 */

import com.gyy.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Test
    public void testTransfrom(){
        //3.执行方法
        accountServiceImpl.transfrom("阿狗1","阿狗2",4000);
    }
}
