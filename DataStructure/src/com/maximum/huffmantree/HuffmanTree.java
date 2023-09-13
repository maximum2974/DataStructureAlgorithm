package com.maximum.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.huffmantree
 * @Author: maximum
 * @CreateTime: 2023-09-13
 * @Description: TODO
 * @Version: 1.0
 */

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr =  {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("是空树,不能遍历~~");
        }
    }

    //创建赫夫曼树的方法
    /**
     *
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好后的赫夫曼树的root结点
     */
    public static Node createHuffmanTree(int[] arr){
        //第一步为了操作方便
        //1. 遍历 arr 数组
        //2. 将arr的每个元素构成成一个node
        //3. 将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while(nodes.size() > 1){
            //排序从小到大
            Collections.sort(nodes);

            //取出根节点权值最小的两颗二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从 ArrayList 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            //将 parent 加入到 nodes
            nodes.add(parent);
        }

        //返回哈夫曼树的 root 结点
        return nodes.get(0);
    }
}

//创建结点类
//为了让Node 对象支持排序Collection集合排序
//让Node 实现Comparable接口
class Node implements Comparable<Node>{
    int value; //结点权值
    Node left; //指向左子结点
    Node right; //指向右子结点

    //前序遍历方法
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "Node [value=" + value + "]";
    }
    
    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}
