package com.xj.daastructures.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(3);
        stack.push(1);
        stack.push(4);
        stack.push(6);
        System.out.println(stack.pop());
        stack.push(3);
        stack.printStack();
    }
}
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈，数据就放在数组中
    private int top = -1;//栈顶

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     *
     * @return 是否栈满
     */
    public boolean isFull(){
        return top == maxSize - 1;
    }

    /**
     *
     * @return 是否栈空
     */
    public boolean isEmpty(){
        return top == -1;
    }

    /**
     * 入栈
     * @param value 压入栈的值
     */
    public void push(int value){
        if (isFull()){
            System.err.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return 弹出栈的数据
     */
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top--];
        return value;
    }

    public void printStack(){
        if (isEmpty()){
            System.err.println("栈空");
            return;
        }
        for (int i=top;i>=0;i--){
            System.out.println(stack[i]);
        }
    }
}
