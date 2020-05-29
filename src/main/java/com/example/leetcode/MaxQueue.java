package com.example.leetcode;

import java.util.LinkedList;

public class MaxQueue {
    LinkedList<Integer> max;
    LinkedList<Integer> list;

    public MaxQueue() {
        max = new LinkedList<>();
        list = new LinkedList<>();
    }

    public int max_value() {
        if (max.isEmpty()) {
            return -1;
        }
        return max.peekFirst();
    }

    public void push_back(int value) {
        list.addLast(value);
        while (!max.isEmpty() && max.peekLast() < value) {
            max.pollLast();
        }
        max.addLast(value);
    }

    public int pop_front() {
        if (list.isEmpty()) {
            return -1;
        }
        if (list.peekFirst().equals(max.peekFirst())) {
            max.pollFirst();
        }
        return list.pollFirst();
    }
}
