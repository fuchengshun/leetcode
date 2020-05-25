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
        LRUCache cache = new LRUCache( 1 /* 缓存容量 */ );

        cache.put(2, 1);
        cache.get(2);
//        TreeNode node = new LeetCode().buildTree(new int[]{1,3,2,4},new int[]{1,2,3,4});
    }
}