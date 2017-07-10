package com.wll.test.java.synchronize;

/**
 * Created by wll on 17-7-10.
 */
public class TestThreadLocal {
    private static ThreadLocal<Integer> num = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private static ThreadLocal<String> name = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "hello";
        }
    };

    public int getNextNum(){
        num.set(num.get() + 1);
        return num.get();
    }

    public String getNextName(){
        name.set(name.get() + "_" + getNextNum());
        return name.get();
    }

    public static void main(String[] args) {
        TestThreadLocal ttl = new TestThreadLocal();

        for(int i=0; i<3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0; j<3; j++){
//                    System.out.println(Thread.currentThread().getName() + "---" + ttl.getNextNum());
                        System.out.println(Thread.currentThread().getName() + "---" + ttl.getNextName());
                    }
                }
            }).start();
        }
    }
}
