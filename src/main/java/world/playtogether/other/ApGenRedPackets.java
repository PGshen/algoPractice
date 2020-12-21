package world.playtogether.other;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> 分红包
 *
 * @author penggs
 * @since 2020-12-16
 */
public class ApGenRedPackets {
    /**
     * 线段切割法
     * 在一条线段上找 N-1 个随机点，就可以将该线段随机且公平地切割成 N 段。
     * @param people 人数
     * @param money 钱
     * @return
     */
    List<Integer> generatePacketsByLineCutting(int people, int money) {
        List<Integer> packets = new ArrayList<>();
        Random random = new Random();
        Set<Integer> points = new TreeSet<>();
        while (points.size() < people - 1) {
            points.add(random.nextInt(money - 1));
        }
        points.add(money);
        int pre = 0;
        for (int p : points) {
            packets.add(p - pre);
            pre = p;
        }
        return packets;
    }

}