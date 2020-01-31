package com.xj.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "林冲");
        HeroNode hero3 = new HeroNode(3, "李逵");
        HeroNode hero4 = new HeroNode(4, "鲁智深");
        HeroNode hero5 = new HeroNode(5, "吴用");
        root.setLeft(hero2);
        root.setRight(hero5);
        hero5.setLeft(hero4);
        hero5.setRight(hero3);
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.delete(2);
        binaryTree.preOrder();
    }
}
class BinaryTree{
    HeroNode root = null;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }
    public void delete(int no){
        if (this.root != null) {
            if (this.root.getNo() == no) {
                this.root = null;
            }else {
                this.root.delete(no);
            }
        }else {
            System.out.println("当前数为空");
        }
    }
    /**
     * 前序遍历
     */
    public void preOrder(){
        if (this.root != null) {
            this.root.preOrder();
        }else {
            System.out.println("当前二叉树为空");
        }
    }
    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.root != null) {
            this.root.infixOrder();
        }else {
            System.out.println("当前二叉树为空");
        }
    }
    /**
     * 后序遍历
     */
    public void portOrder(){
        if (this.root != null) {
            this.root.postOrder();
        }else {
            System.out.println("当前二叉树为空");
        }
    }
    public HeroNode preOrderSearch(int no){
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode infixOrderSearch(int no){
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    public HeroNode postOrderSearch(int no){
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
}
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no,String name){
        this.no = no;
        this.name = name;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public void delete(int no){
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delete(no);
        }
        if (this.right != null) {
            this.right.delete(no);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    /**
     * 后序遍历
     */
    public void postOrder(){
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no 编号
     * @return 对应编号的结点
     */
    public HeroNode preOrderSearch(int no){
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right .preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     * @param no 编号
     * @return 对应编号的结点
     */
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no){
            resNode = this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     * @param no 编号
     * @return 对应结点
     */
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            resNode = this;
        }
        return resNode;
    }
}
