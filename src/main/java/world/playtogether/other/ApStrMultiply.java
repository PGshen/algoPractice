package world.playtogether.other;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 字符串乘法
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 思路：模拟手工计算乘法的过程
 * num1[i] 和 num2[j] 的乘积对应的就是 res[i+j] 和 res[i+j+1] 这两个位置。
 *
 * @author penggs
 * @since 2020-09-07
 */
public class ApStrMultiply {
    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        // 结果最多为 m + n 位数
        //List<Integer> res = new ArrayList<>(m + n);
        int[] res = new int[m + n];
        // 从个位数开始逐位相乘
        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                // 乘积在 res 对应的索引位置
                int p1 = i + j, p2 = i + j + 1;
                // 叠加到 res 上
                int sum = mul + res[p2];
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        // 结果前缀可能存的 0（未使用的位）
        int i = 0;
        while (i < res.length && res[i] == 0)
            i++;
        // 将计算结果转化成字符串
        StringBuilder str = new StringBuilder();
        for (; i < res.length; i++)
            str.append(res[i]);

        return str.toString().length() == 0 ? "0" : str.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("298989", "38899987733"));
    }
}