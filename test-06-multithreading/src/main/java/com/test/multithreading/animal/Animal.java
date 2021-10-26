package com.test.multithreading.animal;

public abstract class Animal extends Thread {

    //比赛的长度
    public double length = 20;

    //抽象方法需要子类实现
    public abstract void runing();

    //在父类重写run方法，在子类只要重写running方法就可以了
    @Override
    public void run() {
        super.run();
        while (length > 0) {
            runing();
        }
    }

    //在需要回调数据的地方（两个子类需要），声明一个接口
    public static interface Calltoback {
        public void win();
    }

    //创建接口对象
    public Calltoback calltoback;

}
