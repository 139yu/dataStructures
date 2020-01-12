package com.xj.linkedlist;
/*
* 约瑟夫问题
* */
public class Josephu {
    public static void main(String[] args) {
        Boy boy1 = new Boy(1);
        Boy boy2 = new Boy(2);
        Boy boy3 = new Boy(3);
        Boy boy4 = new Boy(4);
        Boy boy5 = new Boy(5);
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.add(boy1);
        linkedList.add(boy2);
        linkedList.add(boy3);
        linkedList.add(boy4);
        linkedList.add(boy5);
        linkedList.countBoy(2,2);
    }
}
class CircleSingleLinkedList{
    private Boy first = null;
    //添加结点
    public void add(Boy boy){
        if (first == null) {
            first = boy;
            first.nextBoy = first;
            return;
        }
        Boy temp = first;
        while (true) {
            if (temp.nextBoy == first){
                break;
            }
            temp = temp.nextBoy;
        }
        boy.nextBoy = temp.nextBoy;
        temp.nextBoy = boy;
    }
    public void printList(){
        if (first == null) {
            System.err.println("该链表为空");
        }
        Boy temp = first;
        do {
            System.out.println(temp);
            temp = temp.nextBoy;
        }while (temp != first);
    }

    /**
     * 约瑟夫问题
     * @param startNo 开始报数的位置
     * @param countNum 数几下
     */
    public void countBoy(int startNo,int countNum){
        if (first == null || startNo > size() || countNum < 0 || startNo < 0){
            System.err.println("参数非法");
        }
        //辅助指针，一直指向开始结点的前一个结点，帮助小孩完成出圈
        Boy helper = first;
        while (true) {
            if(helper.nextBoy == first){
                break;
            }
            helper = helper.nextBoy;
        }
        //报数前先将first移动到开始结点
        for (int i=0;i<startNo-1;i++){
            first = first.nextBoy;
            helper = helper.nextBoy;
        }
        //开始报数
        while (true){
            if(helper == first){
                break;
            }
            for (int i=0;i<startNo-1;i++){
                first = first.nextBoy;
                helper = helper.nextBoy;
            }
            System.out.println("boy " + first.no + "has been killed");
            helper.nextBoy = first.nextBoy;
            first = first.nextBoy;
        }
        System.out.println("boy" + first.no + "is alive");
    }

    /**
     *
     * @return 有效结点个数
     */
    public int size(){
        int count = 0;
        if (first == null) {
            return count;
        }
        Boy temp = first;
        while (true) {
            count++;
            if(temp.nextBoy == first){
                break;
            }
            temp = temp.nextBoy;
        }
        return count;
    }
}
class Boy{
    public int no;
    public Boy nextBoy;
    public Boy(int no){
        this.no = no;
    }
    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNextBoy() {
        return nextBoy;
    }

    public void setNextBoy(Boy nextBoy) {
        this.nextBoy = nextBoy;
    }
}
