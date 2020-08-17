package world.playtogether.hashtable;

/**
 * <project> algoPractice
 *
 * <p> 哈希表
 *
 * @author penggs
 * @since 2020-08-11 14:36
 */
@SuppressWarnings("unchecked")
public class ApHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75F;
    private int elementCount = 0;
    private int indexCount = 0;
    private Entry<K, V>[] table;

    public ApHashTable() {
        table = (Entry<K, V>[])new Entry[DEFAULT_CAPACITY];
    }

    public ApHashTable(int capacity) {
        table = (Entry<K, V>[])new Entry[capacity];
    }

    /**
     * 插入
     *
     * @param key 键
	 * @param value 值
     * @author penggs
     * @since 2020/8/11
     */
    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            // 相当于头节点
            table[index] = new Entry<>(null, null, null);
        }
        Entry<K, V> e = table[index];
        if (e.next == null) {
            // 当前槽位第一次写入数据
            e.next = new Entry<>(key, value, null);
            elementCount++;
            indexCount++;
            if (indexCount >= table.length * LOAD_FACTOR) {
                resize();
            }
        } else {
            // 遍历链表，看当前key是否已存在，若存在则覆盖
            do {
                e = e.next;
                if (e.key.equals(key)) {
                    e.value = value;
                    return;
                }
            } while (e.next != null);
            // 新的插入链表头部
            Entry<K, V> first = table[index].next;
            table[index].next = new Entry<>(key, value, first);
            elementCount++;
        }
    }

    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (e == null || e.next == null) {
            return;
        }
        // 保存前驱节点
        Entry<K, V> prev;
        Entry<K, V> head = table[index];
        // 遍历节点
        do {
            prev = e;
            e = e.next;
            if (e.key.equals(key)) {
                prev.next = e.next;
                elementCount--;
                if (head.next == null) {
                    indexCount--;
                }
                return;
            }
        } while (e.next != null);
    }

    public V get(K key) {
        int index = hash(key);
        // 第一个相当于头节点
        Entry<K, V> e = table[index];
        if (e == null || e.next == null) {
            return null;
        }
        // 从链表头开始找
        while (e.next != null) {
            e = e.next;
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[])new Entry[table.length * 2];
        indexCount = 0;
        for (Entry<K, V> kvEntry : oldTable) {
            if (kvEntry == null || kvEntry.next == null) {
                continue;
            }
            Entry<K, V> e = kvEntry;
            while (e.next != null) {
                e = e.next;
                int index = hash(e.key);
                if (table[index] == null) {
                    indexCount++;
                    table[index] = new Entry<>(null, null, null);
                }
                table[index].next = new Entry<>(e.key, e.value, table[index].next);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(i).append(":");
            if (table[i] == null || table[i].next == null) {
                sb.append("\n");
            } else {
                Entry<K, V> e = table[i];
                while (e.next != null) {
                    e = e.next;
                    sb.append(e.key).append(":").append(e.value).append(" ");
                }
                sb.append("\n");
            }
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }

    public String toString2() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Entry<K, V> kvEntry : table) {
            if (kvEntry == null) continue;
            while (kvEntry.next != null) {
                kvEntry = kvEntry.next;
                sb.append(kvEntry.value);
                if (kvEntry.next != null) {
                    sb.append(",");
                }
            }
            sb.append(",");
        }
        if (sb.toString().endsWith(",")) {
            return sb.substring(0, sb.length() - 1) + "]";
        }
        sb.append("]");
        return sb.toString();
    }

    public int getElementCount() {
        return elementCount;
    }

    private int hash(Object key) {
        int hashCode;
        // NOTE: hashCode可能为负数
        return (key == null) ? 0 : Math.abs(((hashCode = key.hashCode()) ^ hashCode >>> 16) % table.length);
    }

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }


    }

    public static void main(String[] args) {
        ApHashTable<String, String> hashTable = new ApHashTable<>(3);
        hashTable.put("A", "aValue");
        hashTable.put("B", "bValue");
        System.out.println(hashTable);
        hashTable.put("C", "cValue");
        hashTable.put("D", "dValue");
        hashTable.put("D", "dValue");
        System.out.println(hashTable);
        hashTable.remove("A");
        hashTable.remove("E");
        System.out.println(hashTable);
        hashTable.put("A", "aValue");
        System.out.println(hashTable);
        System.out.println(hashTable.get("A"));
        System.out.println(hashTable.get("B"));
    }
}