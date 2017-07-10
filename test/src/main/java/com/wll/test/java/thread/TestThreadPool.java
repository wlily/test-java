package com.wll.test.java.thread;

import java.util.concurrent.*;

/**
 * Created by wll on 17-7-7.
 */
public class TestThreadPool {
    private ExecutorService t1 = Executors.newSingleThreadExecutor();
    private ExecutorService t2 = Executors.newFixedThreadPool(2);
    private ExecutorService t3 = Executors.newCachedThreadPool();

    private ScheduledExecutorService tt1 = Executors.newSingleThreadScheduledExecutor();
    private ScheduledExecutorService tt3 = Executors.newScheduledThreadPool(10);

    private ExecutorService ttt1 = new ThreadPoolExecutor(2,5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new MyThreadFactory(), new MyRejectHandler());


    private class MyRejectHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(executor.getPoolSize());
            System.out.println(executor.getTaskCount());
            System.out.println("give up " + r);
        }
    }

    private class MyThreadFactory implements ThreadFactory{
        private int count = 1;
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "thread-" + count++);
        }
    }
    private Runnable r1 = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " begins");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ends");
        }
    };

    private Callable<Integer> c1 = new Callable<Integer>() {
        @Override
        public Integer call() {
            System.out.println(Thread.currentThread().getName() + " begins ss");
            try {
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " ends ss");
            return 1;
        }
    };

    private void test(){
//        for(int i=0; i<3; i++){
//            t1.submit(r1);
//        }
//        for(int i=0; i<5; i++){
//            t2.submit(c1);
//        }
//        for(int i=0; i<5; i++){
//            t3.submit(c1);
//        }

        for(int i=0; i<50; i++){
            ttt1.submit(c1);
        }
    }

    public static void main(String[] args) {
        new TestThreadPool().test();
    }
}
