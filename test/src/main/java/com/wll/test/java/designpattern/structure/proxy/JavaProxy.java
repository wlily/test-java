package com.wll.test.java.designpattern.structure.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wll on 7/12/17.
 * JDK Proxy只能为如下对象生成动态代理： 被代理类(Target)有实现某一个或多个接口
 * Java Proxy自动生成的代理类的特点: $Proxy0 extends Proxy implements Target对应的所有接口，
 * 动态代理类将所有方法调用委托给InvocationHandler处理
 */
public class JavaProxy{

    public static Object getProxy(Class clazz, InvocationHandler handler) {
        if(clazz.isInterface()){
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{clazz}, handler);
        }
        else{
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), clazz.getInterfaces(), handler);
        }
    }

    public static void main(String[] args) {
        // 生成$Proxy0的class文件
        // 配置系统属性sun.misc.ProxyGenerator.saveGeneratedFile为true，代理类生成时将自动将生成的代理类写入硬盘
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Object proxy = JavaProxy.getProxy(Function12.class, invocationHandler());
        Interface1 d1 = (Interface1)proxy;
        System.out.println("--------");
        d1.fun1();
        Interface2 d2 = (Interface2)proxy;
        System.out.println("--------");
        d2.fun2();

        Interface1 intf1 = (Interface1)JavaProxy.getProxy(Interface1.class, invocationHandler());
        intf1.fun1();

        try{
            //这里的proxy继承了Proxy, 因为Dummy3没有实现任何接口，所以proxy也不实现任何接口
            Object proxy3 = JavaProxy.getProxy(Function3.class, invocationHandler2());
            //强制类型转换会抛异常
            Function3 function3 = (Function3)proxy3;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static InvocationHandler invocationHandler(){
        return new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(proxy.getClass());
                System.out.println("pre-" + method.getName());
                Object result = method.invoke(new Function12(), args);
                System.out.println("post-" + method.getName());
                return result;
            }
        };
    }

    private static InvocationHandler invocationHandler2(){
        return new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("pre-" + method.getName());
                System.out.println("post-" + method.getName());
                return null;
            }
        };
    }
}
