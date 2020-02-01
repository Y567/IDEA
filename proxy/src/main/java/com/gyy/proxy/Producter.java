package com.gyy.proxy;

/**
 * 模拟生产厂商
 */
public class Producter implements IProducter {

    //销售
    public int saleProduct(int money){
        System.out.println("卖出了电脑拿到了"+money+"元");
        return money;
    }

    //售后
    public int afterService(int money){
        System.out.println("进行售后服务获得了"+money+"元");
        return money;
    }
}
