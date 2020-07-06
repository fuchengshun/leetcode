package com.example.leetcode;

import org.junit.jupiter.api.Test;

class LeetCode3Test {
    private LeetCode3 t = new LeetCode3();

    @Test
    void uniquePathsWithObstacles() {
        int i = t.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
    }
}