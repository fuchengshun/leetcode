package com.example.leetcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class LeetcodeApplicationTests {
    @Value("${user-data}")
    private int[] data;

    @Test
    void contextLoads() {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.right = treeNode4;
        treeNode3.right = treeNode5;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
//        new LeetCode().maxScoreII(new int[]{53,14,91,35,51,9,80,27,6,15,77,86,34,62,55,45,91,45,23,75,66,42,62,13,34,18,89,67,93,83,100,14,92,73,48,2,47,93,99,100,88,84,48},43);
        new LeetCode().maximalSquare(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}});
    }
}
