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
        String s = new LeetCode3().addBinary("1010", "1011");
    }
}