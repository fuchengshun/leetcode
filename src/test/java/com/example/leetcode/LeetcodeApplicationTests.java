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
        new LeetCode2().findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}});
    }
}