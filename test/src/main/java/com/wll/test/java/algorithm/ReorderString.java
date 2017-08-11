package com.wll.test.java.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wll on 17-8-11.
 */
public class ReorderString {

    public static void main(String[] args) {
        ReorderString tt = new ReorderString();
        tt.reorder("abcd");
    }

    private List<String> reorder(String ss) {
        List<String> list = new LinkedList<>();
        list.add(String.valueOf(ss.charAt(0)));
        if (ss.length() == 1) {
            return list;
        }

        List<String> tmp = list;
        int j = 1;
        while (j < ss.length()) {
            tmp = append(tmp, ss.charAt(j));
            j++;
        }

        for(int k=0; k<tmp.size(); k++){
            System.out.println(tmp.get(k));
        }
        return tmp;
    }

    private List<String> append(List<String> list, char append){
        List<String> newList = new LinkedList<>();
        for(int j=0; j<list.size(); j++){
            newList.addAll(append(list.get(j), append));
        }
        return newList;
    }

    private List<String> append(String src, char append) {
        List<String> list = new LinkedList<>();

        for (int i = 0; i < src.length() + 1; i++) {
            String tmp = src.substring(0, i) + append + src.substring(i, src.length());
            list.add(tmp);
        }
        return list;
    }

}
