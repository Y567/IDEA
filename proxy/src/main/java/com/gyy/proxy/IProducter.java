package com.gyy.proxy;

public interface IProducter {
    //销售
    int saleProduct(int money);

    //售后
    int afterService(int money);
}
