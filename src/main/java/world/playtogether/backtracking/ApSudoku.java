package world.playtogether.backtracking;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 数独
 * 对每一个空着的格子穷举 1 到 9，
 * 如果遇到不合法的数字（在同一行或同一列或同一个 3×3 的区域中存在相同的数字）则跳过，
 * 如果找到一个合法的数字，则继续穷举下一个空格子
 *
 * @author penggs
 * @since 2020-08-22
 */
public class ApSudoku {
    void solveSudoku(char[][] board){
        backtrack(board, 0, 0);
    }

    boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            // 穷举到最后一列换行
            return backtrack(board, i + 1, 0);
        }
        if (i == m) {
            // 到达最后行
            return true;
        }
        if (board[i][j] != '\u0000') {
            // 已有预设值
            return backtrack(board, i, j+1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            if (!isValid(board, i, j, ch)) {
                continue;
            }
            // 做选择
            board[i][j] = ch;
            // 递归下一个
            if (backtrack(board, i, j+1)) {
                return true;
            }
            // 撤销选择
            board[i][j] = '\u0000';
        }
        return false;
    }

    // 判断 board[i][j] 是否可以填入 n
    boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][c] == n) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ApSudoku apSudoku = new ApSudoku();
        char[][] board = new char[9][9];
        board[0][0] = '1';
        board[8][8] = '9';
        apSudoku.solveSudoku(board);
        Arrays.stream(board).forEach(System.out::println);
    }
}