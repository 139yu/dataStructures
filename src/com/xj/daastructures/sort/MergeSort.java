package com.xj.daastructures.sort;

public class MergeSort {
    public static void main(String[] args) {
        //int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2 };
        int arr[] = new int[1000000];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*10000000);
        }
        int temp[] = new int[arr.length];
        long before = System.currentTimeMillis();
        mergeSort(arr,0,arr.length-1,temp);
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
    public static void mergeSort(int arr[],int left,int right,int temp[]){
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr,left,mid,temp);
            //向右递归分解
            mergeSort(arr,mid + 1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }
    /**
     *
     * @param arr 元素数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public static void merge(int arr[],int left,int mid,int right,int temp[]){
        int l = left;
        int r = mid + 1;
        //记录temp数组的位置
        int t = 0;
        //先把左右两边有序的数据填充到temp数组中
        while (l <= mid && r <= right) {
            if(arr[l] <= arr[r]){
                temp[t] = arr[l];
                l += 1;
                t += 1;
            }else{
                temp[t] = arr[r];
                t += 1;
                r += 1;
            }
        }
        //把剩余有序的数据填充到temp中
        while(l <= mid){
            temp[t] = arr[l];
            t += 1;
            l += 1;
        }
        while (r <= right) {
            temp[t] = arr[r];
            t += 1;
            r += 1;
        }
        //将temp中的数据拷贝到arr中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
