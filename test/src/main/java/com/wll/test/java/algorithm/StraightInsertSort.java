package com.wll.test.java.algorithm;

/**
 * Created by wll on 17-7-17.
 */

import java.util.Arrays;

/**
 * 直接插入排序
 * 将一个记录插入到已排序好的有序表中，从而得到一个新，记录数增1的有序表。
 * 即：先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
 *
 * 其他的插入排序有二分插入排序，2-路插入排序。
 */
public class StraightInsertSort {

    public void insertSort(int[] array){
        for(int i = 1; i<array.length; i++){
            if(array[i] < array[i-1]){
                int x = array[i];
                int j = i-1;
                while(x < array[j] && j>=0){
                    array[j+1] = array[j];
                    j--;
                }
                array[j+1] =x;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1,0, 2, 0, 3, 5, 9, 6, 4};
        StraightInsertSort sort = new StraightInsertSort();
        sort.insertSort(array);
        System.out.println(Arrays.toString(array));
    }
}
