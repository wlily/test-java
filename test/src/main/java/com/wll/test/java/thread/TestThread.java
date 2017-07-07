package com.wll.test.java.thread;

import static java.lang.Thread.sleep;

/**
 * Created by wll on 17-6-14.
 */
public class TestThread {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " begin");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "end");
        };

//        new Thread(r).start();
//        new Thread(r).start();
//        new Thread(r).start();
//        new Thread(r).start();

        Thread t = new Thread(r);
        t.start();
        t.start();
        t.start();
        t.start();
    }
}
