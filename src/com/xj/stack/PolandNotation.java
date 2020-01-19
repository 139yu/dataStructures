package com.xj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String suffixExpression = "1+((2+3))*4-5";
        List<String> list = toInfixExpressionList(suffixExpression);
        System.out.println(list);
        System.out.println(calculate(parseSuffixExpressionList(list)));
    }

    /**
     * 将中缀表达式转换为后缀表达式
     * 1.初始化两个栈：运算符栈s1和储存中间结果的栈s2；
     *      2.从左至右扫描中缀表达式；
     *      3.遇到操作数时，将其压s2；
     *      4.遇到运算符时，比较其与s1栈顶运算符的优先级：
     *      (1)如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
     *      (2)否则，若优先级比栈顶运算符的高，也将运算符压入s1；
     *      (3)否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
     *      5遇到括号时：
     *      (1) 如果是左括号“(”，则直接压入s1
     *      (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
     *      6.重复步骤2至5，直到表达式的最右边
     *      7.将s1中剩余的运算符依次弹出并压入s2
     *      8.依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> ls){
        if (ls == null || ls.isEmpty()){
            return null;
        }
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item : ls) {
            if (item.matches("\\d+")){
                s2.add(item);
            }else if ("(".equals(item)){
                s1.push(item);
            }else if (")".equals(item)){
                while (!"(".equals(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else {
                while (!s1.isEmpty() && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }
        return s2;
    }
    /**
     * 将后缀表达式转换为list
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression){
        String[] strings = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele: strings){
            list.add(ele);
        }
        return list;
    }

    /**
     * 将中缀表达式转换为对应的list
     * @param expression
     * @return
     */
    public static List<String> toInfixExpressionList(String expression){
        System.out.println(expression.length());
        if (expression == null || expression.length() <= 0){
            return null;
        }
        List<String> list = new ArrayList<>();
        int index = 0;//用于遍历字符串
        StringBuilder str = new StringBuilder();//存放多位数
        char c = ' ';//存放遍历的字符
        do {
            if ((c = expression.charAt(index)) < 48 || (c = expression.charAt(index)) > 57){
                list.add(""+c);
                index++;
            }else {
                if (str == null) {
                    str = new StringBuilder();
                }
                if ((index+1)<expression.length()){
                    if (expression.charAt(index + 1) < 48 || expression.charAt(index + 1) > 57){
                        str.append(c);
                        list.add(str.toString());
                        index++;
                        str = null;
                    }else {
                        str.append(c);
                        index++;
                    }
                }else {
                    str.append(c);
                    list.add(str.toString());
                    index++;
                }

            }
        }while (index <= expression.length()-1);
        return list;
    }

    /**
     *
     * @param list
     * @return 逆波兰表达式运算的值
     */
    public static int calculate(List<String> list){
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")){
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)){
                    res = num1 + num2;
                }else if ("-".equals(item)){
                    res = num1 - num2;
                }else if ("*".equals(item)){
                    res = num1 * num2;
                }else if ("/".equals(item)){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符非法");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation){
        int res = -1;
        switch (operation){
            case "+":res =  ADD;break;
            case "-":res =  SUB;break;
            case "*":res =  MUL;break;
            case "/":res =  DIV;break;
            default:
                System.out.println("不存在该运算符");break;
        }
        return res;
    }
}