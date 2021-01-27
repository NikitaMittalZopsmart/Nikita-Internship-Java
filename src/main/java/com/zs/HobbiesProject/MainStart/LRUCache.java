package main.java.com.zs.HobbiesProject.MainStart;


import main.java.com.zs.HobbiesProject.Util.LogImplement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * LRUCache implementing.
 */
public class LRUCache<T1, T2> {
    private final int sizeOfCache;
    private Map<T1, T2> map;


    /**
     * Constructor to create cache .
     *
     * @param sizeOfCache Size of cache.
     */
    public LRUCache(int sizeOfCache) {
        this.sizeOfCache = sizeOfCache;
        map = new LinkedHashMap<>(sizeOfCache, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > sizeOfCache;
            }
        };
    }

    /**
     * This function is puting the key and value in cache.
     *
     * @param u U is the key .
     * @param i i is the value.
     */
    public void putCache(T1 u, T2 i) {

        map.put(u, i);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LRUCache<?, ?> lruCache = (LRUCache<?, ?>) o;
        return sizeOfCache == lruCache.sizeOfCache && Objects.equals(map, lruCache.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeOfCache, map);
    }

    /**
     * This function is returning the entry set of cache.
     *
     * @return Returning entrySet of cache.
     */
    public Set<Map.Entry<T1, T2>> getVal() {
        if (map.isEmpty()) {
            LogImplement.getLog().info("Map is empty");
            return null;
        }
        Set<Map.Entry<T1, T2>> entrySet = map.entrySet();

        return entrySet;
    }

    public int sizeOfCache() {
        return map.size();
    }

    /**
     * This function is to display the cache.
     */
    public void displayCache() {
        LogImplement.getLog().info("Displaying Cache");
        Set<Map.Entry<T1, T2>> entrySet = map.entrySet();

        for (Map.Entry<T1, T2> entry : entrySet) {
            T1 u1 = entry.getKey();
            LogImplement.getLog().info(u1.toString());
            T2 u2 = entry.getValue();
            LogImplement.getLog().info(u2.toString());
        }

    }


}
