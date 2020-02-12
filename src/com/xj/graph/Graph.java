package com.xj.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<String> vertexList;//存储顶点
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//存储边的数目
    private boolean isVisited[];
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        String[] vertexs = {"A","B","C","D","E"};
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1); //
		graph.insertEdge(1, 2, 1); //
		graph.insertEdge(1, 3, 1); //
		graph.insertEdge(1, 4, 1); //
        graph.showGraph();
        boolean[] flag = new boolean[5];
        System.out.println(flag[0]);
        graph.bfs();
    }
    public Graph(int n){
        vertexList = new ArrayList<>();
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 获取第一个邻接结点的下标
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index){
        for (int i=0;i<getNumOfVertex();i++){
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }
    public int getNextNeighbor(int v1,int v2){
        for (int i=v2+1;i<getNumOfEdges();i++){
            if (edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    public void dfs(){
        for (int i=0;i<getNumOfVertex();i++){
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }
    public void bfs(){
        for (int i=0;i<getNumOfVertex();i++){
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
    /**
     * 深度优先遍历
     * @param isVisited
     * @param i
     */
    private void dfs(boolean isVisited[],int i){
        System.out.println(vertexList.get(i));
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while(w != -1){
            if (!isVisited[w]){
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    /**
     * 广度优先遍历：遍历完当前节点可以直接访问的所有结点
     * @param isVisited 是否已经遍历
     * @param i 当前遍历索引
     */
    public void bfs(boolean isVisited[],int i){
        int u;
        int w;
        //存储以经遍历索引的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //出队列，访问此节点的邻接结点
            u = queue.removeFirst();
            //当前遍历结点的第一个邻接结点
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u,w);
            }
        }
    }
    /**
     * 返回以v1，v2为下标的结点的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    /**
     * 显示图对应的矩阵
     */
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }
    /**
     * 返回结点下标对应的数据
     * @param index
     * @return
     */
    public String getValueByIndex(int index){
        return vertexList.get(index);
    }
    /**
     * 返回顶点个数
     * @return
     */
    public int getNumOfVertex(){
        return vertexList.size();
    }
    /**
     * 返回图的边的数目
     * @return
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }
    /**
     * 添加顶点
     * @param vertex
     */
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 第一个点的下标 A=>0,B=>1,C=>2,D=>3,E=>4
     * @param v2 第二个点的下标
     * @param weight
     */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
