package com.gyy.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("proxy")
@Aspect
public class Proxy {

    @Autowired
    private TransactionManager tx;

    //切点表达式
    @Pointcut("execution(* com.gyy.service.impl.*.*(..))")
    public void pt() {
    }

    //前置通知 开启事务
//    @Before("pt()")
    public void before() {
        System.out.println("成功了吗");
        tx.begin();
    }

    //后置通知 提交事务
//    @AfterReturning("pt()")
    public void afterReturn() {
        tx.commit();
    }

    //异常通知 回滚事务
//    @AfterThrowing("pt()")
    public void throwLog() {
        tx.rollback();
    }

    //最终通知 释放资源
//    @After("pt()")
    public void after() {
        tx.close();
    }

    //要用注解配置AOP的话用环绕通知，因为其他通知执行顺序不对，先执行最终通知，导致不能用后置通知提交事务}
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp){
        Object returnValue;
        try{
            //获取参数
            Object[] args = pjp.getArgs();
            //前置通知
            this.before();
            //执行方法
            returnValue = pjp.proceed(args);
            //后置通知
            this.afterReturn();
            //返回结果
            return returnValue;
        }catch (Throwable t){
            //异常通知
            this.throwLog();
            throw new RuntimeException(t);
        }finally {
            //最终通知
            this.after();
        }

    }
}
