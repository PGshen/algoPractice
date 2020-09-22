package world.playtogether.other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 队列-栈
 *
 * @author penggs
 * @since 2020-09-22
 */
public class ApMyQueueStack {
    /**
     * 用栈实现队列
     */
    static class MyQueue<T> {
        private Stack<T> s1 = null;
        private Stack<T> s2 = null;

        public MyQueue() {
            this.s1 = new Stack<>();
            this.s2 = new Stack<>();
        }

        /**
         * 入队
         * @param t 值
         */
        public void offer(T t) {
            s1.push(t);
        }

        /**
         * 队头
         * @return
         */
        public T peek() {
            if(s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.peek();
        }

        /**
         * 出队
         * @return
         */
        public T poll() {
            if(s2.isEmpty()) {
                while (!s1.isEmpty()) {
                    s2.push(s1.pop());
                }
            }
            return s2.pop();
        }

        public boolean isEmpty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }

    /**
     * 用队列实现栈
     * @param <T>
     */
    static class MyStack<T> {
        private Queue<T> q = null;
        // 记录栈顶
        private T topEle = null;

        public MyStack() {
            this.q = new LinkedList<>();
        }

        public void push(T t) {
            q.offer(t);
            topEle = t;
        }

        public T peek() {
            return topEle;
        }

        public T pop() {
            // 循环将队头取出加入队尾
            int size = q.size();
            while (size > 2) {
                q.offer(q.poll());
                size--;
            }
            // 倒2成为栈顶
            topEle = q.peek();
            q.offer(q.poll());
            // 弹出栈顶
            return q.poll();
        }

        public boolean isEmpty() {
            return q.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        myQueue.offer(4);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.peek());
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
    }
}