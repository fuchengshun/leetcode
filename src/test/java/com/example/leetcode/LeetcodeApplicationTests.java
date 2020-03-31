package com.example.leetcode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LeetcodeApplicationTests {

    @Test
    void contextLoads() {
        int numberOfLIS = new LeetCode().findNumberOfLIS(new int[]{4,10,4,3,8,9});
    }
}
