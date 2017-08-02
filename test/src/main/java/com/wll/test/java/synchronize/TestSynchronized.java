package com.wll.test.java.synchronize;

/**
 * Created by wll on 17-7-28.
 */

/**
 * 逻辑没意义，但是能看出notify和notifyAll的区别
 */
public class TestSynchronized {
    private int num_apple;

    public static void main(String[] args) {
        TestSynchronized test = new TestSynchronized();

        for(int i=0; i<10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.eatApple();
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.buy();
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private synchronized void buy() {
        num_apple+=10;
        System.out.println("buy apple");
//        notifyAll();
        //替换成notify试试
        notify();
    }

    private synchronized void eatApple() {
        System.out.println(Thread.currentThread().getName() + " into eat apple");
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (num_apple > 0) {
            num_apple--;
            System.out.println(Thread.currentThread().getName() + " eat apple");
        }

        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
