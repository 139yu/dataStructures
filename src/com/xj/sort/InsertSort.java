package com.xj.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*100000 + 1);
        }
        long before = System.currentTimeMillis();
        insertSort(arr);
        long after = System.currentTimeMillis();
        System.out.println(after-before);

    }
    public static void test(int arr[]){
        int insertValue = 0;
        int insertIndex = 0;
        for (int i=1;i<arr.length;i++){
            insertIndex = i - 1;
            insertValue = arr[i];
            for (int j=0;j<i;j++){
                if(insertValue < arr[insertIndex]){
                    arr[insertIndex + 1] = arr[insertIndex];
                    insertIndex--;
                }
                if (insertIndex < 0){
                    break;
                }
            }
            arr[insertIndex + 1] = insertValue;
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void insertSort(int arr[]){
        int insertValue = 0;
        int insertIndex = 0;
        for (int i=1;i<arr.length;i++){
            insertIndex = i - 1;
            insertValue = arr[i];
            while (insertIndex >= 0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }
    }
}
