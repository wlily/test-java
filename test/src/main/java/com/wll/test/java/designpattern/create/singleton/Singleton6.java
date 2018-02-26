package com.wll.test.java.designpattern.create.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 登记式单实例模式
 * spring IOC prototype bean采用的模式
 */
public class Singleton6{
    private static Map<String,Object> map = new HashMap<>();

    private Singleton6(){}

    public static void register(String key, Object singleton){
        if(!map.containsKey(key)){
            map.put(key,singleton);
        }
    }
    public static Object getInstance(String key){
        return map.get(key);
    }
}


