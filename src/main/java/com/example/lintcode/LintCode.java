package com.example.lintcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LintCode {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SegmentTreeNode build(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode root = new SegmentTreeNode(start, end);
        if (start != end) {
            int mid = start + end >>> 1;
            root.left = build(start, mid);
            root.right = build(mid + 1, end);
        }
        return root;
        // write your code here
    }

    public int query(SegmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.max;
        }
        int mid = root.start + root.end >>> 1;
        if (end <= mid) {
            return query(root.left, start, end);
        } else if (start > mid) {
            return query(root.right, start, end);
        } else {
            return Math.max(query(root.left, start, mid), query(root.right, mid + 1, end));
        }
        // write your code here
    }

    public void modify(SegmentTreeNode root, int index, int value) {
        if (root == null || root.start > index || root.end < index) {
            return;
        }
        if (root.start == index && root.end == index) {
            root.max = value;
            return;
        }
        int mid = root.start + root.end >>> 1;
        if (index <= mid) {
            modify(root.left, index, value);
        } else {
            modify(root.right, index, value);
        }
        root.max = Math.max(root.left.max, root.right.max);
        // write your code here
    }

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = nums[i];
            dp[i][i][1] = 0;
        }
        int[] sum = new int[n+1];
        for (int i = 0,temp=0; i < n; i++) {
            temp+=nums[i];
            sum[i+1]=temp;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                dp[i][j][0] = Math.max(dp[i + 1][j][1] + nums[i], dp[i][j - 1][1] + nums[j]);
                dp[i][j][1] = sum[j+1]-sum[i]-dp[i][j][0];
            }
        }
        return dp[0][n - 1][0] >= dp[0][n - 1][1];
    }
}
