package com.example.leetcode.unionfind;

public class UnionFind {
    int[] root;

    public void union(int i, int j) {
        root[find(i)] = find(j);
    }

    public boolean isCollect(int i, int j) {
        return find(i) == find(j);
    }

    public int find(int i) {
        if (root[i] == i) {
            return i;
        }
        return root[i]=find(root[i]);
    }

    public UnionFind(int k) {
        root = new int[k];
        for (int i = 0; i < k; i++) {
            root[i] = i;
        }
    }
}
