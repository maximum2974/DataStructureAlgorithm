package com.maximum.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @BelongsProject: DataStructureAlgorithm
 * @BelongsPackage: com.maximum.graph
 * @Author: maximum
 * @CreateTime: 2023-09-17
 * @Description: TODO
 * @Version: 1.0
 */

public class Graph {
    private ArrayList<String> vertexList; //存储结点集合
    private int[][] edges; //存储图对应的邻接矩阵
    private int numberOfEdges; //表示边的数目
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;
    public static void main(String[] args) {
        int n = 5;//结点的个数
        String vertexValue[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环的添加顶点
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdges(0,1,1);
        graph.insertEdges(0,2,1);
        graph.insertEdges(1,2,1);
        graph.insertEdges(1,3,1);
        graph.insertEdges(1,4,1);

        //显示邻接矩阵
        graph.showGraph();

        //测试dfs
        /*System.out.println("深度遍历");
        graph.dfs(); //A->B->C->D->E*/

        //测试bfs
        System.out.println();
        System.out.println("广度优先");
        graph.bfs();
    }

    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numberOfEdges = 0;
        isVisited = new boolean[5];
    }

    //得到第一个邻接结点的下标w
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1,int v2){
        for(int j = v2 + 1; j < vertexList.size(); j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    private void dfs(boolean[] isVisited, int i){
        //首先我们访问该结点, 输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点
        int w = getFirstNeighbor(i);
        while(w != -1){ //说明有
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs 进行一个回溯, 遍历我们所有的结点, 并进行 dfs
    public void dfs(){
        //遍历所有的结点, 进行dfs[回溯]
        for(int i = 0; i < getNumberOfVertex(); i++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        }
    }

    //对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i){
        int u; // 表示队列的头结点对应的下标
        int w; // 邻接结点w
        //队列: 记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点, 输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);

        while(! queue.isEmpty()){
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标w
            w = getFirstNeighbor(u);
            while(w != -1){ //找到
                //是否访问
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u 为前驱点, 找w后面的下一个邻接点
                w = getNextNeighbor(u, w); //体现出广度优先
            }
        }
    }

    //遍历所有的结点, 都进行广度优先搜索
    public void bfs(){
        for(int i = 0; i < getNumberOfVertex(); i++){
            if(! isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumberOfVertex(){
        return vertexList.size();
    }

    //显示图对应的矩阵
    public void showGraph(){
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumberOfEdges(){
        return numberOfEdges;
    }

    //返回结点i(下标)对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    /**
     *
     * @param v1 表示点的下标即第几个顶点
     * @param v2 第二个顶点对应的下标
     * @param weight 表示权值
     */
    public void insertEdges(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numberOfEdges++;
    }
}
