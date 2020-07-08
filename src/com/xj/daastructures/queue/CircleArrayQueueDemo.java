package com.xj.daastructures.queue;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue circleQueue = new CircleArrayQueue(4);
        circleQueue.addQueue(1);
        circleQueue.addQueue(3);
        circleQueue.addQueue(6);
        System.out.println("队列数据：");
        circleQueue.showQueue();
        System.out.println(circleQueue.getQueue());
        circleQueue.addQueue(55);
        System.out.println("队列数据：");
        circleQueue.showQueue();

        System.out.println(circleQueue.getQueue());
        circleQueue.addQueue(55);
        System.out.println("队列数据：");
        circleQueue.showQueue();
    }
}
class CircleArrayQueue{
    private int front;//队头,就指向队列的第一个元素，初始值为0
    private int rear;//队尾，指向队列最后一个元素的后一个位置，初始值为0
    private int maxSize;//数组最大容量
    private int[] arr;//存放数据模拟队列

    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }
    //出队
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队空，无法取出数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //入队
    public void addQueue(int n){
        if (isFull()){
            System.err.println("队满,无法存储数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }
    //查看队头
    public int getQueueHead(){
        if (isEmpty()){
            throw new RuntimeException("队空队空，无法取出数据");
        }
        return arr[front];
    }
    //显示队列数据
    public void showQueue(){
        if(isEmpty()){
            System.err.println("队列为空，无法取出数据");
            return;
        }
        for (int i=front;i<front+size();i++){
            System.out.printf("arr[%d]:%d\t",i%maxSize,arr[i%maxSize]);
        }
    }
    //查看队列有效值个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }
}
