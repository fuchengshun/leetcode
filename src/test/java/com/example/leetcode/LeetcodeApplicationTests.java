package com.example.leetcode;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class LeetcodeApplicationTests {
    @Value("${user-data}")
    private String[] data;

    @Test
    void contextLoads() {
        char[] chars = "dddd".toCharArray();

        int obstacleGrid[][] = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int i = new LeetCode3().uniquePathsWithObstacles(obstacleGrid);
        ArrayList<Integer> objects = new ArrayList<>(10);
    }
}