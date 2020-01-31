package com.xj.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "jon");
        Node node2 = new Node(3, "tony");
        Node node3 = new Node(8, "jack");
        Node node4 = new Node(10, "akl");
        Node node5 = new Node(6, "mike");
        Node node6 = new Node(14, "mark");
        node1.setLeft(node2);
        node1.setRight(node5);
        node2.setLeft(node3);
        node2.setRight(node4);
        node5.setLeft(node6);
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(node1);
        //threadedBinaryTree.infixThreadedNodes(node1);
        //threadedBinaryTree.infixThreadedList();
        //threadedBinaryTree.preThreadedNodes(node1);
        threadedBinaryTree.postThreadedNodes(node1);
    }
}
class ThreadedBinaryTree{
    private Node root = null;
    private Node pre = null;


    public ThreadedBinaryTree(Node root) {
        this.root = root;
    }

    /**
     * 打印前序线索化二叉树
     */
    public void preThreadedList(){
        Node node = this.root;
        while (node != null) {
            System.out.println(node);
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
    /**
     * 打印中序线索化二叉树
     */
    public void infixThreadedList(){
        Node node = this.root;
        while (node != null) {
            //找到leftType为1的结点，然后输出
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //当结点的右指针一直为它的后继节点时，就一直输出它的有结点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //当节点的rightType为0时，就正常遍历右节点
            node = node.getRight();
        }
    }

    /*
    public void postThreadedList(){
        if (this.root == null) {
            return;
        }
        Node node = root;
        //找到开始遍历的结点
        while (node != null && node.getLeftType() == 0) {
            node = node.getLeft();
        }
        //线索化时pre是有值的，要将pre置空
        pre = null;
        while (node != null) {
            if (node.getRightType() == 1) {
                System.out.println(node);
                pre = node;
                node = node.getRight();
            }
        }
    }*/
    /**
     * 前序线索化
     * @param node 根节点
     */
    public void preThreadedNodes(Node node){
        if (node == null) {
            return;
        }
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //处理左子树
        if (node.getLeftType() == 0) {
            preThreadedNodes(node.getLeft());
        }
        if (node.getRightType() == 0) {
            preThreadedNodes(node.getRight());
        }
    }
    /**
     * 中序线索化
     * @param node
     */
    public void infixThreadedNodes(Node node){
        if (node == null) {
            return;
        }
        //线索化左子树
        infixThreadedNodes(node.getLeft());
        //线索化左节点
        if (node.getLeft() == null){
            //设置当前节点的左指针为它的前驱节点
            node.setLeft(pre);
            //设置当前节点的左指针类型为前驱节点
            node.setLeftType(1);
        }
        //将当前节点的前驱结点设置为当前节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //设置当前节点为下一个结点的前驱节点
        pre = node;
        //线索化右子树
        infixThreadedNodes(node.getRight());
    }


    /**
     * 后序线索化
     * @param node
     */
    public void postThreadedNodes(Node node){
        if (node == null) {
            return;
        }
        postThreadedNodes(node.getLeft());
        postThreadedNodes(node.getRight());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
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
    public Node preOrderSearch(int no){
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    public Node infixOrderSearch(int no){
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }
    public Node postOrderSearch(int no){
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        }else {
            return null;
        }
    }

}
class Node{
    private int no;
    private String name;
    private Node left;
    private Node right;
    //节点类型，0为左节点，1为前驱节点
    private int leftType;
    //节点类型，0为右节点，1为后继节点
    private int rightType;
    //父节点
    private Node parent = null;

    public Node(int no,String name){
        this.no = no;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Node{" +
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

    public Node getLeft() {
        return left;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void delete(int no){
        if (this.left != null && this.left.getNo() == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.getNo() == no) {
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
    public Node preOrderSearch(int no){
        if (this.no == no) {
            return this;
        }
        Node resNode = null;
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
    public Node infixOrderSearch(int no){
        Node resNode = null;
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
    public Node postOrderSearch(int no){
        Node resNode = null;
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
