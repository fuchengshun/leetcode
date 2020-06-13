package com.example.leetcode;

import java.util.LinkedList;

class BrowserHistory {
    LinkedList<String> linkedList = new LinkedList<>();
    int current = -1;

    public BrowserHistory(String homepage) {
        this.linkedList.add(homepage);
        this.current = 0;
    }

    public void visit(String url) {
        while (linkedList.size() > current + 1) {
            linkedList.removeLast();
        }
        linkedList.add(url);
        current++;
    }

    public String back(int steps) {
        while (steps > 0 && current > 0) {
            current--;
            steps--;
        }
        return linkedList.get(current);
    }

    public String forward(int steps) {
        while (steps > 0 && current < linkedList.size()-1) {
            steps--;
            current++;
        }
        return linkedList.get(current);
    }
}
