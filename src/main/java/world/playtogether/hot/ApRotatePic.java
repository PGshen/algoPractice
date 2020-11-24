package world.playtogether.hot;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 图像矩阵旋转
 *
 * @author penggs
 * @since 2020-11-24
 */
public class ApRotatePic {
    /**
     * n * n 矩阵
     * 从最外向内收缩，每次旋转一圈，为方便计算，每4个角进行交换
     * @param matrix 矩阵数组
     */
    public static void rotate(int[][] matrix) {
        int pos1 = 0;
        int pos2 = matrix[0].length - 1;
        while (pos1 < pos2) {
            // 偏移量
            int add = 0;
            while (add < pos2 - pos1) {
                // 四个点交换
                int temp = matrix[pos1][pos1+add];
                matrix[pos1][pos1+add] = matrix[pos2-add][pos1];
                matrix[pos2-add][pos1] = matrix[pos2][pos2-add];
                matrix[pos2][pos2-add] = matrix[pos1+add][pos2];
                matrix[pos1+add][pos2] = temp;
                add++;
            }
            // 圈收缩
            pos1++;
            pos2--;
        }
    }

    /**
     * 旋转图片
     * @param pic
     * @return
     */
    public static int[][] rotatePic(int[][] pic) {
        int xLen = pic[0].length;
        int yLen = pic.length;
        int[][] newPic = new int[xLen][yLen];
        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                newPic[j][yLen-1-i] = pic[i][j];
            }
        }
        return newPic;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
        int[][] pic = new int[][] {{1,2,3},{4,5,6}};
        System.out.println(Arrays.deepToString(rotatePic(pic)));
    }
}