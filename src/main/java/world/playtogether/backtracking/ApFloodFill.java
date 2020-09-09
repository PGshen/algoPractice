package world.playtogether.backtracking;

/**
 * <project> algoPractice
 *
 * <p> 图像渲染---油漆桶工具
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 *
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。连片的相同颜色均上色
 * 输入:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 *
 * @author penggs
 * @since 2020-09-09
 */
public class ApFloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // 原颜色值
        int originColor = image[sr][sc];
        fill(image, sr, sc, originColor, newColor);
        return image;
    }

    public void fill(int[][] image, int x, int y, int originColor, int newColor) {
        // 超出范围，退回
        if(!inArea(image, x, y)) return;
        // 不同颜色，碰壁，直接退回
        if(image[x][y] != originColor) return;
        // 该位置访问过了，也退回
        if(image[x][y] == -1) return;
        // 置为-1，表示这个位置访问过了
        image[x][y] = -1;
        // 向4个方向走
        fill(image, x-1, y, originColor, newColor);
        fill(image, x+1, y, originColor, newColor);
        fill(image, x, y-1, originColor, newColor);
        fill(image, x, y+1, originColor, newColor);
        // 重新写回新的颜色
        image[x][y] = newColor;
    }

    // 是否在图片范围
    private boolean inArea(int[][] image, int x, int y) {
        int heigh = image[0].length;
        int width = image.length;
        return x >= 0 && x < width && y >= 0 && y < heigh;
    }
}