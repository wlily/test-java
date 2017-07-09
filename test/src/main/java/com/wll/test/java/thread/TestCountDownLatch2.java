package com.wll.test.java.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wll on 2/17/17.
 */
public class TestCountDownLatch2 {

    private static int PLAYER_NUM = 10;

    public static void main(String[] args) {

        final CountDownLatch beginSignal = new CountDownLatch(1);
        final CountDownLatch endSignal = new CountDownLatch(PLAYER_NUM);

        ExecutorService executorService = Executors.newFixedThreadPool(PLAYER_NUM);

        for (int i = 0; i < PLAYER_NUM; i++) {
            final int num = i + 1;
            Runnable runner = new Runnable() {
                @Override
                public void run() {
                    System.out.println("No. " + num + " is waiting...");
                    try {
                        beginSignal.await();
                        System.out.println("No. " + num + " begin running");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("No." + num + " arrived");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        endSignal.countDown();
                    }
                }

            };
            executorService.execute(runner);
        }

        System.out.println("before Game Start");
        beginSignal.countDown();
        System.out.println("Game Start");
        System.out.println("---In the middle of the game---");
        try {
            endSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Game Over!");
            executorService.shutdown();
        }

    }
}
