package world.playtogether.stack;

import world.playtogether.list.ApArrayList;

/**
 * <project> algoPractice
 *
 * <p> 栈常用操作
 * 1. 表达式求值
 * 2. 括号匹配
 * 3. 浏览器网页前进后退功能
 *
 * @author penggs
 * @since 2020-08-08 18:54
 */
public class ApOpLinkedStack<T> {
    /**
     * 模拟编译器表达式求值，仅支持加减乘除
     * 思路：通过两个栈来实现的。其中一个保存操作数的栈，另一个是保存运算符的栈。
     * 我们从左向右遍历表达式，当遇到数字，我们就直接压入操作数栈；当遇到运算符，就与运算符栈的栈顶元素进行比较。
     * 如果比运算符栈顶元素的优先级高，就将当前运算符压入栈；
     * 如果比运算符栈顶元素的优先级低或者相同，从运算符栈中取栈顶运算符，从操作数栈的栈顶取 2 个操作数，然后进行计算，再把计算完的结果压入操作数栈，继续比较。
     *
     * @param expression 表达式
     * @return double
     * @author penggs
     * @since 2020/8/8
     */
    public double expressionEval(String expression) {
        ApArrayList<String> expressionList = new ApArrayList<>();
        ApLinkedStack<Double> operandStack = new ApLinkedStack<>();
        ApLinkedStack<String> operatorStack = new ApLinkedStack<>();
        // 将字符串拆分成数组
        int index = 0;
        if (expression.startsWith("+") || expression.startsWith("-")) {
            expressionList.addLast("0");
        }
        for (int i = 0; i < expression.length(); i++) {
            char curChar = expression.charAt(i);
            if (isOperator(curChar)) {
                if (index != i) {
                    expressionList.addLast(expression.substring(index, i).trim());
                }
                expressionList.addLast(String.valueOf(curChar));
                index = i + 1;
            }
        }
        expressionList.addLast(expression.substring(index));
        // 逐个操作数/运算符
        for (int i = 0; i < expressionList.getSize(); i++) {
            String exp = expressionList.get(i);
            if (isOperator(exp)) {
                // 持续与栈顶操作比较，直到优先级不低于栈顶
                while (true) {
                    String topExp = operatorStack.peek();
                    if (topExp == null || getPriority(exp) > getPriority(topExp)) {
                        // 优先级高于栈顶的运算符，直接入栈
                        operatorStack.push(exp);
                        break;
                    } else {
                        // 低于栈顶，从操作数栈取两个进行运算
                        Double operand1 = operandStack.pop();
                        Double operand2 = operandStack.pop();
                        Double opResult = opCompute(topExp, operand1, operand2);
                        // 计算完成，运算符栈弹出栈顶元素，计算结果入操作数栈
                        operatorStack.pop();
                        operandStack.push(opResult);
                    }
                }
            } else {
                operandStack.push(Double.valueOf(exp));
            }
        }
        // 最后一次运算
        Double operand1 = operandStack.pop();
        Double operand2 = operandStack.pop();
        String exp = operatorStack.pop();
        return opCompute(exp, operand1, operand2);
    }

    public Double opCompute(String operator, Double operand1, Double operand2) {
        if ("*".equals(operator)) {
            return operand1 * operand2;
        } else if ("/".equals(operator)) {
            return operand2 / operand1;
        } else if ("+".equals(operator)) {
            return operand1 + operand2;
        } else if ("-".equals(operator)) {
            return operand2 - operand1;
        }
        throw new IllegalArgumentException("Unsupported operator!");
    }

    public boolean isOperator(String str) {
        return "*".equals(str) || "/".equals(str) || "+".equals(str) || "-".equals(str);
    }

    public boolean isOperator(char ch) {
        return ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    /**
     * 获取运算符的优先级
     *
     * @param ch 运算符
     * @return int 优先级，数值越大优先级越高
     * @author penggs
     * @since 2020/8/8
     */
    public int getPriority(String ch) {
        switch (ch) {
            case "*":
            case "/": return 10;
            case "+":
            case "-": return 9;
            default: return -1;
        }
    }

    /**
     * 括号匹配
     * 思路：如果是左括号，入栈；如果是右括号，与栈顶进行匹配，若匹配，栈顶出栈，继续匹配，若不匹配，说明格式非法；
     * 所有字符串匹配结束后，如果栈为空，说明括号匹配；否则为格式非法
     *
     * @param parentheses 括号
     * @return boolean 是否配置
     * @author penggs
     * @since 2020/8/8
     */
    public boolean parenthesesMatching(String parentheses) {
        ApLinkedStack<Character> apLinkedStack = new ApLinkedStack<>();
        // 过滤非括号字符
        ApArrayList<Character> parenthesesList = new ApArrayList<>();
        for (int i = 0; i < parentheses.length(); i++) {
            char ch = parentheses.charAt(i);
            if (isParentheses(ch)) {
                parenthesesList.addLast(ch);
            }
        }
        // 左括号入栈，右括号与栈顶比较匹配
        for (int i = 0; i < parenthesesList.getSize(); i++) {
            Character ch = parenthesesList.get(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                apLinkedStack.push(ch);
            } else {
                if (!isPaired(apLinkedStack.pop(), ch)) {
                    return false;
                }
            }
        }
        return apLinkedStack.isEmpty();
    }

    public boolean isParentheses(char ch) {
        return ch == '(' || ch == ')' || ch == '{' || ch == '}' || ch == '[' || ch == ']';
    }

    public boolean isPaired(char left, char right) {
        return left == '(' && right == ')' || left == '[' && right == ']' ||left == '{' && right == '}';
    }

    /**
     * 模拟浏览器的网页前进后退功能
     * 思路：使用两个栈，X 和 Y，
     * 我们把首次浏览的页面依次压入栈 X，并清空栈Y
     * 当点击后退按钮时，再依次从栈 X 中出栈，并将出栈的数据依次放入栈 Y。
     * 当我们点击前进按钮时，我们依次从栈 Y 中取出数据，放入栈 X 中。
     * 当栈 X 中没有数据时，那就说明没有页面可以继续后退浏览了。当栈 Y 中没有数据，那就说明没有页面可以点击前进按钮浏览了。
     *
     * @author penggs
     * @since 2020/8/8
     */
    public void browserForwardAndBack() {

    }

    public static void main(String[] args) {
        ApOpLinkedStack<String> apOpLinkedStack = new ApOpLinkedStack<>();
        System.out.println(apOpLinkedStack.expressionEval("12+12*3/4-4"));

        System.out.println(apOpLinkedStack.parenthesesMatching("{{}()[()]}"));
        System.out.println(apOpLinkedStack.parenthesesMatching("{{}())[()]}"));
    }
}