package com.example.leetcode;

import org.junit.jupiter.api.Test;

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
}