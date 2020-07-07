package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LeetCode3Test {
    private LeetCode3 t = new LeetCode3();

    @Test
    void uniquePathsWithObstacles() {
        int i = t.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}});
    }

    @Test
    void letterCasePermutation() {
        List<String> a1b2 = t.letterCasePermutation("a1b2");
    }

    @Test
    void subsetsWithDup() {
//        List<List<Integer>> lists = t.subsetsWithDup(new int[]{1, 2, 3});
    }

    @Test
    void hasPathSum() {
        TreeNode root = TreeNode.create(new Integer[0]);
        boolean b = t.hasPathSum(root, 0);
    }

    @Test
    void subsets() {
        List<List<Integer>> subsets = t.subsets(new int[]{1, 2, 3});
    }

    @Test
    void partitionLabels() {
        List<Integer> list = t.partitionLabels("caedbdedda");
//        List<Integer> list = t.partitionLabels("ababcbacadefegdehijhklij");
    }
}