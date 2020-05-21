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
//        TreeNode treeNode = TreeNode.create(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        TreeNode treeNode = TreeNode.create(new Integer[]{3,5,1,6,2,0,8,null,null,7,4});
        TreeNode node = new LeetCode().lowestCommonAncestor(treeNode.left, treeNode.left, treeNode.right.right);
    }
}
