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
        ListNode listNode = ListNode.create(new int[]{4,2,1,3});
        new LeetCode().sortList(listNode);
    }
}
