package world.playtogether.hot;

import java.util.*;

/**
 * 课程表
 */
public class ApCourseSchedule {
    /**
     * 拓扑排序，最后判断入度
     * @param numCourses 课程总数
     * @param prerequisites 课程先后顺序
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites){
        // 用map保存信息
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
        }
        Map<Integer, List<Integer>> map = new HashMap<>();

        // 初始化入度和邻接表
        for (int[] relation: prerequisites) {
            int cur = relation[1];
            int next = relation[0];
            inDegree.put(next, inDegree.get(next) + 1);
            if (!map.containsKey(cur)) {
                map.put(cur, new ArrayList<>());
            }
            map.get(cur).add(next);
        }

        // 入度为0的入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int key: inDegree.keySet()){
            if (inDegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        while (!queue.isEmpty()){
            int q = queue.poll();
            if (!map.containsKey(q)) continue;
            // 更新这个课程的后续课程的入度
            List<Integer> list = map.get(q);
            for (int k: list) {
                inDegree.put(k, inDegree.get(k)-1);
                // 如果入度为0，入队列，表示可选这门课
                if (inDegree.get(k) == 0) queue.offer(k);
            }
        }
        // 判断是否还有寻找入度不为0的课程，有的话表示还有课程不能选
        for (int k: inDegree.keySet()) {
            if (inDegree.get(k) != 0) return false;
        }

        return true;
    }
}
