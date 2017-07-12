package com.wll.test.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wll on 7/12/17.
 */
public class JavaProxy implements InvocationHandler {
    private Object target; //被代理对象

    public JavaProxy(Object target){
        super();
        this.target = target;
    }

    public static void main(String[] args) {
        JavaProxy handler = new JavaProxy(new Dummy());

        DummyInterface proxy = (DummyInterface)handler.getProxy();

        proxy.fun1();

        System.out.println("--------");

        proxy.fun2();

        System.out.println("************");

//        T2 ttt = (T2)new JavaProxy(new TTT()).getProxy();
        TTT ttt = (TTT)new JavaProxy(new TTT()).getProxy();

        ttt.fun3();
    }

    private Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre-" + method.getName());

        Object resutl = method.invoke(target, args);

        System.out.println("post-" + method.getName());
        return resutl;
    }

//    static interface T2{
//        void fun3();
//    }
//    static class TTT implements T2{
//        public void fun3(){
//            System.out.println("fun3 start");
//            System.out.println("fun3 end");
//        }
//    }

    static class TTT{
        public void fun3(){
            System.out.println("fun3 start");
            System.out.println("fun3 end");
        }
    }
}
