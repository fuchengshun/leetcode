package com.example.leetcode;

import java.util.Deque;
import java.util.LinkedList;

class CQueue {
Deque<Integer> tail;
Deque<Integer> head;
    public CQueue() {
        tail =new LinkedList<>();
        head =new LinkedList<>();
    }

    public void appendTail(int value) {
        tail.push(value);
    }

    public int deleteHead() {
        if (tail.isEmpty()) {
            return -1;
        }
        while (!tail.isEmpty()) {
            head.push(tail.pop());
        }
        int ans = head.pop();
        while (!head.isEmpty()) {
            tail.push(head.pop());
        }
        return ans;
    }
}
