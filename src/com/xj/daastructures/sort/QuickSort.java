package com.xj.daastructures.sort;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = new int[100000];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*1000000);
        }
        long before = System.currentTimeMillis();
        quickSort(arr,0,arr.length-1);
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
    public static void quickSort(int arr[],int left,int right){
        int temp = 0;
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        while(l < r){
            while(arr[l] < pivot){
                l += 1;
            }
            while(arr[r] > pivot){
                r -= 1;
            }
            if(l >= r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot){
                r -= 1;
            }
            if (arr[r] == pivot){
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
