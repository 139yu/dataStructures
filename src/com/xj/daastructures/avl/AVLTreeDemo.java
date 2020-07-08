package com.xj.daastructures.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int arr[] = {4,3,6,5,7,8};
        //int arr[] = {10,12,8,9,7,6};
        int arr[] = {2,1,6,5,7,3};
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().leftHeight());
        avlTree.infixOrder();
    }
}
class AVLTree{
    private Node root = null;
    public Node getRoot(){
        return this.root;
    }
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
        System.out.println();
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
     * 将二叉树向右旋转
     */
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }
    /**
     * 将二叉树向左旋转
     */
    public void leftRotate(){
        //创建一个新的结点，使它的值等于当前节点的值
        Node newNode = new Node(value);
        //将新节点的左子树设置成当前节点的左子树
        newNode.left = left;
        //将新节点的右子树设置成当前节点右子树的左子树
        newNode.right = right.left;
        //将当前节点的值设置成右子节点的值
        value = right.value;
        //将当前节点的右子节点设置成当前节点的右子节点的右子节点
        right = right.right;
        //将当前节点的左子节点设置成新节点
        left = newNode;
    }

    /**
     * 返回当前节点左子树高度
     * @return
     */
    public int leftHeight(){
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回当前节点右子树高度
     * @return
     */
    public int rightHeight(){
        if (right == null) {
            return 0;
        }
        return right.height();
    }
    /**
     * 返回以当前结点为根节点的二叉树的高度
     * @return
     */
    public int height(){
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height()) + 1;
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
        if (rightHeight() - leftHeight() > 1){
            //进行左旋转时，如果当前节点右节点的左子树高度大于当前节点有节点的右子树高度，要先将当前节点的右子树进行右旋转
            if (right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1){
            //进行右旋转时，如果当前节点左子节点的右子树高度大于当前节点左子节点的左子树，要先先将当前节点的左子树进行左旋转
            if (left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
                rightRotate();
            }else {
                rightRotate();
            }
            return;
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
