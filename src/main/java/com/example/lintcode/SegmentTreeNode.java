package com.example.lintcode;

public class SegmentTreeNode {
    int start,end,max;
    SegmentTreeNode left,right;

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
