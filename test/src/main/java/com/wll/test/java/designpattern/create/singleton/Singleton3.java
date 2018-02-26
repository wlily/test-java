package com.wll.test.java.designpattern.create.singleton;

/**
 * DCL模式(Double Check Lock)
 * DCL模式解决懒汉模式(线程安全)每次get都需要synchronize导致的效率低的问题。
 *
 * DCL模式有一个难以理解的地方，为什么需要使用volatile变量修饰instance?
 * <<HeadFirst设计模式>>对于volatile描述如下，
 * The volatile keyword ensures that multiple threads handle the instance variable correctly
 * when it is being initialized to the Singleton3 instance.
 * 翻译成中文是：volatile关键字保证instance字段初始化时，多线程可以正确处理这个变量。
 *
 * 这个解释还是不太好理解。
 *
 * 网上对于是否加volatile也存在很多争论。
 * 支持使用volatile的同学，觉得问题可能会出在4这一步上，因为new Singleton3()会被分解成三个操作，
 * (1) 分配内存 (2)初始化构造函数和成员变量 (3)将instance指向分配的空间
 * 觉得这一过程可能会被重排序，如果重排成1-3-2, 而在1-3后，CPU切换到另一个线程，另一个线程可能得到一个未初始化的实例
 *
 * 也有少数不支持volatile的同学，支持synchronized关键字也会禁用指令重排序，不会出现上述问题。
 *
 * 我偏向于支持后者， 如果非得说使用volatile有什么用处，可能存在这样一种序列：
 * 1. ThreadA执行到4时，CPU切换到ThreadB,
 * 2. 因为ThreadA的synchronized块并未返回，并不能保证实例化后的instance已经被刷到主内存，所以ThreadB不一定立即可见，
 * 如果不可见，那么ThreadB继续执行到2等待锁，等ThreadB获取到锁后，instance肯定已经被刷回到主内存了(这是synchronized的可见性保证的），
 * 此时ThreadB判断instance不为null，也不会再实例化了， 所以不使用volatile， 只可能会导致ThreadB进入第一个if多等待了一个锁，不会有其他问题。
 *
 * */
public class Singleton3 {
    private volatile static Singleton3 instance;

    private Singleton3(){}

    public static Singleton3 getInstance(){
        if(instance == null){ //1
            synchronized (Singleton3.class){ //2
                if(instance == null){ //3
                    instance = new Singleton3(); //4
                }
            }
        }
        return instance;
    }


}
