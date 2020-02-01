package com.gyy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 模拟消费者
 */
public class Client {
    /**
     * 动态代理:
     * 特点：
     *   随用随创建，随用随加载
     * 作用：
     *   不修改源码的基础上改变被代理的对象的方法
     * 分类：
     *   1.基于接口的动态代理
     *   2.基于子类的动态代理
     *
     * 这里是基于子类的，注意：如果被代理的对象没有继承接口那么将无法代理
     *    用到的类：Enhancer
     *    提供者：第三方库(需要导入依赖包)
     * 如何创建代理对象：
     *    利用Enhancer类的create方法
     *    create方法的参数
     *         Class:
     *             它是用于加载被代理对象的字节码文件的即和被代理对象的类加载器一样
     *         callback：
     *             用于提供增强的代码
     *         通常情况下是写一个匿名内部类，但不是必须的
     *
     *
     */
    public static void main(String[] args) {
        final Producter pro = new Producter();

        Producter producter = (Producter) Enhancer.create(pro.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象都要执行该方法
             * @param proxy
             * @param method
             * @param args
             *                以上三个参数和基于动态代理对象那个的作用一样
             * @param methodProxy 当前执行方法的代理对象
             * @return
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                Object returnValue = null;
                if ("saleProduct".equals(method.getName())) {
                    Integer money = (Integer) args[0]; //这里我们知道只有一个参数而前是int所以强转
                    returnValue = method.invoke(pro, money * 2);
                }
                return returnValue;
            }
        });
        producter.saleProduct(10000);
    }
}
