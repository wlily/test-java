package com.wll.test.java.synchronize;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wll on 2/17/17.
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock testLock = new TestLock();
        testLock.testLock();
        testLock.testCondition();
    }

    private void testLock(){
        TestLock1 testLock1 = new TestLock1();
        Thread t1 = new Thread(() -> testLock1.test1(), "thread1");
        Thread t2 = new Thread(() -> testLock1.test1(), "thread2");
        Thread t3 = new Thread(() -> testLock1.test2(), "thread3");
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(100);
            t3.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void testCondition(){
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        Thread t1 = new Thread(() -> {
            System.out.println("t1 run");
            for (int i=0;i<1000;i++) {
                try {
                    System.out.println("putting..");
                    boundedBuffer.put(Integer.valueOf(i));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }) ;

        Thread t2 = new Thread(() -> {
            for (int i=0;i<1000;i++) {
                try {
                    Object val = boundedBuffer.take();
                    System.out.println(val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }) ;

        t1.start();
        t2.start();
    }

    static class TestLock1 {
        private ReentrantLock lock = new ReentrantLock();

        public void test1() {
            try {
                System.out.println(Thread.currentThread().getName() + " try lock");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " locked");
                try {
                    Thread.sleep(2 * 1000);
                    tt();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted");
                }
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlocked");
            }
        }

        private void tt() {
            try {
                if (lock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " try lock+1");
                }
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlock+1");
            }
        }

        public void test2() {
            try {
                System.out.println(Thread.currentThread().getName() + " lockInterruptibly");
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " locked");
                Thread.sleep(3 * 1000);
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " unlock");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted, no need unlock");
            }
        }
    }

    static class TestLock2 {
        private int state = 1;
        private int n = 1;
        private ReentrantLock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        public void test() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " lock ");
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + " locked ");
                            while (state != 1) {
                                try {
                                    c1.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int j = 0; j < 5; j++) {
                                System.out.println(Thread.currentThread().getName() + ": " + n++);
                            }
                            System.out.println();
                            state = 2;
                            c2.signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }, "thread1").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " lock ");
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + " locked ");
                            while (state != 2) {
                                try {
                                    c2.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int j = 0; j < 5; j++) {
                                System.out.println(Thread.currentThread().getName() + ": " + n++);
                            }
                            System.out.println();
                            state = 3;
                            c3.signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }, "thread2").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " lock ");
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + " locked ");
                            while (state != 3) {
                                try {
                                    c3.await();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            for (int j = 0; j < 5; j++) {
                                System.out.println(Thread.currentThread().getName() + ": " + n++);
                            }
                            System.out.println();
                            state = 1;
                            c1.signal();
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }, "thread3").start();
        }
    }

    static class BoundedBuffer {
        final Lock lock = new ReentrantLock();
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();

        final Object[] items = new Object[100];
        int putptr, takeptr, count;

        public void put(Object x) throws InterruptedException {
            System .out.println("put wait lock");
            lock.lock();
            System.out.println("put get lock");
            try {
                while (count == items.length) {
                    System.out.println("buffer full, please wait");
                    notFull.await();
                }

                items[putptr] = x;
                if (++putptr == items.length){
                    putptr = 0;
                }
                ++count;
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            System.out.println("take wait lock");
            lock.lock();
            System.out.println("take get lock");
            try {
                while (count == 0) {
                    System.out.println("no elements, please wait");
                    notEmpty.await();
                }
                Object x = items[takeptr];
                if (++takeptr == items.length){
                    takeptr = 0;
                }
                --count;
                notFull.signal();
                return x;
            } finally {
                lock.unlock();
            }
        }
    }
}
