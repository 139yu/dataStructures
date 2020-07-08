package com.xj.daastructures.sort;

public class RadixSort {
    public static void main(String[] args) {
        //int arr[] = {13,64,456,234,76,671};
        int arr[] = new int[100000];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*1000000);
        }
        long before = System.currentTimeMillis();
        radixSort(arr);
        long after = System.currentTimeMillis();
        System.out.println(after - before);
    }
    public static void radixSort(int arr[]){
        //获取最大数的位数
        int max = arr[0];
        for (int i=1;i<arr.length;i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int length = (max + "").length();
        //定义一个二维数组，表示10个桶，一个桶表示一个一维数组
        int bucket[][] = new int[10][arr.length];
        //记录每个桶中放入的数据的个数
        int bucketElementCounts[] = new int[10];
       for (int i=0,n=1;i<length;i++,n*=10){
           //将arr数组中的数据放入桶中
           for (int j=0;j<arr.length;j++){
               int digitElement = arr[j] / n % 10;
               bucket[digitElement][bucketElementCounts[digitElement]] = arr[j];
               bucketElementCounts[digitElement]++;
           }
           //将桶中数据取出放入原数组中
           //记录原数组的位置
           int index = 0;
           for (int k=0;k<bucketElementCounts.length;k++){
               if(bucketElementCounts[k] != 0){
                   for (int l=0;l<bucketElementCounts[k];l++){
                       arr[index++] = bucket[k][l];
                   }
               }
               bucketElementCounts[k] = 0;
           }
       }

    }
}
