package com.wll.test.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by wll on 17-7-7.
 */
public class TestThreadPool {

    private ExecutorService t1 = Executors.newSingleThreadExecutor();
    private ExecutorService t2 = Executors.newFixedThreadPool(10);
    private ExecutorService t3 = Executors.newCachedThreadPool();

    private ScheduledExecutorService tt1 = Executors.newSingleThreadScheduledExecutor();
    private ScheduledExecutorService tt3 = Executors.newScheduledThreadPool(10);

    private Runnable r1 = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " begins");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ends");
        }
    };

    private Callable<Integer> c1 = new Callable<Integer>() {
        @Override
        public Integer call() {
            System.out.println(Thread.currentThread().getName() + " begins");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ends");
            return 1;
        }
    };

    private void test(){
        t1.submit(r1);
    }

    public static void main(String[] args) {
        new TestThreadPool().test();
    }
}
