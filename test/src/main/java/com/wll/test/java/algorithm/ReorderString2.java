package com.wll.test.java.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wll on 17-8-11.
 */
public class ReorderString2 {

    public static void main(String[] args) {
        ReorderString2 tt = new ReorderString2();
        List<Character> ss = new LinkedList<>();
        ss.add('a');
        ss.add('b');
        ss.add('c');
        ss.add('d');

        List<String> pp = tt.reorder(ss);
        for(String one: pp){
            System.out.println(one);
        }
    }

    private List<String> reorder(List<Character> ss) {
        List<String> list = new LinkedList<>();
        if(ss.size() == 1){
            list.add(String.valueOf(ss.get(0)));
        }
        else{
            for(int i=0; i<ss.size(); i++){
                List<Character> bb = new LinkedList<>();
                bb.addAll(ss);
                char c = bb.remove(i);
                List<String> tmp = reorder(bb);
                for(String one : tmp){
                    list.add(one + c);
                }
            }
        }
        return list;

    }

}
