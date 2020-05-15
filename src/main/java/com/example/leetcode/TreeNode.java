package com.example.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static TreeNode create(Integer[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        int index = 0;
        TreeNode root = new TreeNode(array[index++]);
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll == null) {
                    continue;
                }
                poll.left = (index < array.length && array[index] != null) ? new TreeNode(array[index]) : null;
                index++;
                deque.add(poll.left);
                poll.right = (index < array.length && array[index] != null) ? new TreeNode(array[index]) : null;
                index++;
                deque.add(poll.right);
            }
        }
        return root;
    }
}