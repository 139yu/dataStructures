package com.xj.hashtable;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Emp jon = new Emp(1, "Jon");
        Emp tom = new Emp(2, "tom");
        Emp tank = new Emp(45, "tank");
        Emp andy = new Emp(434, "andy");
        hashTable.add(jon);
        hashTable.add(tom);
        hashTable.add(tank);
        hashTable.add(andy);
        hashTable.delete(2);
        hashTable.findEmpById(45);
        hashTable.list();
    }
}
class HashTable{
    private EmpLinkedList empLinkedList[] = null;
    private int size;
    public HashTable(int size){
        this.size = size;
        empLinkedList = new EmpLinkedList[size];
        for (int i=0;i<empLinkedList.length;i++){
            empLinkedList[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     * @param emp
     */
    public void add(Emp emp) {
        if (emp == null) {
            System.out.println("非法参数");
            return;
        }
        int empLinkedListNO = hashFun(emp.id);
        empLinkedList[empLinkedListNO].add(emp);
    }

    /**
     * 根据id删除雇员
     * @param id
     */
    public void delete(int id){
        int empLinkedListNO = hashFun(id);
        empLinkedList[empLinkedListNO].delete(id);
    }

    /**
     * 打印雇员信息
     */
    public void list() {
        for (int i=0;i<size;i++){
            empLinkedList[i].list(i);
        }
    }

    /**
     * 根据id查找雇员
     * @param id 雇员ID
     */
    public void findEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedList[empLinkedListNO].findEmpById(id);
        if (emp == null) {
            System.out.println("不存在该雇员");
        }else {
            System.out.println("该雇员在第" + (empLinkedListNO + 1) + "条链中，该雇员信息为：id=>" + emp.id + " name=>" + emp.name);
        }
    }
    public int hashFun(int id){
        int empLinkedListNO = id % size - 1;
        if (empLinkedListNO >= 0) {
            return empLinkedListNO;
        }else {
            return size - 1;
        }
    }
}
class Emp{
    int id;
    String name;
    Emp nextEmp = null;
    public Emp(int id,String name){
        this.name = name;
        this.id = id;
    }
}
class EmpLinkedList{
    private Emp head = null;

    /**
     * 添加雇员
     * @param emp
     */
    public void add(Emp emp){
        if (emp == null) {
            throw new RuntimeException("非法参数");
        }
        if (head == null) {
            head = emp;
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp.nextEmp == null) {
                break;
            }
            temp = temp.nextEmp;
        }
        temp.nextEmp = emp;
    }

    /**
     * 打印雇员信息
     * @param no 雇员编号
     */
    public void list(int no){
        if (head == null) {
            System.out.println("第"+ (no + 1) + "条链表为空");
            return;
        }
        Emp temp = head;
        System.out.print("第"+ (no + 1) + "条链表信息为");
        while (true) {
            System.out.print("=> id:" + temp.id + ",name:" + temp.name);
            if (temp.nextEmp == null) {
                break;
            }
            temp = temp.nextEmp;
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     * @param id 雇员id
     * @return
     */
    public Emp findEmpById(int id){
        if (head == null) {
            return null;
        }
        Emp emp = null;
        Emp temp = head;
        while(true){
            if (temp.id == id){
                emp = temp;
                break;
            }
            if (temp.nextEmp == null) {
                break;
            }
            temp = temp.nextEmp;
        }
        return emp;
    }

    /**
     * 根据id删除雇员
     * @param id 雇员id
     */
    public void delete(int id){
        if (head == null) {
            System.out.println("该链表为空");
            return;
        }
        if (head.id == id) {
            head = head.nextEmp;
            return;
        }
        Emp temp = head;
        //判断是否找到该雇员
        boolean flag = false;
        while (true) {
            if (temp.nextEmp == null) {
                break;
            }
            if (temp.nextEmp.id == id){
                flag = true;
                break;
            }
            temp = temp.nextEmp;
        }
        if (flag){
            temp.nextEmp = temp.nextEmp.nextEmp;
        }else {
            System.out.println("不存在该雇员");
        }
    }
}
