package com.wll.test.java.algorithm;

import java.util.Arrays;

/**
 * Created by wll on 17-7-15.
 */
public class QuickSort {

    public void quickSort(int[] array, int left, int right){
        int tmp_left = left;
        int tmp_right = right;

        int X = array[tmp_left];

        while(tmp_left < tmp_right){
            while (tmp_left < tmp_right && array[tmp_right] >= X){
                tmp_right--;
            }
            array[tmp_left] = array[tmp_right];

            while (tmp_left < tmp_right && array[tmp_left] < X){
                tmp_left++;
            }
            array[tmp_right] = array[tmp_left];
        }
        array[tmp_left] = X;

        if(tmp_left > left){
            quickSort(array, left, tmp_left-1);
        }
        if(tmp_right < right){
            quickSort(array, tmp_right + 1, right);
        }
    }

    public static void main(String[] args) {
        int[] array = {1,0, 2, 0, 3, 5, 9, 6, 4};
        QuickSort sort = new QuickSort();
        sort.quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
