package world.playtogether.list;

/**
 * algoPractice
 *
 * <p> 数组列表
 *
 * @author : penggs
 * @since : 2020-08-05 23:12
 */
public class ApArrayList<T> {
    private Object[] data;
    private int size;

    public ApArrayList() {
        this(16);
    }

    public ApArrayList(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public void add(int index, T element) {
        checkIndexForAdd(index);
        if (index == data.length) {
            resize(2 * data.length);
        }
        if (size - index >= 0) System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    public void addFirst(T element) {
        add(0, element);
    }

    public void addLast(T element) {
        add(size, element);
    }

    public void set(int index, T element) {
        checkIndex(index);
        data[index] = element;
    }

    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    public void remove(int index) {
        checkIndex(index);
        // 此处减一，是因为数组的最后一位已经没有右侧数据可以补位
        if (size - 1 - index >= 0) System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size - 1);
    }

    public boolean contain(T element) {
        return find(element) > -1;
    }

    public int find(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int capacity) {
        Object[] newData = new Object[capacity];
        if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index illegal!");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index illegal!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ApArrayList<String> apArrayList = new ApArrayList<>();
        apArrayList.addFirst("two");
        apArrayList.addLast("one");
        apArrayList.add(1,"three");
        apArrayList.add(2, "four");
        apArrayList.set(2, "five");
        System.out.println(apArrayList.find("one"));
        System.out.println(apArrayList.contain("one"));
        System.out.println(apArrayList.get(2));
        System.out.println(apArrayList.toString());
        apArrayList.remove(2);
        System.out.println(apArrayList.toString());
        apArrayList.removeLast();
        System.out.println(apArrayList.toString());
        apArrayList.removeFirst();
        System.out.println(apArrayList.toString());
    }
}