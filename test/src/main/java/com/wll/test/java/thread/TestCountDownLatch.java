package com.wll.test.java.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by wll on 2/17/17.
 */
public class TestCountDownLatch {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        CountDownLatch latch = new CountDownLatch(3);

        Student s1 = new Student(101, latch);
        Student s2 = new Student(102, latch);
        Student s3 = new Student(103, latch);
        Teacher t = new Teacher(latch);

        executor.execute(t);
        executor.execute(s1);
        executor.execute(s2);
        executor.execute(s3);

        executor.shutdown();
    }

    static class Student implements Runnable {

        private int num;
        private CountDownLatch cdlatch;

        Student(int num, CountDownLatch latch) {
            this.num = num;
            this.cdlatch = latch;
        }

        @Override
        public void run() {
            System.out.println("Student " + num + " is doing the exam!");
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Student " + num + " finished!");
            cdlatch.countDown();
        }
    }

    static class Teacher implements Runnable {

        private CountDownLatch cdlatch;

        Teacher(CountDownLatch latch) {
            this.cdlatch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println("teacher is waiting...");
                cdlatch.await();
                System.out.println("teacher is collecting......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
