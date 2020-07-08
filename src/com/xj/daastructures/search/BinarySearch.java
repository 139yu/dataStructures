package com.xj.daastructures.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1,5,65,76,733,976,1111,1111,1111};

        System.out.println(binarySearch2(arr, 0, arr.length - 1, 1111));
    }
    public static int binarySearch(int arr[],int left,int right,int value){
        if (left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (value < midVal) {
            return binarySearch(arr,left,mid - 1,value);
        }else if (value > midVal){
            return binarySearch(arr,mid + 1,right,value);
        }else {
            return mid;
        }
    }

    public static List<Integer> binarySearch2(int arr[], int left, int right, int value){
        if (left > right){
            return null;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (value < midVal) {
            return binarySearch2(arr,left,mid - 1,value);
        }else if (value > midVal){
            return binarySearch2(arr,mid - 1,right,value);
        }else {
            int temp = mid -1;
            List<Integer> valueIndexList = new ArrayList<>();
            while(true){
                if (temp < 0 || arr[temp] != value){
                    break;
                }
                valueIndexList.add(temp);
                temp--;
            }
            valueIndexList.add(mid);
            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                valueIndexList.add(temp);
                temp++;
            }
            return  valueIndexList;
        }
    }
}
