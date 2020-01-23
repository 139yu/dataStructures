package com.xj.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[100000];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*100000 + 1);
        }
        long before = System.currentTimeMillis();
        selectSort(arr);
        long after = System.currentTimeMillis();
        System.out.println("耗时" + (after - before));
    }
    public static void selectSort(int arr[]){
        int min = 0;
        int minIndex = 0;
        for (int i=0;i<arr.length;i++){
            minIndex = i;
            min = arr[minIndex];
            for (int j=i+1;j<arr.length;j++){
                if(min > arr[j]){
                    minIndex = j;
                    min = arr[j];
                }
            }
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
