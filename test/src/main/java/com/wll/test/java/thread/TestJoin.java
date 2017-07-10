package com.wll.test.java.thread;

/**
 * Created by wll on 2/17/17.
 */
public class TestJoin {

    public static void main(String[] args) {

        System.out.println("main thread running");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread1 running");
                    Thread.currentThread().sleep(5*1000);
                    System.out.println("thread1 finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread2 running");
                    Thread.currentThread().sleep(3*1000);
                    System.out.println("thread2 finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("thread3 running");
                    Thread.currentThread().sleep(5*1000);
                    System.out.println("thread3 finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            System.out.println("main thread waiting thread1 finish");
            t1.join();
            System.out.println("sdsfsd");
            t2.join();
            System.out.println("main thread resumes");
            t3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
