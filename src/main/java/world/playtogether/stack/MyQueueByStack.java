package world.playtogether.stack;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 使用队列实现栈
 *
 * @author penggs
 * @since 2021-04-07 23:17
 */
public class MyQueueByStack {
    Stack<Integer> stack1;  // 入栈都在这个
    Stack<Integer> stack2;  // 出栈都在这个
    /** Initialize your data structure here. */
    public MyQueueByStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.empty()) {
            // 如果stack2为空了，那么将stack1的元素都倒入stack2
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }
}