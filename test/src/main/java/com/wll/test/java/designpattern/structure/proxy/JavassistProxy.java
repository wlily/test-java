package com.wll.test.java.designpattern.structure.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wll on 7/12/17.
 * Javassist Proxy可以利用任意类生成动态字节码（也就是代理)
 * Javassist Proxy自动生成的代理类的特点:
 * 如果使用set super class为A, xxx$EnhancerByCGLIB$$xxx extends A implements Factory，
 * 如果使用set interfaces, xxx$EnhancerByCGLIB$$xxx implments A, Factory.
 * 代理实现里将所有方法调用委托给MethodInterceptor处理 *
 */
public class JavassistProxy{

    public static Object getProxy(Class clazz, MethodHandler methodHandler){
        try {
            ProxyFactory proxyFactory = new ProxyFactory();
            if(clazz.isInterface()){
                proxyFactory.setInterfaces(new Class[]{clazz});
            }
            else{
                proxyFactory.setSuperclass(clazz);
            }
            return proxyFactory.create(null, null, methodHandler);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("", e);
        }
    }

    public static void main(String[] args) {
        Object proxy = JavassistProxy.getProxy(Function12.class, methodHandler());
        Interface1 d1 = (Interface1)proxy;
        d1.fun1();
        System.out.println("-------------------------------");
        Interface2 d2 = (Interface2)proxy;
        d2.fun2();
        System.out.println("--------------------------------");

        try {
            Interface1 intf1 = (Interface1) JavassistProxy.getProxy(Interface1.class, methodHandler());
            intf1.fun1();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Object proxy3 = JavassistProxy.getProxy(Function3.class, methodHandler());
        Function3 function3 = (Function3)proxy3;
        function3.fun3();
    }

    private static MethodHandler methodHandler(){
        return new MethodHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Method proceed, Object[] args) throws Throwable {
                Object result;
                System.out.println(proxy.getClass());  //class com.javassist.demo.A_$$_javassist_0
                if(proceed != null){
                    System.out.println("into proceed invoke");
                    System.out.println("pre-" + method.getName() + "----" + proceed.getName());
                    result = proceed.invoke(proxy, args);
                    System.out.println("post-" + method.getName() + "----" + proceed.getName());
                }
                else{
                    //对应于对应接口或者抽象类等没有具体实现的代理
                    System.out.println("into specific invoke");
                    System.out.println("pre-" + method.getName());
                    result = method.invoke(new Function12(),args);
                    System.out.println("post-" + method.getName());
                }
                return result;
            }
        };
    }
}
