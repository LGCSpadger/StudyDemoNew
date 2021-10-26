package com.test.multithreading.mult;

public class Bank {

    //假设一个银行账户有1000块钱
    static int money = 1000;

    //柜台取钱
    public void counter(int counterMoney) {//counterMoney为用户每次取走的钱数
        Bank.money -= counterMoney;
        System.out.println("A取走了：" + counterMoney + " 元，账户剩余：" + Bank.money + " 元");
    }

    //ATM机取钱
    public void ATM(int counterMoney) {//counterMoney为用户每次取走的钱数
        Bank.money -= counterMoney;
        System.out.println("B取走了：" + counterMoney + " 元，账户剩余：" + Bank.money + " 元");
    }

}
