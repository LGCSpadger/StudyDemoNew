package com.test.multithreading.mult;

public class BankMainClass {

    public static void main(String[] args) {
        // 实例化一个银行对象
        Bank bank = new Bank();
        // 实例化两个人A和B，传入同一个银行的对象
        PersonA personA = new PersonA(bank);
        PersonB personB = new PersonB(bank);
        // 两个人开始取钱
        personA.start();
        personB.start();
    }

}
