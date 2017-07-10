package com.wll.test.java.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wll on 17-7-10.
 */
public class TestBase {

    protected void walk(Collection collection){
        Iterator iterator = collection.iterator();
        for(;iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }

    protected void walk(Map map){
        Iterator iterator = map.entrySet().iterator();
        for(;iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }

}
