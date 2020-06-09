package com.example.leetcode;

class UnionFind {
    int[] roots;

    UnionFind(int num) {
        roots = new int[num];
        for (int i = 0; i < num; i++) {
            roots[i] = i;
        }
    }

    /**
     * 找出顶点x的根节点
     *
     * @param index
     * @return
     */
    public int findRoot(int index) {
        while (roots[index] != index) {
            roots[index] = roots[roots[index]];
            index = roots[index];
        }
        return index;
    }

    /**
     * 把顶点x和顶点y所在的集合合并到一起
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);
        roots[xRoot] = yRoot;
    }
}
