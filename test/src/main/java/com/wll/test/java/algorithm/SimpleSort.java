package com.wll.test.java.algorithm;

/**
 * Created by wll on 17-7-15.
 */
public class SimpleSort{

    public static void main(String[] args) {
        int[] array = new int[]{1,10,3,4,8,7,2,9,1,2};

        SimpleSort sort = new SimpleSort();
        sort.sort(array);
        sort.print(array);
    }

    public void sort(int[] array){
        for(int i=0; i<array.length; i++){
            for(int j=i+1; j<array.length; j++){
                if(array[j] < array[i]){
                    swap(array, j, i);
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
