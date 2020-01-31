package com.xj.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    public static void heapSort(int arr[]){
        int temp = 0;
        //将一个无序列表根据升序或降序构成一个大顶堆或小顶堆
        for (int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }
        for (int j=arr.length-1;j>0;j--){
            //将堆顶元素与末尾元素交换，保证末尾元值最大
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //交换之后要保证堆顶值要大于左右子节点一直保持堆顶为最大值
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树）调成一个大顶堆
     * @param arr 待调整的数组
     * @param i 非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整
     */
    public static void adjustHeap(int arr[],int i,int length){
        int temp = arr[i];
        //k是i节点的左子节点，k+1就是i的右子节点
        for (int k=i*2+1;k<length;k=k*2+1){
            //如果右子节点大于左子节点，就让k指向右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]){
                k++;
            }
            //如果子节点大于当前节点，就让当前节点大于该子节点的值
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }
}
