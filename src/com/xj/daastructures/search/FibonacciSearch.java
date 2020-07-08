package com.xj.daastructures.search;

import java.util.Arrays;

public class FibonacciSearch {
    static int maxSize = 20;
    public static void main(String[] args) {
        int [] arr = {1,8, 10, 89, 1000, 1234,8965};
        System.out.println(fibonacciSearch(arr,1234));
    }
    public static int[] fib(){
        int fib[] = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }
    public static int fibonacciSearch(int arr[],int key){
        int k = 0;
        int fib[] = fib();
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;
        while(high > fib[k] - 1){
            k++;
        }
        int temp[] = Arrays.copyOf(arr,fib[k]);
        for (int i=high+1;i<temp.length;i++){
            temp[i] = arr[high];
        }
        while(low <= high){
            mid = low + fib[k - 1] - 1;
            if (key < temp[mid]){
                high = mid - 1;
                k--;
            }else if (key > temp[mid]){
                low = mid + 1;
                k -=2;
            }else {
                if (mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        throw  new RuntimeException("不存在该值");
    }
}
