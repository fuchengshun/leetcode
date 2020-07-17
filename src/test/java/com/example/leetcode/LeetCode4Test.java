package com.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    void levelOrderBottom() {
        List<List<Integer>> list = t.levelOrderBottom(TreeNode.create(new Integer[]{3, 9, 20, null, null, 15, 7}));
    }

    @Test
    void buildTree() {
        TreeNode treeNode = t.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }

    @Test
    void minimumTotalII() {
        Integer[][] arr= {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(arr[0]));
        list.add(Arrays.asList(arr[1]));
        list.add(Arrays.asList(arr[2]));
        list.add(Arrays.asList(arr[3]));
        t.minimumTotalII(list);
    }

    @Test
    void divide() {
//        int divide = t.divide(1004958205, -2137325331);
    }

    @Test
    void hammingWeight() {
//        t.hammingWeight(4294967293);
        int[] a={8};
        f(a);
    }
    public void f(int[] i){
        i[0]=10;
    }
}