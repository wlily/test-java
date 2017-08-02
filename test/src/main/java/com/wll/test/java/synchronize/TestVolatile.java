package com.wll.test.java.synchronize;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * volatile变量自增运算测试
 *
 * @author zzm
 */
public class TestVolatile {

    public static void main(String[] args) {
//        new Test1().test();
//        new Test2().test();
        new Test3().test();
    }

    static class Test1{
        public static volatile int race = 0;

        public static void increase() {
//        当getstatic指令把race的值取到操作栈顶时,volatile关键字保证了race的值在此
//        时是正确的,但是在执行iconst_1、iadd这些指令的时候,其他线程可能已经把race的值加大
//        了,而在操作栈顶的值就变成了过期的数据,所以putstatic指令执行后就可能把较小的race值
//        同步回主内存之中
            race++;
        }

        //这段代码发起了20个线程,每个线程对race变量进行10000次自增操作,如果这段代码能
//够正确并发的话,最后输出的结果应该是200000。读者运行完这段代码之后,并不会获得期
//望的结果,而且会发现每次运行程序,输出的结果都不一样,都是一个小于200000的数字,
//这是为什么呢?
        public void test() {
            Thread[] threads = new Thread[20];

            for (int i = 0; i < 20; i++) {
                threads[i] = new Thread(() -> {
                    for (int i1 = 0; i1 < 10000; i1++) {
                        increase();
                    }
                });
                threads[i].start();
            }
            //等待所有累加线程都结束
            while (Thread.activeCount() > 1) {
                Thread.yield();
            }
            System.out.println(race);
        }
    }

    static class Test2{
        volatile boolean shutdownRequested;

        public void shutdown() {
            shutdownRequested = true;
        }

        public void doWork() {
            while (!shutdownRequested) {
                try {
                    sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void test() {

            for (int i = 0; i < 20; i++) {
                new Thread(() -> {
                    doWork();
                }).start();
            }

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //这类场景就很适合使用volatile变量来控制并发,
            //当shutdown()方法被调用时,能保证所有线程中执行的doWork()方法都立即停下来。
            shutdown();
        }
    }

    static class Test3 {
        Map configOptions;
        char[] configText;
        //此变量必须定义为volatile
         boolean initialized = false;

        public void test(){
            new Thread(()->init()).start();
            new Thread(()->work()).start();
        }

        public void init() {
            //假设以下代码在线程A中执行
            //模拟读取配置信息,当读取完成后将initialized设置为true以通知其他线程配置可用
            configOptions = new HashMap();
            configText = readConfigFile("");
            processConfigOptions(configText, configOptions);
            initialized = true;
        }

        public void work() {
            //假设以下代码在线程B中执行
            //等待initialized为true,代表线程A已经把配置信息初始化完成
            while (!initialized)
            {
                try {
                    sleep(60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //使用线程A中初始化好的配置信息
            doSomethingWithConfig();
        }

        private char[] readConfigFile(String s) {
            System.out.println("into readConfigFile");
            try {
                sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("out readConfigFile");
            return null;
        }

        private void processConfigOptions(char[] configText, Map configOptions) {
            System.out.println("into processConfigOptions");
            try {
                sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("out processConfigOptions");
        }



        private void doSomethingWithConfig() {
            System.out.println("into doSomethingWithConfig");
            try {
                sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("out doSomethingWithConfig");
        }
    }

}