package com.xj.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "吴用", "智多星");
        HeroNode heroNode3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode heroNode4 = new HeroNode(4, "鲁智深", "忘记了");
        SingleLinkedList linkedList1 = new SingleLinkedList();
        linkedList1.addOrderBy(heroNode1);
        linkedList1.addOrderBy(heroNode2);
        linkedList1.addOrderBy(heroNode3);
        linkedList1.addOrderBy(heroNode4);
        HeroNode Node1 = new HeroNode(45, "宋江", "及时雨");
        HeroNode Node2 = new HeroNode(75, "吴用", "智多星");
        HeroNode Node3 = new HeroNode(7, "林冲", "豹子头");
        HeroNode Node4 = new HeroNode(8, "鲁智深", "忘记了");
        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.addOrderBy(Node1);
        linkedList2.addOrderBy(Node2);
        linkedList2.addOrderBy(Node3);
        linkedList2.addOrderBy(Node4);
        SingleLinkedList singleLinkedList = SingleLinkedList.connectLinkedList(linkedList1, linkedList2);
        HeroNode headNode = singleLinkedList.getHeadNode();
        HeroNode cur = headNode.nextNode;
        while (cur.nextNode != null) {
            System.out.println(cur);
            cur = cur.nextNode;
        }
    }
    public static void change(int n){
        n = 3;
    }
}

class SingleLinkedList{
    //头结点，不存放任何数据
    private HeroNode headNode = new HeroNode(0,null,null);

    public HeroNode getHeadNode() {
        return headNode;
    }
    //合并两个有序列表，并且合并之后依然有序
    public static SingleLinkedList connectLinkedList(SingleLinkedList linkedList1,SingleLinkedList linkedList2){
        HeroNode cur1 = linkedList1.getHeadNode().nextNode;
        HeroNode cur2 = linkedList2.getHeadNode().nextNode;
        SingleLinkedList linkedList = new SingleLinkedList();
        HeroNode cur = linkedList.getHeadNode();
        //记录当前节点的下一个结点地址
        HeroNode temp = null;
        while(cur1 != null && cur2 != null){
            if (cur1.no <= cur2.no) {
                temp = cur1.nextNode;
                cur.nextNode = cur1;
                cur.nextNode.nextNode = null;
                cur1 = temp;
            }else {
                temp = cur2.nextNode;
                cur.nextNode = cur2;
                cur.nextNode.nextNode = null;
                cur2 = temp;
            }
            cur = cur.nextNode;
        }
        while(cur1 != null){
            temp = cur1.nextNode;
            cur.nextNode = cur1;
            cur = cur.nextNode;
            cur1 = temp;
        }
        while(cur2 != null){
            temp = cur2.nextNode;
            cur.nextNode = cur2;
            if(cur.nextNode != null){
                cur.nextNode.nextNode = null;
            }
            cur = cur.nextNode;
            cur2 = temp;
        }
        System.out.println(1);
        return linkedList;
    }
    //反转结点
    public static void reverseNode(HeroNode headNode){
        //如果当前链表为空或只有一个结点无需反转
        if(headNode.nextNode == null && headNode.nextNode.nextNode == null){
            return ;
        }
        HeroNode cur = headNode.nextNode;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,null,null);
        while (cur != null) {
            next = cur.nextNode;//暂时保存下一个结点的地址
            cur.nextNode = reverseHead.nextNode;//插入到新链表的最前端
            reverseHead.nextNode = cur;
            //cur指针后移
            cur = next;
        }
        headNode.nextNode = reverseHead.nextNode;
    }
    //逆序打印链表信息
    public static void reversePrint(HeroNode headNode){
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = headNode.nextNode;
        while (cur != null){
            stack.push(cur);
            cur = cur.nextNode;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
    //有效结点个数
    public static int size(HeroNode headNode){
        int count = 0;
        if(headNode.nextNode == null){
            return count;
        }
        HeroNode temp = headNode.nextNode;
        while (true){
            count++;
            if(temp.nextNode == null){
                break;
            }
            temp = temp.nextNode;
        }
        return count;
    }
    //查找倒数第n个结点
    public static HeroNode findLastIndexNode(HeroNode headNode,int n){
        int index = size(headNode) - n;
        if (index <=0){
            return null;
        }
        HeroNode temp = headNode.nextNode;
        for (int i=0;i<index;i++){
            temp = temp.nextNode;
        }
        return temp;
    }
    //添加结点，不考虑序号顺序
    public void add(HeroNode heroNode){
        HeroNode temp = headNode;
        while (true){
            //判断链表是否为空
            if(temp.nextNode == null){
                break;
            }
            temp = temp.nextNode;
        }
        temp.nextNode = heroNode;
    }
    //按编号顺序插入结点,若编号存在则不插入
    public void addOrderBy(HeroNode heroNode){
        HeroNode temp = headNode;
        boolean flag = false;//判断编号是否存在
        while (true){
            //判断链表是否为空
            if (temp.nextNode == null){
                break;
            }
            if (temp.nextNode.no == heroNode.no){
                flag = true;
                break;
            }else if (temp.nextNode.no > heroNode.no){
                break;
            }
            temp = temp.nextNode;
        }
        if(flag){
            System.err.println(heroNode.no + "编号已存在");
            return;
        }
        heroNode.nextNode = temp.nextNode;
        temp.nextNode = heroNode;
    }
    //根据no编号修改结点信息
    public void update(int no,String name,String nickName){
        //判断是否找到该结点
        boolean flag = false;
        //判断链表是否为空
        if(headNode.nextNode == null){
            System.err.println("链表为空");
            return;
        }
        HeroNode temp = headNode.nextNode;
        while(true){
            if(temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.nextNode;
        }
        if(flag){
            temp.name = name;
            temp.nickName = nickName;
        }else {
            System.err.println("不存在此节点");
        }
    }
    //根据no编号删除结点
    public void delete(int no){
        boolean flag = false;//判断是否找到该节点
        if(headNode.nextNode == null){
            System.err.println("链表为空");
            return;
        }
        HeroNode temp = headNode;
        while (true){
            if(temp.nextNode == null){
                break;
            }
            if(temp.nextNode.no == no){
                flag = true;
                break;
            }
            temp = temp.nextNode;
        }
        if (flag){
            temp.nextNode = temp.nextNode.nextNode;
        }else {
            System.err.println("该节点不存在");
        }
    }

    public void showLinkedList(){
        if(headNode.nextNode == null){
            System.err.println("链表为空");
            return;
        }
        HeroNode temp = headNode;
        while (true){
            if(temp.nextNode == null){
                break;
            }
            temp = temp.nextNode;
            System.out.println(temp);
        }
    }
}

class HeroNode{
    public int no;//序号
    public String name;//英雄名字
    public String nickName;//英雄昵称
    public HeroNode nextNode;//指向下一个结点

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    //构造器
    public HeroNode(int no,String name,String nickName){
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
}
