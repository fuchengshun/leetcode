package com.example.lintcode;

public class SegmentTreeNode {
    int start,end,max;
    SegmentTreeNode left,right;

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }

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
}
