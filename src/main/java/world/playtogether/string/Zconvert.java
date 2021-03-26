package world.playtogether.string;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> z字形变换输出
 *
 * @author penggs
 * @since 2021-03-25
 */
public class Zconvert {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        // 初始化行
        List<StringBuilder> rows = new ArrayList<>();
        int totalRow = Math.min(numRows, s.length());
        for (int i = 0; i < totalRow; i++) {
            rows.add(new StringBuilder());
        }
        int curRow = 0;
        boolean goingDown = false;
        // 遍历字符，填入期望的所在行
        for (Character ch: s.toCharArray()) {
            rows.get(curRow).append(ch);
            if (curRow == 0 || curRow == totalRow - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }
        // 收集全部行
        StringBuilder ret = new StringBuilder();
        rows.forEach(row -> ret.append(row.toString()));
        return ret.toString();
    }
}