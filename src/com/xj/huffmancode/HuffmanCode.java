package com.xj.huffmancode;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        Map<Byte, Integer> map = getCount(str);
        List<Node> nodes = getNodes(map);
        Node huffmanTree = createHuffmanTree(nodes);
        getCode(huffmanTree,"",stringBuilder);
        System.out.println(huffmanCode);
    }
    public static StringBuilder stringBuilder = new StringBuilder();
    public static Map<Byte,String> huffmanCode = new HashMap<>();
    public static Map<Byte,String> getCode(Node node,String code,StringBuilder sb){
        StringBuilder stringBuilder = new StringBuilder(sb);
        stringBuilder.append(code);
        if (node != null) {
            if (node.data == null) {
                getCode(node.left,"0",stringBuilder);
                getCode(node.right,"1",stringBuilder);
            }else {
                huffmanCode.put(node.data,stringBuilder.toString());
            }
        }
        return huffmanCode;
    }
    /**
     * 将字节作为结点的数据，出现次数作为结点的权重保存在结点中
     * @param map 保存字节和字节出现次数的集合
     * @return 以集合的形式返回
     */
    public static List<Node> getNodes(Map<Byte,Integer> map){
        if (map == null || map.size() == 0) {
            return null;
        }
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    /**
     * 获取每个字符出现的个数
     * @param str
     * @return 以字节为key，出现次数为值的map形式返回
     */
    public static Map<Byte,Integer> getCount(String str){
        byte[] bytes = str.toLowerCase().getBytes();
        Map<Byte,Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (map.containsKey(b)){
                Integer value = map.get(b);
                value++;
                map.put(b,value);
            }else {
                int value = 1;
                map.put(b,value);
            }
        }
        return map;
    }

    /**
     * 构建霍夫曼树
     * @param nodes 需要构建霍夫曼树的所有结点
     * @return 根节点
     */
    public static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1) {
            //按从小到大排序
            Collections.sort(nodes);
            //取出权重最小的两个结点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //创建一个新的二叉树作为根节点，权重为这两个结点权重之和
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
class Node implements Comparable<Node>{
    Byte data;//存放数据，例如'a'=>97
    int weight;//结点权重
    Node left;
    Node right;
    public Node(Byte data,int weight){
        this.data = data;
        this.weight = weight;
    }
    public Node(int weight){
        this.weight = weight;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if (this == null) {
            return ;
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
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}