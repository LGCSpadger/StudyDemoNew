package com.test.multithreading.sub;

public class MainClass {

    public static void main(String[] args) {
        //创建一个存放ThreadAddSub对象的数组
        ThreadAddSub[] subs = new ThreadAddSub[4];
        for (int i = 0; i < subs.length; i++) {
            //把实例化ThreadAddSub对象赋值到数组内
            //第一三个是true，二四个是false
            subs[i] = new ThreadAddSub(i % 2 == 0 ? true : false);
            //让线程开始工作
            subs[i].start();
        }
    }

}
