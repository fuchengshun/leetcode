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
        ListNode[] listNodes = new ListNode[3];
        listNodes[0]=ListNode.create(new int[]{1,4,5});
        listNodes[1]=ListNode.create(new int[]{1,3,4});
        listNodes[2]=ListNode.create(new int[]{2,6});
        ListNode node = new LeetCode2().mergeKLists(listNodes);
    }
}