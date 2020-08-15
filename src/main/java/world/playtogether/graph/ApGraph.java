package world.playtogether.graph;

import world.playtogether.hashtable.ApHashTable;
import world.playtogether.list.ApSingleLinkedList;
import world.playtogether.queue.ApArrayQueue;
import world.playtogether.queue.ApSingleLinkedQueue;

/**
 * <project> algoPractice
 *
 * <p> 无向图
 *
 * @author penggs
 * @since 2020-08-15
 */
public class ApGraph<T> {
    // 顶点个数
    private int v;
    private ApHashTable<T, ApSingleLinkedList<T>> adj;
    private boolean found = false;

    public ApGraph() {
        this.v = 10;
        this.adj = new ApHashTable<>(10);
    }
    
    public ApGraph(int v) {
        this.v = v;
        this.adj = new ApHashTable<>(v);
    }

    /** 
     * 无向图添加边
     * 
     * @param s 起始点
	 * @param t 终点
     * @author penggs
     * @since 2020/8/15 
     */
    public void addEdge(T s, T t) {
        // 双向添加边
        ApSingleLinkedList<T> sLinkedList = adj.get(s);
        if (sLinkedList == null) {
            sLinkedList = new ApSingleLinkedList<>();
        }
        sLinkedList.insertToTail(t);
        adj.put(s, sLinkedList);
        ApSingleLinkedList<T> tLinkedList = adj.get(t);
        if (tLinkedList == null) {
            tLinkedList = new ApSingleLinkedList<>();
        }
        tLinkedList.insertToTail(s);
        adj.put(t, tLinkedList);
    }

    /**
     * 广度优先搜索,搜索到的路径是最短的
     *
     * @param s 起始点
	 * @param t 终点
     * @author penggs
     * @since 2020/8/15
     */
    public void bfs(T s, T t) {
        if (s.equals(t)) {
            return;
        }
        // 记录已访问的顶点
        ApHashTable<T, Boolean> visited = new ApHashTable<>();
        visited.put(s, Boolean.TRUE);
        // 记录已访问、但相连顶点还没被访问的顶点
        ApSingleLinkedQueue<T> queue = new ApSingleLinkedQueue<>();
        queue.enqueue(s);
        // 记录搜索路径,实际使用时记录的是当前顶点的前一顶点
        ApHashTable<T, T> prev = new ApHashTable<>();

        // 开始广度搜索
        while (!queue.isEmpty()) {
            T vertex = queue.dequeue();
            ApSingleLinkedList<T> vertexList = adj.get(vertex);
            ApSingleLinkedList.Node<T> p = vertexList.getHead();
            while (p != null) {
                // 顶点未被访问过
                T curData = p.getData();
                if (visited.get(curData) == null || !visited.get(curData)) {
                    // 记录路径
                    prev.put(curData, vertex);
                    if (curData.equals(t)) {
                        print(prev, s, t);
                        return;
                    }
                    visited.put(curData, Boolean.TRUE);
                    // 将顶点加入队列
                    queue.enqueue(curData);
                }
                p = p.getNext();
            }
        }
    }

    private void print(ApHashTable<T, T> prev, T s, T t) {
        // 因为是prev存储的是倒序的，所以反向输出
        T cur = prev.get(t);
        if (cur != null && !cur.equals(s)) {
            print(prev, s, cur);
        } else {
            System.out.print(s + " ");
        }
        System.out.print(t + " ");
    }

    /**
     * 深度优先搜索，搜索到的路径不一定是最短的
     *
     * @param s 起始点
	 * @param t 终点
     * @author penggs
     * @since 2020/8/15
     */
    public void dfs(T s, T t) {
        // 已找到标识
        found = false;
        // 记录已访问的
        ApHashTable<T, Boolean> visited = new ApHashTable<>();
        // 记录路径
        ApHashTable<T, T> prev = new ApHashTable<>();
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    /**
     * 递归搜索
     * @param s 起始点
     * @param t 终点
     * @param visited 已访问顶点
     * @param prev 路径
     */
    public void recurDfs(T s, T t, ApHashTable<T, Boolean> visited, ApHashTable<T, T> prev) {
        // 已找到，回溯
        if (found) {
            return;
        }
        visited.put(s, Boolean.TRUE);
        if (s.equals(t)) {
            found = true;
            return;
        }
        ApSingleLinkedList<T> vertexList = adj.get(s);
        ApSingleLinkedList.Node<T> p = vertexList.getHead();
        // 遍历相连的顶点
        while (p != null) {
            // 顶点未被访问过，向下递归
            T curData = p.getData();
            if (visited.get(curData) == null || !visited.get(curData)) {
                prev.put(curData, s);
                recurDfs(curData, t, visited, prev);
            }
            p = p.getNext();
        }
    }

    public static void main(String[] args) {
        ApGraph<String> apGraph = new ApGraph<>();
        apGraph.addEdge("One", "Two");
        apGraph.addEdge("One", "Three");
        apGraph.addEdge("One", "Four");
        apGraph.addEdge("Two", "Four");
        apGraph.addEdge("Three", "Four");
        apGraph.addEdge("Four", "Five");
        apGraph.bfs("One", "Five");
        System.out.println();
        apGraph.dfs("One", "Five");
    }
}