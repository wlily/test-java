package com.wll.test.java.algorithm;

/**
 * Created by wll on 17-7-15.
 */
public class OptimizedBubbleSort {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,2,3,5,8, 0};

        OptimizedBubbleSort sort = new OptimizedBubbleSort();
        sort.sort(array);
        sort.print(array);
    }

    public void sort(int[] array){
        boolean hasSwap;
        int len = array.length;

        for(int i=0; i<len; i++){
            hasSwap = false;
            for(int j = len-1; j>i; j--){
                if(array[j] < array[j-1]){
                    swap(array, j, j-1);
                    hasSwap = true;
                }
            }
            if(!hasSwap){
                break;
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
