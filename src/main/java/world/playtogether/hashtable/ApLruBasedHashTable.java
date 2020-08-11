package world.playtogether.hashtable;

import world.playtogether.list.ApDoubleLinkedList;

import java.util.Objects;

/**
 * <project> algoPractice
 *
 * <p> 基于哈希表实现的LRU
 *
 * @author penggs
 * @since 2020-08-11 17:15
 */
public class ApLruBasedHashTable<K, V> {

    // 哈希表
    private ApHashTable<K, ApDoubleLinkedList.Node<KvEntry<K, V>>> map;
    // 链表
    private ApDoubleLinkedList<KvEntry<K, V>> cache;
    private int capacity;

    public ApLruBasedHashTable() {
        this.capacity = 16;
        this.map = new ApHashTable<>();
        this.cache = new ApDoubleLinkedList<>();
    }

    public ApLruBasedHashTable(int capacity) {
        this.capacity = capacity;
        this.map = new ApHashTable<>();
        this.cache = new ApDoubleLinkedList<>();
    }

    public V get(K key) {
         ApDoubleLinkedList.Node<KvEntry<K, V>> kvEntryNode = map.get(key);
        if (kvEntryNode == null) {
            return null;
        }
        // 将节点移动到最前面
        cache.deleteByData(kvEntryNode.getData());
        cache.insertToHead(kvEntryNode.getData());
        return kvEntryNode.getData().value;
    }

    public void put(K key, V value) {
        KvEntry<K, V> kvEntry = new KvEntry<>(key, value);
        ApDoubleLinkedList.Node<KvEntry<K, V>> kvEntryNode = map.get(key);
        if (kvEntryNode != null) {
            // 删除、然后重新插入
            cache.deleteByData(kvEntry);
            cache.insertToHead(kvEntry);
        } else {
            // 达到容量、删除最后一个节点
            if (capacity == cache.getSize()) {
                ApDoubleLinkedList.Node<KvEntry<K, V>> tailNode = cache.getTail();
                cache.deleteTail();
                map.remove(tailNode.getData().key);
            }
            // 插入
            cache.insertToHead(kvEntry);
            ApDoubleLinkedList.Node<KvEntry<K, V>> node = new ApDoubleLinkedList.Node<>(kvEntry);
            map.put(key, node);
        }
    }

    @Override
    public String toString() {
        return cache.toString();
    }

    static class KvEntry<K, V> {
        K key;
        V value;

        public KvEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KvEntry<?, ?> kvEntry = (KvEntry<?, ?>) o;
            return Objects.equals(key, kvEntry.key) &&
                    Objects.equals(value, kvEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        ApLruBasedHashTable<String, Integer> lru = new ApLruBasedHashTable<>(3);
        lru.put("One", 1);
        lru.put("Two", 2);
        lru.put("Three", 3);
        System.out.println(lru);
        lru.put("Four", 4);
        System.out.println(lru.get("Two"));
        lru.get("Two");
        lru.get("Three");
        System.out.println(lru);
    }
}