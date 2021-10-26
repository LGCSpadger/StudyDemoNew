package com.test.multithreading.mult;

public class MainClass {

    //一个车站的三个窗口同时出售20张票
    public static void main(String[] args) {
        //实例化窗口对象，并为每一个窗口取名
        Station station1 = new Station("一号窗口");
        Station station2 = new Station("二号窗口");
        Station station3 = new Station("三号窗口");

        //让每一个窗口开始工作
        station1.start();
        station2.start();
        station3.start();
    }

}
