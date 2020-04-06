package com.example.leetcode;

import java.util.Stack;

public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stack1.push(x);
        int size=stack2.size();
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        stack2.push(x);
        while (size>0){
            stack2.push(stack1.pop());
            size--;
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        int ans = stack2.pop();
        int length = stack2.size();
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        stack2.pop();
        while (length > 0) {
            stack1.push(stack2.pop());
            length--;
        }
        return ans;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return stack2.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack1.empty();
    }
}
