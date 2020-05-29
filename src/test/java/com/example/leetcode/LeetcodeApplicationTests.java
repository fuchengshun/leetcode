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
        Position p1 = new Position(3, 5);
        Position p2 = new Position(3, 5);
        System.out.println(p1.equals(p2));
        HashMap<Position, String> map = new HashMap<>();
        map.put(p1,"ssss");
        System.out.println(map.get(p2));
//        boolean b = new LeetCode2().canMeasureWater(2, 6, 5);
    }
}