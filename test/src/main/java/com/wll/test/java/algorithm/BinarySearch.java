package com.wll.test.java.algorithm;

/**
 * Created by wll on 17-8-2.
 */
public class BinarySearch {

    public int search(int[] array, int des){
        int low =0;
        int high = array.length - 1;
        return binSearch(array, des, low, high);
    }

    private int binSearch(int[] array, int des, int low, int high){
        if(low <= high){
            int middle = (low + high) >> 1;
            if(des == array[middle]){
                return middle;
            }
            else if(des < array[middle]){
                high = middle - 1;
            }
            else{
                low = middle + 1;
            }
            return binSearch(array, des, low, high);
        }
        throw new RuntimeException("not found");
    }

    private int binSearch2(int[] array, int des){
        int low =0;
        int high = array.length - 1;
        while(low <= high){
            int middle = (low + high) >> 1;
            if(des == array[middle]){
                return middle;
            }
            else if(des < array[middle]){
                high = middle - 1;
            }
            else{
                low = middle + 1;
            }
        }
        throw new RuntimeException("not found");
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{1,2,3,4,5,8,9,10,12};
        int[] array2 = new int[]{11};
        int[] array3 = new int[]{1,3};
        int[] array4 = new int[]{1,3,5};
        System.out.println(new BinarySearch().binSearch2(array1, 8));
        System.out.println(new BinarySearch().binSearch2(array2, 11));
        System.out.println(new BinarySearch().binSearch2(array3, 3));
        System.out.println(new BinarySearch().binSearch2(array4, 5));
    }
}
