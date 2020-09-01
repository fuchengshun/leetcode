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
        int mid = start + end >>> 1;
        if (end <= mid) {
            return query(root.left, start, end);
        } else if (start >= mid) {
            return query(root.right, start, end);
        } else {
            return Math.max(query(root.left, start, mid), query(root.right, mid + 1, end));
        }
        // write your code here
    }
}
