package com.gyy.proxy;

import javax.annotation.PreDestroy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
     * 这里是基于接口的，注意：如果被代理的对象没有继承接口那么将无法代理
     *    用到的类：Proxy
     *    提供者：JDK
     *  如何创建代理对象：
     *    利用Proxy类的newProxyInstance方法
     *         newProxyInstance方法的三个参数
     *         ClassLoader:类加载器：它是用于加载被代理对象的字节码文件的即和被代理对象的类加载器一样
     *         Class[]数组：字节码数组，它是用于让代理对象和被代理对象有相同的方法。固定方法。
     *         InvocationHandler:用于提供增强的代码
     *         通常情况下是写一个匿名内部类，但不是必须的
     *
     *
     */
    public static void main(String[] args) {
        final Producter pro = new Producter();

        IProducter proxy = (IProducter) Proxy.newProxyInstance(pro.getClass().getClassLoader(), pro.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 执行任何被代理对象的任何接口方法都要经过这里
             * @param proxy 代理对象的引用
             * @param method  当前执行的方法
             * @param args   当前执行的方法的所需要的参数
             * @return 和被代理对象有相同的返回值
             * @throws Throwable
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = null;
                if ("saleProduct".equals(method.getName())) {
                    Integer money = (Integer) args[0]; //这里我们知道只有一个参数而前是int所以强转
                    returnValue = method.invoke(pro, money * 2);
                }
                return returnValue;
            }
        });
        proxy.saleProduct(10000);
    }
}
