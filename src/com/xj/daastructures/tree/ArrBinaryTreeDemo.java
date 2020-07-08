package com.xj.daastructures.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int [] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.postOrder(0);
    }
}
class ArrBinaryTree{
    private int arr[];
    public ArrBinaryTree(int arr[]){
        this.arr = arr;
    }
    public void preOrder(){
        this.preOrder(0);
    }
    /**
     * 顺序二叉表前序 遍历
     * @param index 数组开始下标
     */
    public void preOrder(int index){
        if (this.arr == null || arr.length == 0){
            return;
        }
        System.out.println(arr[index]);
        //遍历左子树
        if ((2 * index + 1) <arr.length){
            this.preOrder(2 * index + 1);
        }
        //遍历右子树
        if ((2 * index + 2) < arr.length){
            this.preOrder(2 * index + 2);
        }
    }

    /**
     * 顺序遍历二叉树的中序遍历
     * @param index 数组开始下标
     */
    public void infixOrder(int index){
        if (this.arr == null || arr.length == 0){
            return;
        }
        if ((2 * index + 1) <arr.length){
            this.infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if ((2 * index + 2) < arr.length){
            this.infixOrder(2 * index + 2);
        }
    }
    public void postOrder(int index){
        if (this.arr == null || arr.length == 0){
            return;
        }
        if ((2 * index + 1) <arr.length){
            this.postOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length){
            this.postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}
