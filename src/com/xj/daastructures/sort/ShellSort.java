package com.xj.daastructures.sort;

public class ShellSort {
    public static void main(String[] args) {
        int arr[] = new int[1000000];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*100000000);
        }
        long before = System.currentTimeMillis();
        shellSort2(arr);
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
    //交换法
    public static void shellSort(int arr[]){
        int temp = 0;
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (arr[j] > arr[j + step]) {
                        temp = arr[j];
                        arr[j] = arr[j + step];
                        arr[j + step] = temp;
                    }
                }
            }
        }
    }
    //移位法
    public static void shellSort2(int arr[]){
        int insertIndex = 0;
        int insertValue = 0;
        for (int step=arr.length/2;step>0;step/=2){
            for (int i=step;i<arr.length;i++){
                insertIndex = i;
                insertValue = arr[insertIndex];
                if (arr[insertIndex] < arr[insertIndex - step]){
                    while(insertIndex - step >= 0 && insertValue < arr[insertIndex - step]){
                        arr[insertIndex] = arr[insertIndex - step];
                        insertIndex -= step;
                    }
                    arr[insertIndex] = insertValue;
                }
            }
        }
    }
}
