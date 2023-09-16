package com.maximum.binarysorttree;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.binarysorttree
 * @Author: maximum
 * @CreateTime: 2023-09-16
 * @Description: TODO
 * @Version: 1.0
 */

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~~~");
        binarySortTree.infixOrder();

        //测试删除叶子结点
        //binarySortTree.delNode(2);
        binarySortTree.delNode(1);
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}
//创建二叉排序树
class BinarySortTree{
    private Node root;

    //查找要删除的结点
    public Node search(int value){
        if(root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    //编写方法
    //1. 返回的以node为根结点的二叉排序树的最小结点的值
    //2. 删除node为根结点的二叉排序树的最小结点
    /**
     *
     * @param node 传入的结点(当做二叉排序树的根结点)
     * @return 返回的 以node 为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左子结点,就会找到最小值
        while(target.left != null){
            target = target.left;
        }
        //这时 target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value){
        if(root == null){
            return;
        }else{
            //1. 先去找到要删除的结点 targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的结点
            if(targetNode == null){
                return;
            }
            //如果我们发现当前这棵二叉排序树只有一个结点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }

            //去找到targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode 是父结点的左子结点, 还是右子结点
                if(parent.left != null && parent.left.value == value){ //是左子结点
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){ //是右子结点
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){ //删除有两棵子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else{ //删除只有一棵子树的结点
                //如果要删除的结点有左子结点
                if(targetNode.left != null){
                    if(parent != null){
                        //如果 targetNode 是 parent 的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        }else{ //targetNode 是 parent 的右子结点
                            parent.right = targetNode.left;
                        }
                    }else{
                        root = targetNode.left;
                    }
                }else{ //如果要删除的结点是右子结点
                    if(parent != null){
                        //如果 targetNode 是 parent 的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        }else{ //如果 targetNode 是 parent的右子结点
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    //添加结点的方法
    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    //遍历方法
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("二叉排序树为空, 不能遍历");
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的结点
    /**
     *
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点, 否则返回null
     */
    public Node search(int value){
        if(value == this.value){ //找到就是该结点
            return this;
        }else if(value < this.value){ //如果查找的值小于当前结点, 向左子树递归查找
            //如果左子结点为空
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        }else{ //如果查找的值大于当前节点, 向右子树递归查找
            //如果右子结点为空
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除结点的父结点
    /**
     *
     * @param value 要找到的结点的值
     * @return 返回的是要删除结点的父结点, 如果没有就返回null
     */
    public Node searchParent(int value){
        //如果当前结点就是要删除的结点的父结点, 就返回
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        }else{
            //如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
            if(value < this.value && this.left != null){
                return this.left.searchParent(value); //向左子树递归查找
            }else if(value >= this.value && this.right != null){
                return this.right.searchParent(value); //向右子树递归查找
            }else{
                return null; //没有找到
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加节点的方法
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断传入的结点的值, 和当前子树的跟结点的值关系
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                //递归向左子树添加
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}