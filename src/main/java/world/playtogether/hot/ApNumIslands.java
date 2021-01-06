package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围
 *
 * @author penggs
 * @since 2021-01-05
 */
public class ApNumIslands {
    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 发现了一个陆地
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 深度优先遍历，遍历过的记录修改为0，避免重复访问陷入死循环
     *
     * @param grid
     * @param x
     * @param y
     */
    public static void dfs(char[][] grid, int x, int y) {
        if (!inArea(grid, x, y)) return;
        if (grid[x][y] == '0') return;
        grid[x][y] = '0';
        // 向四个方向移动
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }

    /**
     * 是否在区域内
     */
    public static boolean inArea(char[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }
}