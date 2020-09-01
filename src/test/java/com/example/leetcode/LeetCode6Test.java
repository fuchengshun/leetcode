package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class LeetCode6Test {
    private LeetCode6 t = new LeetCode6();

    @Test
    void findSubsequences() {
        List<List<Integer>> subsequences = t.findSubsequences(new int[]{2,3,2,2});
    }

    @Test
    void checkInclusion() {
        boolean b = t.checkInclusion("ab", "eidbaooo");
    }

    @Test
    void getMaxLen() {
        int maxLen = t.getMaxLen(new int[]{1,2,3,5,-6,4,0,10});
    }

    @Test
    void leastInterval() {
        int i = t.leastInterval(new char[]{'A','A','A','B','B','B'}, 2);
    }
}