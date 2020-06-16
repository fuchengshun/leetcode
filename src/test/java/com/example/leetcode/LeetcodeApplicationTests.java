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
        int a=5;
        System.out.println(a++==5?a+1:a+10);
        Codec codec = new Codec();
        TreeNode treeNode = TreeNode.create(new Integer[]{1, 2, 3, null, null, 4, 5,7});
        String serialize = codec.serialize(treeNode);
        TreeNode deserialize = codec.deserialize(serialize);
        String serialize1 = codec.serialize(deserialize);
//        List<List<String>> ladders = new LeetCode3().findLadders("nanny", "aloud", Arrays.asList(data));
    }
    public int f(int i){
        System.out.println(i);
        return i+10;
    }
}