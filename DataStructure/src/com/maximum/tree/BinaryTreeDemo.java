package com.maximum.tree;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.tree
 * @Author: maximum
 * @CreateTime: 2023-09-11
 * @Description: TODO
 * @Version: 1.0
 */

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        System.out.println("前序遍历");
        binaryTree.preOrder();

        System.out.println("中序遍历");
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        binaryTree.postOrder();


        //前序遍历查找
        System.out.println("前序遍历方式~");
        HeroNode resNode = binaryTree.preOrderSearch(5);
        if(resNode != null){
            System.out.printf("找到了, 信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        }else{
            System.out.printf("没有找到 no=%d 的英雄", 5);
        }

        //中序遍历查找
        /*System.out.println("中序遍历方式~");
        HeroNode resNode = binaryTree.infixOrderSearch(5);
        if(resNode != null){
            System.out.printf("找到了, 信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        }else{
            System.out.printf("没有找到 no=%d 的英雄", 5);
        }*/

        //后序遍历查找
        /*System.out.println("后序遍历方式~");
        HeroNode resNode = binaryTree.postOrderSearch(5);
        if(resNode != null){
            System.out.printf("找到了, 信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        }else{
            System.out.printf("没有找到 no=%d 的英雄", 5);
        }*/
    }
}

//定义BinaryTree 二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }
}

//先创建HeroNode 结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left; //默认null
    private HeroNode right; //默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this); //先输出父结点
        //递归左子树
        if(this.left != null){
            this.left.preOrder();
        }
        //递归右子树
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //递归左子树
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归右子树
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //递归左子树
        if(this.left != null){
            this.left.postOrder();
        }
        //递归右子树
        if(this.right != null){
            this.right.postOrder();
        }
        //输出父结点
        System.out.println(this);
    }

    //前序遍历查找
    /**
     *
     * @param no 查找no
     * @return 如果找到就返回Node,如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历");
        //比较当前结点是不是
        if(this.no == no){
            return this;
        }
        //1. 则判断当前结点的左子结点是否为空,如果不为空,则递归前序查找
        //2. 如果左递归前序查找, 找到结点, 则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){ //说明左子树找到
            return resNode;
        }
        //1. 左递归前序查找, 找到结点, 则返回, 否则继续判断
        //2. 当前的结点的右子节点是否为空, 如果不空, 则继续向右递归前序查找
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        //判断当前结点的左子节点是否为空, 如果不为空, 则递归中序查找
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        System.out.println("进入中序查找");
        //如果找到, 则返回, 如果没有找到, 就和当前结点比较, 如果是则返回当前结点
        if(this.no == no){
            return this;
        }
        //否则继续进行右递归的中序查找
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        //判断当前结点的左子结点是否为空, 如果不为空, 则递归后序查找
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){ //说明左子树找到
            return resNode;
        }
        //如果左子树没有找到,则向右子树递归进行后序遍历查找
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        System.out.println("进行后序查找");
        //如果左右子树都没有找到, 就比较当前结点是不是
        if(this.no == no){
            return this;
        }

        return resNode;
    }
}
