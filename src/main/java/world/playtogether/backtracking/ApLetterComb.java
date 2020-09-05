package world.playtogether.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 电话号码的字母组合
 *
 * @author penggs
 * @since 2020-09-05
 */
public class ApLetterComb {
    //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
    //这里也可以用map，用数组可以更节省点内存
    String[] letterMap = {
            " ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
    };
    List<String> res;

    /**
     * 借助队列的方式
     * @param digits 输入的号码
     * @return
     */
    public List<String> letterCombinations1(String digits) {
        if(digits==null || digits.length()==0) {
            return new ArrayList<String>();
        }
        res = new ArrayList<>();
        //先往队列中加入一个空字符
        res.add("");
        for(int i=0;i<digits.length();i++) {
            //由当前遍历到的字符，取字典表中查找对应的字符串
            String letters = letterMap[digits.charAt(i)-'0'];
            int size = res.size();
            //计算出队列长度后，将队列中的每个元素挨个拿出来
            for(int j=0;j<size;j++) {
                //每次都从队列中拿出第一个元素
                String tmp = res.remove(0);
                // 与当前数据所对应的字母拼接
                for(int k=0;k<letters.length();k++) {
                    res.add(tmp+letters.charAt(k));
                }
            }
        }
        return res;
    }

    /**
     * 回溯法
     * @param digits 输入的数字
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        res = new ArrayList<String>();
        if(digits.equals(""))
            return res;
        StringBuilder curStr = new StringBuilder();
        findCombination(digits, 0, curStr);
        return res;
    }

    private void findCombination(String digits, int index, StringBuilder s){
        // 到达最后一个数字，结束
        if(index == digits.length()){
            res.add(s.toString());
            return;
        }
        // 获取第index个数字对应的字母
        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        // 将每一个字母分别拼接到s
        for(int i = 0 ; i < letters.length() ; i ++){
            findCombination(digits, index+1, s.append(letters.charAt(i)));
            // 撤销
            s.deleteCharAt(s.length() - 1);
        }
    }

    public static void main(String[] args) {
        ApLetterComb apLetterComb = new ApLetterComb();
        apLetterComb.letterCombinations1("23").forEach(x -> System.out.print(x + " "));
        System.out.println();
        apLetterComb.letterCombinations2("23").forEach(x -> System.out.print(x + " "));
    }
}