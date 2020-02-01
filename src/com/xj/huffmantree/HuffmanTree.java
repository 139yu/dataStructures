package com.xj.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {1,3,6,7,8,13,29};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrder();
    }
    public static Node createHuffmanTree(int arr[]){
        //将结点保存在一个list中，用于管理结点
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //当集合中只剩根节点时就退出循环
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            //取出集合中权值最小的两个结点，组成一个二叉树，根节点权值为这两个结点权值的和
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //删除取出的两个结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新生成的结点保存在集合中，并再次排序
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;
    public Node(int value){
        this.value = value;
    }

    /**
     * 前序遍历二叉树
     */
    public void preOrder(){
        if (this == null) {
            return;
        }
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
