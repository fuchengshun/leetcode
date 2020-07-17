package com.example.leetcode;

class UnionFind {
    int[] roots;

    UnionFind(int num) {
        roots = new int[num];
        for (int i = 0; i < num; i++) {
            roots[i] = i;
        }
    }

    public int findRoot(int index) {
        if (index==roots[index]){
            return index;
        }
        return roots[index]=findRoot(roots[index]);
    }

    public void union(int x, int y) {
        roots[findRoot(x)] = findRoot(y);
    }

    public boolean isConnected(int x, int y) {
        return findRoot(x) == findRoot(y);
    }
}
