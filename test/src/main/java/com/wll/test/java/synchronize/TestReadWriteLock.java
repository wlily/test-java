package com.wll.test.java.synchronize;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by wll on 17-7-20.
 */
public class TestReadWriteLock {

    private ReadWriteLock lock;

    public static void main(String[] args) {
        new TestReadWriteLock().test();
    }

    public void test(){
        lock = new ReentrantReadWriteLock();
        new Thread(new WriteThread(), "w0").start();
        new Thread(new ReadThread(), "r1").start();
        new Thread(new ReadThread(), "r2").start();
        new Thread(new WriteThread(), "w1").start();
        new Thread(new WriteThread(), "w2").start();
    }

    class ReadThread implements Runnable{

        @Override
        public void run() {
            try {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + " get read lock");
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
                System.out.println(Thread.currentThread().getName() + " release read lock");
            }
        }
    }

    class WriteThread implements Runnable{

        @Override
        public void run() {
            try {
                lock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() + " get write lock");
                Thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
                System.out.println(Thread.currentThread().getName() + " release write lock");
            }
        }
    }
}
