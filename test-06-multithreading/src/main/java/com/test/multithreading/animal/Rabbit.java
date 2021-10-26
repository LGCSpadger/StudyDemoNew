package com.test.multithreading.animal;

public class Rabbit extends Animal {

    //通过构造方法给线程取名
    public Rabbit() {
        setName("兔子");// Thread的方法，给线程赋值名字
    }

    // 重写running方法，编写兔子的奔跑操作
    @Override
    public void runing() {
        // 跑的距离
        double dis = 0.5;
        //跑完后距离减少
        length -= dis;
        if (length <= 0) {
            length = 0;
            System.out.println("兔子获得了胜利！");
                //给回调对象赋值，让乌龟不要再跑了
            if (calltoback != null) {
                calltoback.win();
            }
        }
        System.out.println("兔子跑了 " + dis + " 米，距离终端还有 " + length + " 米");
        // 两米休息一次
        if (length % 2 == 0) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
