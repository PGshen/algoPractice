package world.playtogether.hot;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> 打开锁的最少步数
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author penggs
 * @since 2021-01-25
 */
public class ApOpenLock {

    public int openLock(String[] deadends, String target) {
        // 记录已访问过的
        Set<String> visitedSet = new HashSet<>();
        // 记录死锁
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();

        // 初始化
        queue.offer("0000");
        visitedSet.add("0000");
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return step;
                    // 这个值在死亡数字，他的不能将它的相邻数字加入遍历
                } else if (deadendSet.contains(cur)){
                    continue;
                }
                // 翻转
                for (int j = 0; j < 4; j++) {
                    String next = plusOne(cur, j);
                    if (!visitedSet.contains(next)) {
                        queue.offer(next);
                        visitedSet.add(next);
                    }
                }
                // 翻转
                for (int j = 0; j < 4; j++) {
                    String next = minusOne(cur, j);
                    if (!visitedSet.contains(next)) {
                        queue.offer(next);
                        visitedSet.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    public static void main(String[] args) {
        String[] deadends = new String[] {"0201","0101","0102","1212","2002"};
        System.out.println(new ApOpenLock().openLock(deadends, "0202"));
    }
}