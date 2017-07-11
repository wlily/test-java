package com.wll.test.java.thread;

import static java.lang.Thread.sleep;

/**
 * Created by wll on 7/11/17.
 */
public class TestInterrupt {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " interrupted1: " + Thread.interrupted());
            System.out.println(Thread.currentThread().getName() + " interrupted2: " + Thread.interrupted());
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrupted3: " + Thread.currentThread().isInterrupted());
            try {
                sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt 1st");
            }
            System.out.println(Thread.currentThread().getName() + " interrupted5: " + Thread.currentThread().isInterrupted());

            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrupted6: " + Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName() + " interrupted7: " + Thread.interrupted());
            try {
                sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt 2nd");
            }
            System.out.println(Thread.currentThread().getName() + " interrupte8: " + Thread.currentThread().isInterrupted());
        };
        Thread t = new Thread(r);
        t.start();
    }
}
