package com.test.multithreading.animal;

public class Tortoise extends Animal {

    //通过构造方法给线程取名
    public Tortoise() {
        setName("乌龟");//Thread的方法
    }

    //实现父类的runing方法，实现乌龟的奔跑操作
    @Override
    public void runing() {
        //兔子每秒跑的距离
        double dis = 0.1;
        //剩余路程
        length -= dis;
        if (length <= 0) {
            length = 0;
            System.out.println("乌龟胜利了！");
            //给回调对象赋值，让兔子不要再跑了
            if (calltoback != null) {
                calltoback.win();
            }
        }
        System.out.println("乌龟跑了 " + dis + " 米，距离终点还剩 " + length + " 米");
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
