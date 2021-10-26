package com.test.multithreading.mult;

/**
 * 多线程使用案例一：三个售票窗口同时出售20张车票
 * 程序分析：
 *      1.票数要使用同一个静态值
 *      2.为保证三个窗口不会卖出同一个票数，要使用同步锁
 * 设计思路：
 *      1.创建一个站台类Station，继承Thread类，重写run方法，在run方法里面执行售票操作！售票要使用同步锁：即有一个站台卖这张票时，其他站台要等这张票卖完
 *      2.创建主方法调用站台类
 */
public class Station extends Thread {

    //通过构造方法给线程名字赋值
    public Station(String name) {
        super(name);
    }

    //为了保持票数一致，票数要静态
    static int ticket = 20;

    //创建一个静态钥匙（值可以是任意的）
    static Object ob = "aa";

    //重写run方法，实现卖票操作
    @Override
    public void run() {
        while (ticket > 0) {
            synchronized (ob) {
                if (ticket > 0) {
                    System.out.println(getName() + "卖出了地 " + ticket + " 张票");
                    ticket--;
                } else {
                    System.out.println("票卖完了");
                }
            }
            try {
                sleep(1000);//休息一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
