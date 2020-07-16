package com.xj.algorithm;

import java.util.Arrays;

public class SortDemo {
    public static void main(String[] args) {
        int arr[] = {3, 12, 54, 21, 5212, 352, 31};
        System.out.println(Arrays.toString(insertSort(arr)));
    }

    /**
     * 冒泡排序
     * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * 3.针对所有的元素重复以上的步骤，除了最后一个。
     * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    public static int[] bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * 1.找到未排序列表中最小的元素，放在已排序末尾
     * 2.再从为排序中继续找到最小元素，放在末尾
     * 3.重复以上步骤
     */
    public static int[] selectSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }

    /**
     * 插入排序
     * 1.将第一个数看作是有序序列，第二个数到最后一个数看做是待排序序列
     * 2.从第二个数开始扫描，将扫描到的数放在有序序列的适当位置（如果待插入元素与有序序列中的元素相等，则放到左边）
     */
    public static int[] insertSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            //待插入元素
            int temp = arr[i];
            //从已排序序列最右边开始
            int j = i;
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j-1];
                j--;
            }
            //如果存在比其小的的插入
            if (j !=1) {
                arr[j] = temp;
            }
        }
        return arr;
    }
    /**
     * 希尔排序
     */
    /*public static int[] shellSort(int arr[]) {
        for (int step=arr.length/2;step>0;step/=2) {

        }
    }*/
}
