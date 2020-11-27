package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * @author penggs
 * @since 2020-11-27
 */
public class ApWordSearch {
    private boolean[][] marked;

    //        x-1,y
    // x,y-1  x,y    x,y+1
    //        x+1,y
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    // 盘面上有多少行
    private int m;
    // 盘面上有多少列
    private int n;
    private String word;
    private char[][] board;

    public boolean search(char[][] board, String word) {
        m = board.length;
        if (m == 0) {
            return false;
        }
        n = board[0].length;
        // 标记位置是否被使用过
        marked = new boolean[m][n];
        this.word = word;
        this.board = board;

        // 对每个单元格和字串从头开始匹配，暴力搜索
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 回溯，（深度优先遍历）
     * @param i 当前x坐标
     * @param j 当前y坐标
     * @param start 字符串的位置点
     * @return
     */
    private boolean dfs(int i, int j, int start) {
        if (start == word.length() - 1) {
            // 最后一个字符
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j] == word.charAt(start)) {
            // 选择
            marked[i][j] = true;
            // 四个方向递归
            for (int k = 0; k < 4; k++) {
                // 处理技巧
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                // 判断是否超出边界，当前位置是否已访问过
                if (inArea(newX, newY) && !marked[newX][newY]) {
                    if (dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            // 撤销选择
            marked[i][j] = false;
        }
        return false;
    }

    /**
     * 是否在区域内
     * @param x
     * @param y
     * @return
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        ApWordSearch apWordSearch = new ApWordSearch();
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(apWordSearch.search(board, "ABCCED"));
    }
}