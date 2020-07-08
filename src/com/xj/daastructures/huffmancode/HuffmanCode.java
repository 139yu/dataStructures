package com.xj.daastructures.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 压缩
 * 1.统计每个字符出现的次数，存储在一个map集合中，以map的形式返回
 * 2.将这些信息存放在结点中，构建成一棵霍夫曼树
 * 3.获取这些信息的霍夫曼编码表
 * 4.将原字符串转成霍夫曼编码
 * 解压
 * 1.将赫夫曼编码byte[]转成二进制字符串
 * 2.将二进制字符串与赫夫曼编码表匹配还原
 */
public class HuffmanCode {
    public static void main(String[] args) {
        /*String str = "i like like like java do you like a java";
        String s = byteToBitString((byte)-28, true);*/
        /*Map<Byte, Integer> count = getCount(str.getBytes());
        List<Node> nodes = getNodes(count);
        Node huffmanTree = createHuffmanTree(nodes);
        getCodes(huffmanTree,"",stringBuilder);
        System.out.println(huffmanCode);
        byte[] zip = zip(str.getBytes(), huffmanCode);
        System.out.println(Arrays.toString(zip));*/
        /*byte[] bytes = huffmanZip(str.getBytes());
        System.out.println(Arrays.toString(bytes));
        byte[] decode = decode(huffmanCode, bytes);
        System.out.println(new String(decode));*/
        String srcFile = "C:\\Users\\xj139\\Pictures\\Saved Pictures\\aa.png";
        String dstFile = "C:\\Users\\xj139\\Pictures\\Saved Pictures\\1.zip";
        //zipFile(srcFile,dstFile);
        unZipFile(dstFile,srcFile);
    }

