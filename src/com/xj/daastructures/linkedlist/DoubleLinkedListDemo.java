package com.xj.daastructures.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "不知道");
        Node node2 = new Node(4, "不值得");
        Node node3 = new Node(65, "不咋地");
        Node node4 = new Node(13, "包装袋");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addOrderBy(node1);
        linkedList.addOrderBy(node2);
        linkedList.addOrderBy(node3);
        linkedList.addOrderBy(node4);
        //linkedList.update(2,"不直达");
        //linkedList.delete(4);
        linkedList.printList(linkedList.getHeadNode());
    }
}
class DoubleLinkedList{
    private Node headNode = new Node(0,null);

    public Node getHeadNode() {
        return headNode;
    }
    //按照编号顺序添加结点
    public void addOrderBy(Node node){
        if (headNode.nextNode == null) {
            node.preNode = headNode;
            headNode.nextNode = node;
            return;
        }
        Node temp = headNode;
        boolean flag = false;//判断编号是否存在
        while (true) {
            if(temp.nextNode == null){
                break;
            }
            if(temp.nextNode.no == node.no){
                flag = true;
                break;
            }else if(temp.nextNode.no > node.no){
                break;
            }
            temp = temp.nextNode;
        }
        if (flag) {
            System.err.println("该节点已存在");
        }else {
            node.preNode = temp;
            node.nextNode = temp.nextNode;
            if (temp.nextNode != null) {
                temp.nextNode.preNode = node;
            }
            temp.nextNode = node;
        }
    }
    //删除结点
    public void delete(int no){
        if(headNode.nextNode == null){
            System.err.println("该链表为空");
            return;
        }
        Node temp = headNode.nextNode;
        //判断是否找到该节点
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.nextNode;
        }
        if(flag){
            temp.preNode.nextNode = temp.nextNode;
            if(temp.nextNode != null){
                temp.nextNode.preNode = temp.preNode;
            }
        }else {
            System.err.println("该节点不存在");
        }
    }
    //修改结点信息
    public void update(int no,String value){
        if(headNode.nextNode == null){
            System.err.println("链表表为空");
            return;
        }
        Node temp = headNode.nextNode;
        //判断是否找到该节点
        boolean flag = false;
        while (true){
            if(temp.nextNode == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.nextNode;
        }
        if (flag) {
            temp.value = value;
        }else {
            System.err.println("该节点不存在");
        }
    }
    //不考虑顺序添加结点
    public void addNode(Node node){
        if(headNode.nextNode == null){
            headNode.nextNode = node;
            node.preNode = headNode;
            return;
        }
        Node temp = headNode;
        while (true){
            if(temp.nextNode == null){
                break;
            }
            temp = temp.nextNode;
        }
        node.preNode = temp;
        temp.nextNode = node;
    }
    //打印链表
    public void printList(Node headNode){
        if (headNode.nextNode == null) {
            System.err.println("链表为空");
            return;
        }
        Node temp = headNode;
        while (temp.nextNode != null){
            temp = temp.nextNode;
            System.out.println(temp);
        }

    }
}
class Node{
    public int no;//序号
    public String value;//结点存储的数据
    public Node nextNode;//指向下一个结点
    public Node preNode;//指向前一个结点
    public Node(int no,String value){
        this.no = no;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", value='" + value + '\'' +
                '}';
    }
}
