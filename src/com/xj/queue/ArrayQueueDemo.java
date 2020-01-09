package com.xj.queue;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        queue.addQueue(1);
        queue.addQueue(3);
        queue.addQueue(5);
        queue.showQueue();
    }
}
class ArrayQueue{
    private int front;//队头
    private int rear;//队尾
    private int maxSize;//数组最大容量
    private int[] arr;//存放数据模拟队列

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }
    //判断队列是否满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //出队
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队空，无法取出数据");
        }
        front++;//队头往后移动一位
        return arr[front];
    }
    //入队
    public void addQueue(int n){
        if (isFull()){
            System.err.println("队满,无法存储数据");
            return;
        }
        rear++;//队尾往后移一位
        arr[rear] = n;
    }
    //查看队头
    public int getQueueHead(){
        if (isEmpty()){
            throw new RuntimeException("队空队空，无法取出数据");
        }
        return arr[front + 1];
    }
    //显示队列数据
    public void showQueue(){
        while (!isEmpty()){
            System.out.println(getQueue());
        }
    }
}
