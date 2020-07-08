package com.xj.daastructures.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "20+4+1*5-5";
        int index = 0;//记录扫描字符串的位置
        int num1 = 0;
        int num2 = 0;
        int res = 0;//记录运算的结果
        int oper = ' ';//存储运算符
        char ch = ' ';//将每次扫描的字符存储
        StringBuilder keepNum = new StringBuilder();
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        while (true) {
            ch = expression.substring(index,index + 1).charAt(0);
            //如果是运算符将运算符存储在运算符栈中
            if (operStack.isOper(ch)){
                //如果运算符栈为空直接存储
                if (operStack.isEmpty()){
                    operStack.push(ch);
                }else {
                    //如果不为空，则比较当前运算符与栈顶的运算符的优先级，如果当前运算符小于或等于
                    //栈顶运算符，就在数栈取出两个数和运算符栈顶运算符运算，将当前运算符入栈，运算结果入栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }
            }else {
                if (keepNum == null) {
                    keepNum = new StringBuilder();
                }
                keepNum.append(ch);
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum.toString()));
                }else {
                    if (numStack.isOper(expression.substring(index + 1,index + 2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        keepNum = null;
                    }
                }
            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println(expression + "=" + numStack.pop());
    }
}
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//用数组模拟栈，数据就放在数组中
    private int top = -1;//栈顶

    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 查看栈顶数据
     * @return 返回栈顶数据
     */
    public int peek(){
        return stack[top];
    }

    /**
     *
     * @param ch
     * @return 判断是否是运算符
     */
    public boolean isOper(char ch){
        return ch == '-' || ch == '+' || ch == '/' || ch == '*';
    }

    /**
     *
     * @param oper
     * @return返回运算符优先级
     */
    public int priority(int oper){
        if (oper == '-' || oper == '+') {
            return 0;
        }else if (oper == '/' || oper == '*'){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     *
     * @param num1
     * @param num2
     * @param oper 运算符
     * @return 返回运算结果
     */
    public int cal(int num1,int num2,int oper){
        int res = 0;
        switch (oper){
            case '+':
                res = num1 + num2;break;
            case '-':
                res = num2 - num1;break;
            case '*':
                res = num1 * num2;break;
            case '/':
                res = num2 / num1;break;
            default:
                break;
        }
        return res;
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
