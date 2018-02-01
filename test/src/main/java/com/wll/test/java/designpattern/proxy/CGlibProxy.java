package com.wll.test.java.designpattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wll on 7/12/17.
 * Cglib Proxy可以利用任意类生成动态字节码（也就是代理)，底层使用了ASM动态字节码技术
 * Cglib Proxy自动生成的代理类的特点:
 * 如果使用set super class为A, xxx$EnhancerByCGLIB$$xxx extends A implements Factory，
 * 如果使用set interfaces, xxx$EnhancerByCGLIB$$xxx implments A, Factory.
 * 代理实现里将所有方法调用委托给MethodInterceptor处理 *
 */
public class CGlibProxy {

    public static Object getProxy(Class clazz, MethodInterceptor interceptor) {
        Enhancer enhancer = new Enhancer();
        if(clazz.isInterface()){
            enhancer.setInterfaces(new Class[]{clazz});
        }
        else{
            enhancer.setSuperclass(clazz);
        }
        enhancer.setCallback(interceptor);
        return enhancer.create();
    }

    public static void main(String[] args) {
        Function12 function12 = (Function12)CGlibProxy.getProxy(Function12.class, interceptor());
        function12.fun1();
        System.out.println("--------------");
        function12.fun2();
        System.out.println("************");

        try {
            Interface1 intf1 = (Interface1) CGlibProxy.getProxy(Interface1.class, interceptor2());
            //替换成下面试试， 因为interface1没有实现，methodProxy.invokeSuper(super,xx)会抛错
//             Interface1 intf1 = (Interface1) CGlibProxy.getProxy(Interface1.class, interceptor());
            intf1.fun1();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Object proxy3 = CGlibProxy.getProxy(Function3.class, interceptor());
        Function3 function3 = (Function3)proxy3;
        function3.fun3();
    }

    private static MethodInterceptor interceptor(){
        return new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println(proxy.getClass());
                System.out.println("pre-" + method.getName());
                Object result = methodProxy.invokeSuper(proxy, args);
                //换成下面的方式，试下，比较打印结果
//                 Object result = method.invoke(new Function12(), args);
                System.out.println("post-" + method.getName());
                return result;
            }
        };
    }

    private static MethodInterceptor interceptor2(){
        return new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("pre-" + method.getName());
                 Object result = method.invoke(new Function12(), args);
                System.out.println("post-" + method.getName());
                return result;
            }
        };
    }
}
