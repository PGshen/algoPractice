package world.playtogether.hot;

/**
 * 最大正方形
 */
public class ApMaximalSquare {
    public int maximalSquare(char[][] matrix){
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, maxSquare(matrix, i, j));
            }
        }
        return max * max;
    }

    public int maxSquare(char[][] matrix, int x, int y){
        if (matrix[x][y] == '0') return 0;
        int max = 1;
        int xLen = matrix.length;
        int yLen = matrix[0].length;
        int len = Math.min(xLen - x, yLen - y);
        for (int i = 1; i < len; i++) {
            // 判断 matrix[x+i][y...y+i]
            for (int j = 0; j <= i; j++) {
                if (matrix[x+i][y+j] == '0') return max;
            }
            // 判断 matrix[x...x+i][y+i]
            for(int j = 0; j <= i; j++) {
                if (matrix[x+j][y+i] == '0') return max;
            }
            max++;
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '1', '1', '0'},
        };
        System.out.println(new ApMaximalSquare().maximalSquare(matrix));
    }
}