    /**
     * 解压文件
     * @param srcFile 需要解压的文件
     * @param dstFile 解压后的文件
     */
    public static void unZipFile(String srcFile,String dstFile){
        InputStream fis = null;
        ObjectInputStream ois = null;
        OutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            ois = new ObjectInputStream(fis);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte,String> huffmanCode = (Map<Byte, String>) ois.readObject();
            byte[] decode = decode(huffmanCode, huffmanBytes);
            fos = new FileOutputStream(dstFile);
            fos.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 压缩文件
     * @param srcFile 源文件
     * @param dstFile 压缩后的文件
     */
    public static void zipFile(String srcFile,String dstFile){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //读取源文件
            fis = new FileInputStream(srcFile);
            byte[] b = new byte[fis.available()];
            //将源文件内容读取到字节数组中
            fis.read(b);
            //将文件压缩成赫夫曼字节数组
            byte[] huffmanZip = huffmanZip(b);
            fos = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(fos);
            //将赫夫曼字节数组和赫夫曼编码表写入到压缩文件中
            oos.writeObject(huffmanZip);
            oos.writeObject(huffmanCode);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 将赫夫曼字节数组转成原始内容
     * @param huffmanCode 赫夫曼编码表
     * @param huffmanBytes 赫夫曼字节数组
     * @return 原始内容字节数组
     */
    public static byte[] decode(Map<Byte,String> huffmanCode,byte[] huffmanBytes)throws Exception {
        if (huffmanBytes == null || huffmanBytes.length == 0) {
            return null;
        }
        //存储原始内容二进制字符串
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<huffmanBytes.length;i++){
            boolean flag = (i == huffmanBytes.length - 1);
            byte b = huffmanBytes[i];
            sb.append(byteToBitString(b,!flag));
        }
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i=0;i<sb.length();){
            Byte b = null;
            boolean flag = true;
            int count = 1;
            while (flag){
                String key = stringBuilder.substring(i, i+count);//i ????????count?????????????????
                b = map.get(key);
                if(b == null) {//?????????
                    count++;
                }else {
                    //???
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i=0;i<bytes.length;i++){
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将一个byte转成二进制字符串
     * @param b 需要转成二进制的字符串
     * @param flag 是否需要补高位
     * @return 二进制字符串
     */
    public static String byteToBitString(byte b,boolean flag){
        int temp = b;
        if (flag) {
            temp |= 256;
        }
        //转为二进制字符串
        String s = Integer.toBinaryString(temp);
        if (flag) {
            return s.substring(s.length() - 8);
        }
        return s;
    }

    private static Map<Byte,String> huffmanCode = new HashMap<>();
    private static StringBuilder stringBuilder = new StringBuilder();

    public static byte[] huffmanZip(byte[] content){
        //1.统计每个字符出现的次数，存储在一个map集合中，以map的形式返回
        Map<Byte, Integer> byteCount = getCount(content);
        //2.将这些信息存放在结点中，构建成一棵霍夫曼树
        List<Node> nodes = getNodes(byteCount);
        Node huffmanTree = createHuffmanTree(nodes);
        //3.获取这些信息的霍夫曼编码表
        getCodes(huffmanTree);
        //4.将原字符串转成霍夫曼编码
        byte[] zip = zip(content, huffmanCode);
        return zip;
    }
    /**
     * 将原始内容的字节数组压缩成赫夫曼编码byte[]
     * @param content 原始内容字节数组
     * @param huffmanCode 赫夫曼编码表
     * @return 赫夫曼编码byte[]
     */
    public static byte[] zip(byte[] content,Map<Byte,String> huffmanCode){
        if (content == null || content.length == 0) {
            return null;
        }
        //将字节数组转成霍夫曼编码的二进制
        StringBuilder sb = new StringBuilder();
        for (byte b : content) {
            sb.append(huffmanCode.get(b));
        }
        int len = 0;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        }else{
            len = sb.length() / 8 + 1;
        }
        byte[] b = new byte[len];
        int index = 0;
        //将赫夫曼编码八个一位存储在byte[]中
        for (int i=0;i<sb.length();i+=8){
            String strByte;
            if (i + 8 < sb.length()) {
                strByte = sb.substring(i,i + 8);
            }else {
                strByte = sb.substring(i);
            }
            b[index] = (byte) Integer.parseInt(strByte,2);
            index++;
        }
        return b;
    }
    public static Map<Byte,String> getCodes(Node node){
        if (node == null) {
            return null;
        }else{
            getCodes(node,"",stringBuilder);
        }
        return huffmanCode;
    }
    /**
     * 获取霍夫曼编码表
     * @param node 霍夫曼树的根节点
     * @param code 遍历路径，向左遍历为1，向右为0
     * @param stringBuilder 存储遍历路径
     * @return 霍夫曼编码表
     */
    public static Map<Byte,String> getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder sb = new StringBuilder(stringBuilder);
        sb.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left,"0",sb);
                getCodes(node.right,"1",sb);
            }else {
                huffmanCode.put(node.data,sb.toString());
            }
        }
        return huffmanCode;
    }

    /**
     * 统计每个字符出现的次数，存储在一个map集合中，以map的形式返回
     * @param bytes 原始内容的字节数组
     * @return 存储每个字节及字节出现次数的map集合
     */
    public static Map<Byte,Integer> getCount(byte[] bytes){
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        Map<Byte,Integer> map = new HashMap<>();
        for (byte b : bytes) {
            if (map.get(b) != null) {
                int value = map.get(b);
                value++;
                map.put(b,value);
            }else{
                int value = 1;
                map.put(b,value);
            }
        }
        return map;
    }

    /**
     * 将所有字节信息存储在Node对象中，并以List集合返回
     * @param map
     * @return
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
     * 构建霍夫曼树
     * @param nodes 需要构建霍夫曼树的结点
     * @return 返回霍夫曼树根节点
     */
    public static Node createHuffmanTree(List<Node> nodes){
        if (nodes == null || nodes.size() == 0) {
            return null;
        }
        while (nodes.size() > 1) {
            //每次操作完都要按从小到大的顺序存储
            Collections.sort(nodes);
            //去取集合中权重最小和次小的两个结点构建成一个霍夫曼树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //父节点权重为左右两结点权重之和
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //删除取出的两个结点，并把新生成的父节点保存在list集合中
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