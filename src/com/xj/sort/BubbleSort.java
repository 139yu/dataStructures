package com.xj.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3,16,75,-5,1,21};
        int temp = 0;
        boolean flag = false;
        for (int i=0;i<arr.length-1;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if(arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第" + (i+1) + "趟");
            System.out.println(Arrays.toString(arr));
            if(!flag){
                break;
            }else {
                flag = false;
            }
        }

    }
}
