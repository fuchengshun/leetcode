package com.example.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        ArrayList<String> ans = new ArrayList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll == null) {
                    ans.add("null");
                } else {
                    ans.add(String.valueOf(poll.val));
                    deque.add(poll.left);
                    deque.add(poll.right);
                }
            }
        }
        int i = ans.size() - 1;
        while (i >= 0 && "null".equals(ans.get(i))) {
            i--;
        }
        return "[" + String.join(",", ans.subList(0, i + 1)) + "]";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() < 3) {
            return null;
        }
        String[] split = data.substring(1, data.length() - 1).split(",");
        Integer[] nums = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = "null".equals(split[i]) ? null : Integer.valueOf(split[i]);
        }
        if (nums[0] == null) {
            return null;
        }
        int index = 0;
        TreeNode root = new TreeNode(nums[index++]);
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            for (int size = deque.size(); size > 0; size--) {
                TreeNode poll = deque.poll();
                if (poll != null) {
                    poll.left = index < nums.length && nums[index] != null ? new TreeNode(nums[index]) : null;
                    index++;
                    poll.right = index < nums.length && nums[index] != null ? new TreeNode(nums[index]) : null;
                    index++;
                    deque.add(poll.left);
                    deque.add(poll.right);
                }
            }
        }
        return root;
    }
}
