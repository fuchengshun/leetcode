package com.example.leetcode;

import org.junit.jupiter.api.Test;

class LeetCode5Test {
    private LeetCode5 t = new LeetCode5();

    @Test
    void decompressRLElist() {
    }

    @Test
    void recoverFromPreorder() {
        TreeNode treeNode = t.recoverFromPreorder("1-2--3---4-5--6---7");
    }

    @Test
    void recoverFromPreorderII() {
        TreeNode treeNode = t.recoverFromPreorderII("1-401--349---90--88");
    }

    @Test
    void getPermutation() {
        String permutation = t.getPermutation(3, 3);
    }
}