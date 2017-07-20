package com.wll.test.java.algorithm;

/**
 * Created by wll on 17-7-15.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{1,10,3,4,8,7,2,9,1,2};

        BubbleSort sort = new BubbleSort();
        sort.sort(array);
        sort.print(array);
    }

    public void sort(int[] array){
        int len = array.length;

        for(int i=0; i<len; i++){
            for(int j = len-1; j>i; j--){
                if(array[j] < array[j-1]){
                    swap(array, j, j-1);
                }
            }
        }
    }

    protected void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    protected void print(int[] array){
        for(int one : array){
            System.out.println(one);
        }
    }
}
