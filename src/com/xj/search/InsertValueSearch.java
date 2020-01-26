package com.xj.search;

public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = {1,5,65,76,733,976,1111};
        System.out.println(insertValueSearch(arr,0,arr.length-1,733));
    }
    public static int insertValueSearch(int arr[],int left,int right,int value){
        System.out.println("aaaa");
        if(left > right || value < arr[0] || value > arr[arr.length - 1]){
            throw new RuntimeException("值不存在");
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        if (value < arr[mid]){
            return insertValueSearch(arr,left,mid - 1,value);
        } else if (value > arr[mid]) {
            return insertValueSearch(arr,mid + 1,right,value);
        }else {
            return mid;
        }
    }
}
