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
        List<Integer> list = new ArrayList<>(2);
        list.add(1);
        list.add(2);
//        boolean b = new LeetCode2().canMeasureWater(2, 6, 5);
    }
}