package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.List;

class LeetCode4Test {
    private LeetCode4 t = new LeetCode4();
    @Test
    void countAndSay() {
        String s = t.countAndSay(5);
    }

    @Test
    void mySqrt() {
        int i = t.mySqrt(2147395599);
    }

    @Test
    void movingCount() {
        int i = t.movingCount(3, 1, 0);
    }

    @Test
    void respace() {
        int i = t.respace(new String[]{"looked", "just", "like", "her", "brother"}, "jesslookedjustliketimherbrother");
    }

    @Test
    void minimumLengthEncoding() {
        int i = t.minimumLengthEncoding(new String[]{"time", "me", "bell"});
    }

    @Test
    void myPow() {
        int a=2147483647;
        double v = t.myPow(2, 3);
    }

    @Test
    void zigzagLevelOrder() {
        List<List<Integer>> lists = t.zigzagLevelOrder(TreeNode.create(new Integer[]{3, 9, 20, null, null, 15, 7}));
    }

    @Test
    void deleteDuplicatesII() {
        ListNode node = t.deleteDuplicatesII(ListNode.create(new int[]{1, 1, 1, 2, 3}));
    }

    @Test
    void countSmaller() {
        List<Integer> integers = t.countSmaller(new int[]{5, 2, 6, 1});
    }
}