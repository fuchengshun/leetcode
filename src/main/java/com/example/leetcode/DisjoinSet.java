package com.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class DisjoinSet<T> {
    Map<T, T> map = new HashMap<>();

    public T find(T t) {
        return map.get(t) == null || map.get(t) == t ? t : find(map.get(t));
    }

    public void union(T i, T j) {
        map.put(find(i), find(j));
    }

    public boolean isConnected(T i, T j) {
        return find(i) == find(j);
    }
}
