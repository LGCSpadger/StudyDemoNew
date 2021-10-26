package com.test.multithreading.sub;

public class ThreadAddSub extends Thread {

    //判断要进行的操作
    boolean operate = true;

    //要操作的数
    static int sum = 0;

    // 把操作运算通过构造方法传进来
    public ThreadAddSub(boolean operate) {
        super();
        this.operate = operate;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            if (operate) {
                sum += 5;
                System.out.println("加后，sum = " + sum);
            } else {
                sum -= 4;
                System.out.println("减后，sum = " + sum);
            }
            try {
                sleep(500);// 睡眠0.5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
