package world.playtogether.stack;

import java.util.Arrays;

/**
 * project: algoPractice
 *
 * <p> 顺序栈
 *
 * @author : penggs
 * @since : 2020-08-05 23:14
 */
public class ApArrayStack<T> {
    private Object[] container;
    private int capacity;
    private int count;

    public ApArrayStack() {
        this.container = new Object[10];
        this.capacity = 10;
        this.count = 0;
    }

    public ApArrayStack(int capacity) {
        this.container = new Object[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    public boolean push(T data) {
        if (count == capacity) {
            resize(2 * capacity);
        }
        container[count++] = data;
        return true;
    }

    public T pop() {
        if (count < 1) {
            return null;
        }
        return (T) container[--count];
    }

    public T peek() {
        if (count < 1) {
            return null;
        }
        return (T) container[count - 1];
    }

    private void resize(int capacity) {
        Object[] newData = new Object[capacity];
        if (count >= 0) System.arraycopy(container, 0, newData, 0, count);
        this.capacity = capacity;
        container = newData;
        System.out.println("Resize: count = " + this.count + ", capacity = " + this.capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = count - 1; i >= 0; i--) {
            sb.append(container[i].toString()).append(",");
        }
        return sb.toString().substring(0, sb.toString().lastIndexOf(","));
    }

    public static void main(String[] args) {
        ApArrayStack<String> stack = new ApArrayStack<>(3);
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        System.out.println(stack.toString());
        stack.push("Four1");
        stack.push("Four2");
        stack.push("Four3");
        stack.push("Four4");
        System.out.println(stack.peek());
        System.out.println(stack.toString());
        System.out.println(stack.pop());
        System.out.println(stack.toString());
    }

}