package com.example.leetcode;

import com.sun.org.apache.xerces.internal.xs.StringList;
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
        List<List<String>> ladders = new LeetCode3().findLadders("hot", "dog", Arrays.asList("hot","cog","dog","tot","hog","hop","pot","dot"));
    }
}