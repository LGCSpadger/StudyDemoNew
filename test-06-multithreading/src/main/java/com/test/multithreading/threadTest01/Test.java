package com.test.multithreading.threadTest01;

public class Test {

  public static void main(String[] args) {
    MyThread myThread = new MyThread();
    myThread.setName("myThread");
    myThread.start();
    for (int i = 0; i < 10; i++) {
      System.out.println("main=" + Thread.currentThread().getName());
    }
  }

}
