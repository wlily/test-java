package com.wll.test.java.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wll on 7/12/17.
 */
public class CGlibProxy implements MethodInterceptor {

    public static void main(String[] args) {
        CGlibProxy ce = new CGlibProxy();
        Dummy dummy = (Dummy)ce.getProxy(Dummy.class);

        dummy.fun1();

        System.out.println("--------------");

        dummy.fun2();

        System.out.println("************");
        TTT ttt = (TTT)ce.getProxy(TTT.class);
        ttt.fun3();
    }

    private Object getProxy(Class clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("pre-" + method.getName());
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("post-" + method.getName());
        return result;
    }

    static class TTT{
        public void fun3(){
            System.out.println("fun3 start");
            System.out.println("fun3 end");
        }
    }
}
