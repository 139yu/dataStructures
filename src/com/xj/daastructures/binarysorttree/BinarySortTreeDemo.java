package com.xj.daastructures.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int [] arr = {7,3,10,12,5,9,1};
        BinarySortTree binaryTree = new BinarySortTree();
        for (int i : arr) {
            binaryTree.add(new Node(i));
        }
        binaryTree.infixOrder();
        binaryTree.delNode(7);
        System.out.println("---------");
        binaryTree.infixOrder();
    }
}
class BinarySortTree{
    private Node root = null;
    public void add(Node node){
        if (root == null) {
            root = node;
        }else {
            root.add(node);
        }
    }
    public int delRightTree(Node node){
        Node temp = node;
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
    }
    /**
     * 删除结点
     * @param value 要删除结点的值
     */
    public void delNode(int value){
        if (root == null) {
            return;
        }
        Node target = root.search(value);
        if (target == null) {
            return;
        }
        //如果要删除的结点是根节点，并且没有子节点
        if (root.left == null && root.right == null){
            root = null;
        }
        Node parent = root.searchParent(value);
        //要删除的结点是叶子节点
        if (target.left == null && target.right == null) {
            //要删除结点是父节点的左子节点
            if (parent.left.value == target.value) {
                parent.left = null;
            } else {//要删除的结点是父节点的右子节点
                parent.right = null;
            }
        } else if (target.left != null && target.right == null
                && target.left.left == null && target.left.right == null) {//要删除结点只有一个左子节点
            if (parent != null) {
                if (parent.left.value == target.value) {
                    parent.left = target.left;
                } else {
                    parent.right = target.left;
                }
            }else {
                root = target.left;
            }
        } else if (target.left != null && target.right != null) {//删除的结点有两个子树结点
            int minValue = delRightTree(target.right);
            target.value = minValue;
        }else{//要删除结点只有一个右子节点
            if (parent != null) {
                if (parent.left.value == target.value) {
                    parent.left = target.right;
                } else {
                    parent.right = target.right;
                }
            }else {
                root = target.right;
            }
        }
    }
    public Node searchParent(int value){
        if (this.root == null) {
            return null;
        }
        return this.root.searchParent(value);
    }
    public Node search(int value){
        if (this.root == null) {
            return null;
        }
        return this.root.search(value);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this == null) {
            return;
        }
        root.infixOrder();
    }
}
class  Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找要删除的结点
     * @param value 要删除结点的值
     * @return
     */
    public Node search(int value){
        if (this == null) {
            return null;
        }
        if (this.value == value) {
            return this;
        } else if (this.value > value) {
            if (this.left != null) {
                return this.left.search(value);
            }else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            }else {
                return null;
            }
        }
    }

    /**
     * 查找要删除结点的父节点
     * @param value 要删除结点的值
     * @return
     */
    public Node searchParent(int value){
        if (this == null) {
            return null;
        }
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else {
            if (this.left != null && this.value > value) {
                return this.left.searchParent(value);
            }else if (this.right != null && this.value <= value){
                return this.right.searchParent(value);
            }else {
                return null;
            }
        }
    }
    /**
     * 添加结点
     * @param node
     */
    public void add(Node node){
        if (node == null) {
            return;
        }
        if (this.value > node.value){
            if (this.left == null) {
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null) {
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this == null) {
            return;
        }
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.print(this.value + " ");
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
