package world.playtogether.dynamicprogramming;

import java.util.Arrays;

import static world.playtogether.dynamicprogramming.ApDp.lengthOfLongestIncreasingSubsequence;

/**
 * <project> algoPractice
 *
 * <p> 信封最大嵌套个数（俄罗斯套娃）
 *
 * @author penggs
 * @since 2021-04-25 18:10
 */
public class ApMaxEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 先按宽升序，再按高降序（保证相同宽度只有一个能被选入增长序列）
        Arrays.sort(envelopes,
                (a, b) -> a[0]==b[0] ? b[1] - a[1] : a[0] - b[0]
        );
        int[] height = new int[n];
        // 提取高度，然后作为最大增长子序列进行求解
        for(int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLongestIncreasingSubsequence(height);
    }
}