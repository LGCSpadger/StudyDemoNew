package com.test.pub.utils;

public class Test001 {

    public static void main(String[] args) {
        System.out.println(test01());
    }

    public static int test01() {
        int x = 1;
        try {
            return x;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            ++x;
//            return ++x;
        }
    }

}
