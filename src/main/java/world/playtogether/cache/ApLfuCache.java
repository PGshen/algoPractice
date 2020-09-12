package world.playtogether.cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * <project> algoPractice
 *
 * <p> 最近最少未使用缓存算法,基于访问频率淘汰
 *
 * @author penggs
 * @since 2020-09-12
 */
public class ApLfuCache<K, V> {
    // 保存 key-value映射
    HashMap<K, V> kToV;
    // 保存 key-freq 映射
    HashMap<K, Integer> kToF;
    // 保存 freq-keys 映射
    HashMap<Integer, LinkedHashSet<K>> fToK;
    int minFreq;
    int capacity;

    public ApLfuCache(int capacity) {
        this.kToV = new HashMap<>();
        this.kToF = new HashMap<>();
        this.fToK = new HashMap<>();
        this.minFreq = 0;
        this.capacity = capacity;
    }

    /**
     * 读缓存
     * @param key key
     * @return 返回value，不存在返回null
     */
    public V get(K key) {
        if (!kToV.containsKey(key)) {
            return null;
        }
        increaseFreq(key);
        return kToV.get(key);
    }

    /**
     * 写缓存
     * @param key key
     * @param value value
     */
    public void put(K key, V value) {
        if (capacity < 0) return;
        // 已存在这个key
        if (kToV.containsKey(key)) {
            kToV.put(key, value);
            increaseFreq(key);
            return;
        }
        // 已达到容量，删除频次最低的key
        if (kToV.size() >= capacity) {
            rmMinFreqKey();
        }
        // 更新映射表
        kToV.put(key, value);
        kToF.put(key, 1);
        fToK.putIfAbsent(1, new LinkedHashSet<>());
        fToK.get(1).add(key);
        // 此时 minFreq是1
        minFreq = 1;
    }

    /**
     * 移除最小频次的key
     */
    private void rmMinFreqKey() {
        LinkedHashSet<K> keyList = fToK.get(this.minFreq);
        // 获取待删除的key
        K toDelKey = keyList.iterator().next();
        // 更新三个映射表
        keyList.remove(toDelKey);
        if (keyList.isEmpty()) {
            // 当前频次的全空，从fToK移除，但是不需要更新minFreq，因为不影响
            fToK.remove(this.minFreq);
        }
        kToV.remove(toDelKey);
        kToF.remove(toDelKey);
    }

    /**
     * 增加频次
     * @param key key
     */
    private void increaseFreq(K key) {
        Integer freq = kToF.get(key);
        // 更新映射表
        kToF.put(key, freq+1);
        fToK.get(freq).remove(key);
        // 新的频次增加key到列表中
        fToK.putIfAbsent(freq+1, new LinkedHashSet<>());
        fToK.get(freq+1).add(key);
        // 原freq映射的keyList已空
        if (fToK.get(freq).isEmpty()) {
            fToK.remove(freq);
            // 刚好是最低频次
            if (freq == minFreq) {
                minFreq++;
            }
        }
    }

    public static void main(String[] args) {
        ApLfuCache<String, String> cache = new ApLfuCache<>(2);
        cache.put("one", "oneVal");
        cache.put("two", "twoVal");
        System.out.println(cache.get("one"));
        System.out.println(cache.get("three"));
        cache.put("three", "threeVal");
        System.out.println(cache.get("two"));
        System.out.println(cache.get("three"));
    }
}