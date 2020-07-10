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

    @Test
    void respace() {
        int i = t.respace(new String[]{"looked", "just", "like", "her", "brother"}, "jesslookedjustliketimherbrother");
    }

    @Test
    void gameOfLife() {
        t.gameOfLife(new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}});
    }

    @Test
    void sortColors() {
        t.sortColors(new int[]{2,0,2,1,1,0});
    }

    @Test
    void nextPermutation() {
        t.nextPermutation(new int[]{1,3,2});
    }

    @Test
    void fib() {
        int fib = t.fib(3);
    }
}