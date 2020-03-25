package com.example.leetcode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LeetcodeApplicationTests {

    @Test
    void contextLoads() {
        int[] in={1,2,5};
        int fib = new LeetCode().coinChange(in,100);
    }

}
