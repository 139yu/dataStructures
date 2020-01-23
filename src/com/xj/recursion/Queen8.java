package com.xj.recursion;

public class Queen8 {
    int max = 8;
    int[] array = new int[max];
    static int count = 0;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(count);
    }
    public  void check(int n){
        if (n == max){
            print();
            return;
        }
        for (int i=0;i<max;i++){
            array[n] = i;
            if (judge(n)){
                check(n + 1);
            }
        }
    }
    //array[i] == array[n]判断是否是在同一列
    //Math.abs(i-n) == Math.abs(array[i] - array[n])判断是否是在同一斜线
    public boolean judge(int n){
        for (int i=0;i<n;i++){
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
    public void print(){
        for (int i=0;i<max;i++){
            System.out.print(array[i] + " ");
        }
        count++;
        System.out.println();
    }
}
