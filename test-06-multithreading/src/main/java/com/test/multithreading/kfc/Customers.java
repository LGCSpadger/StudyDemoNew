package com.test.multithreading.kfc;

public class Customers extends Thread {

    KFC kfc;

    //KFC要传入，保证每一个服务员和用户在同一个KFC对象内
    public Customers(KFC kfc) {
        this.kfc = kfc;
    }

    @Override
    public void run() {
        //每次要消费的食物的数量
        int size = (int) (Math.random() * 5);
        while (true) {
            kfc.consu(size);//在消费的方法里面传入参数
        }
    }

}