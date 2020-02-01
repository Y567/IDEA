package com.gyy.utils;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 模拟通知，也就是增强的部分   写日志
 */
public class Logger {
    //前置通知
    public void beforeLog(){
        System.out.println("前置通知");
    }

    //后置通知
    public void afterReturnLog(){
        System.out.println("后置通知");
    }

    //异常通知
    public void afterThrowLog(){
        System.out.println("异常通知");
    }

    //最终通知
    public void afterLog(){
        System.out.println("最终通知");
    }

    /**
     * 环绕通知会让切入点方法不执行从而手动调用执行，同时环绕通知也可以以代码的形式来手动决定通过的类型
     * spring框架为我们提供了一个接口：ProceedJoinPoints。该接口有一个方法proceed，该方法表示显示调用切入点方法
     * 该接口可以作为环绕通知方法的方法参数，在程序执行时spring框架会提供其实现类给我们使用
     *
     * @return
     */
    public Object aroundLog(ProceedingJoinPoint pjp){
        Object returnValue = null;
        try{
            Object[] args = pjp.getArgs();  //用来获得切入点方法的参数
            System.out.println("放在这里就是前置通知");
            returnValue = pjp.proceed(args);//有重载函数，有参数的无参数的，这里用的是有参数的
            System.out.println("放在这里就是后置通知");
            return returnValue;
        }catch(Throwable t){
            System.out.println("环绕通知放在异常里就是异常通知");
        }finally {
            System.out.println("放在这里就是最终通知");
        }

        return returnValue;
    }
}
