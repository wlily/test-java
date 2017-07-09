package com.wll.test.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by wll on 2/17/17.
 */
public class TestRunnableCallableFutureExecutor {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            //1. Runnable通过Future返回结果为空
            //创建一个Runnable,来调度,等待任务执行完毕,取得返回结果
            Future<?> runnable1 = executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("runnable1 running");
                }
            });
            System.out.println("runnable1 result: " + runnable1.get());

            //2. Callable通过Future能返回结果
            //提交并执行任务, 任务启动时返回了一个Future对象,
            //如果想得到任务执行的结果或者是异常可对这个Future对象进行操作
            Future<String> future1 = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(10*1000);
                    return "result=task1";
                }
            });
            //获得任务的结果,如果调用get(), 当前线程会等待任务执行完毕后才往下执行
            System.out.println("sdsfd");
            System.out.println("task1 result: " + future1.get());
            System.out.println("vvvv");

            //3. 对Callable调用cancel可以对该任务进行中断
            //提交并执行任务, 任务启动时返回了一个Future对象
            //如果想得到任务执行的结果或者是异常可对Future对象进行操作
            Future<String> future2 = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    try{
                        while (true){
                            System.out.println("task2 running");
                            Thread.sleep(50);
                        }
                    }
                    catch (InterruptedException e){
                        System.out.println("task 2 interrupted");
                    }
                    return "task2=false";
                }
            });
            Thread.sleep(10);
            System.out.println("task2 cancel: " + future2.cancel(true));

            //4. 用Callable时抛出异常则Future什么也取不到
            // 获取第三个任务的输出, 因为执行第三个任务会引起异常
            // 所以下面的语句将引起异常的抛出
            Future<String> future3 = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    throw new Exception("task3 throw exception");
                }
            });
            System.out.println("task3: " + future3.get());

        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        // 停止任务执行服务
        executor.shutdownNow();
    }
}
