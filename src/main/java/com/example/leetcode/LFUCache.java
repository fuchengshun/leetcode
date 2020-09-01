package com.example.leetcode;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {
    Map<Integer, Node> cache;
    Map<Integer, LinkedHashSet<Node>> freqMap;
    int size, min;

    public LFUCache(int capacity) {
        cache = new HashMap<> (capacity);
        freqMap = new HashMap<>();
        this.size = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        LinkedHashSet<Node> set1 = freqMap.get(node.count);
        set1.remove(node);
        if (set1.isEmpty()){
            freqMap.remove(node.count);
        }
        node.count++;
        LinkedHashSet<Node> set2 = freqMap.getOrDefault(node.count,new LinkedHashSet<>());
        set2.add(node);
        freqMap.put(node.count,set2);
        return node.value;
    }

    public void put(int key, int value) {
        if (size==0){
            return;
        }
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            LinkedHashSet<Node> set1 = freqMap.get(node.count);
            if (node.count == min && set1.size() == 1) {
                min++;
            }
            set1.remove(node);
            node.value = value;
            node.count++;
            LinkedHashSet<Node> set2 = freqMap.getOrDefault(node.count, new LinkedHashSet<>());
            set2.add(node);
            freqMap.put(node.count, set2);
        } else {
            if (cache.size() == size) {
                LinkedHashSet<Node> set = freqMap.get(min);
                Node next = set.iterator().next();
                set.remove(next);
                if (set.isEmpty()){
                    freqMap.remove(min);
                }
                cache.remove(next.key);
            }
            Node node = new Node(key, value);
            cache.put(key, node);
            LinkedHashSet<Node> set = freqMap.getOrDefault(1, new LinkedHashSet<>());
            set.add(node);
            freqMap.put(node.count, set);
            min = 1;
        }
    }

    static class Node {
        int count, key, value;

        public Node() {
        }

        public Node(int key, int value) {
            this.count = 1;
            this.key = key;
            this.value = value;
        }
    }
}
