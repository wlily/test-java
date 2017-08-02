package com.wll.test.java.collection;

import java.util.concurrent.*;

/**
 * Created by wll on 17-7-28.
 */
public class TestSynchronousQueue {
    private static int THREAD_NUM;
    private static int N = 1000000;
    private static ExecutorService executor;

    public static void main(String[] args) throws Exception {
        System.out.println("Producer\tConsumer\tcapacity \t LinkedBlockingQueue \t ArrayBlockingQueue \t SynchronousQueue");

        for (int j = 0; j < 10; j++) {
            THREAD_NUM = (int) Math.pow(2, j);
            executor = Executors.newFixedThreadPool(THREAD_NUM * 2);

            for (int i = 0; i < 10; i++) {
                int length = (i == 0) ? 1 : i * 10;
                System.out.print(THREAD_NUM + "\t\t");
                System.out.print(THREAD_NUM + "\t\t");
                System.out.print(length + "\t\t");
                System.out.print(doTest2(new LinkedBlockingQueue<Integer>(length), N) + "/s\t\t\t");
                System.out.print(doTest2(new ArrayBlockingQueue<Integer>(length), N) + "/s\t\t\t");
                System.out.print(doTest2(new SynchronousQueue<Integer>(), N) + "/s");
                System.out.println();
            }

            executor.shutdown();
        }
    }

    private static class Producer implements Runnable {
        private int initN;
        private BlockingQueue<Integer> initQ;

        public Producer(int initN, BlockingQueue<Integer> initQ) {
            this.initN = initN;
            this.initQ = initQ;
        }

        public void run() {
            for (int i = 0; i < initN; i++)
                try {
                    initQ.put(i);
                } catch (InterruptedException ex) {
                }
        }
    }

    private static class Consumer implements Callable<Long> {
        private int initN;
        private BlockingQueue<Integer> initQ;

        public Consumer(int initN, BlockingQueue<Integer> initQ) {
            this.initN = initN;
            this.initQ = initQ;
        }

        public Long call() {
            long sum = 0;
            for (int i = 0; i < initN; i++)
                try {
                    sum += initQ.take();
                } catch (InterruptedException ex) {
                }
            return sum;
        }
    }

    private static long doTest2(final BlockingQueue<Integer> q, final int n) throws Exception {
        CompletionService<Long> completionServ = new ExecutorCompletionService<Long>(executor);

        long t = System.nanoTime();
        for (int i = 0; i < THREAD_NUM; i++) {
            executor.submit(new Producer(n / THREAD_NUM, q));
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            completionServ.submit(new Consumer(n / THREAD_NUM, q));
        }

        for (int i = 0; i < THREAD_NUM; i++) {
            completionServ.take().get();
        }

        t = System.nanoTime() - t;
        return (long) (1000000000.0 * N / t); // Throughput, items/sec
    }
}
