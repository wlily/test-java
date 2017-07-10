package com.wll.test.java.synchronize;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wll on 2/17/17.
 */
//http://www.cnblogs.com/techyc/
//生产者-消费者模型是多线程问题里面的经典问题，也是面试的常见问题。有如下几个常见的实现方法：
//        1. wait()/notify()
//        2. lock & condition
//        3. BlockingQueue
public class TestProductConsumer {

    public static void main(String[] args) {
        final WaitNotifyBroker broker = new WaitNotifyBroker(100);

        for (int i = 0; i < 3; i++) {
            //producer
            new Thread(new Runnable() {
                @Override
                public void run() {
                    broker.put("sdfsdf");
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            //consumer
            new Thread(new Runnable() {
                @Override
                public void run() {
                    broker.take();
                }
            }).start();
        }
    }

    interface Broker<T> {
        T take();
        void put(T obj);
    }

    static class WaitNotifyBroker<T> implements Broker<T> {
        private int capacity = 100;
        private final LinkedList<T> items = new LinkedList<T>();

        public WaitNotifyBroker(int capacity) {
            this.capacity = capacity;
        }

        @Override
        public T take() {
            try {
                synchronized (items) {
                    if (items.size() == 0) {
                        items.wait();
                    }

                    T item = items.poll();
                    items.notifyAll();
                    return item;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void put(T obj) {
            try {
                synchronized (items) {
                    if (items.size() >= capacity) {
                        items.wait();
                    } else {
                        items.add(obj);
                        items.notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class LockConditionBroker<T> implements Broker<T> {
        private final ReentrantLock lock;
        private final Condition notFull;
        private final Condition notEmpty;
        private final int capacity;
        private LinkedList<T> items;

        public LockConditionBroker(int capacity) {
            this.lock = new ReentrantLock();
            this.notFull = lock.newCondition();
            this.notEmpty = lock.newCondition();
            this.capacity = capacity;
            items = new LinkedList<T>();
        }

        @Override
        public T take() {
            T tmpObj = null;
            lock.lock();
            try {
                while (items.size() == 0) {
                    notEmpty.await();
                }

                tmpObj = items.poll();
                notFull.signalAll();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return tmpObj;
        }

        @Override
        public void put(T obj) {
            lock.lock();
            try {
                while (items.size() == capacity) {
                    notFull.await();
                }

                items.offer(obj);
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    static class BlockingQueueBroker<T> implements Broker<T> {
        private final BlockingQueue<T> queue;

        public BlockingQueueBroker() {
            this.queue = new LinkedBlockingQueue<T>();
        }

        @Override
        public T take() {
            try {
                return queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public void put(T obj) {
            try {
                queue.put(obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
