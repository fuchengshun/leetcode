package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FDATree {
    public static final String SIGN = "+-";
    public static final String E = "Ee";
    public static final String DOT = ".";
    public static final String NUMBER = "0123456789";
    List<Node> root = new ArrayList<>();

    public boolean isNumber(String s) {
        for (FDATree.Node node : root) {
            if (check(node, s.trim(), 0)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(Node node, String s, int i) {
        if (i >= s.length() && s.length() > 0) {
            return true;
        }
        if (s.length() == 0 || node.value.indexOf(s.charAt(i)) == -1 || i == s.length() - 1 && !node.canBeLast) {
            return false;
        }
        for (Node n : node.next) {
            if (check(n, s, i + 1)) {
                return true;
            }
        }
        return false;
    }

    static class Node {
        String value;
        boolean canBeLast;
        List<Node> next = new ArrayList<>();

        public Node(String value, boolean canBeLast) {
            this.value = value;
            this.canBeLast = canBeLast;
        }
    }

    public FDATree() {
        Node node1 = new Node(SIGN, false);
        Node node2 = new Node(NUMBER, true);
        Node node3 = new Node(DOT, true);
        Node node4 = new Node(DOT, false);
        Node node5 = new Node(NUMBER, true);
        Node node6 = new Node(E, false);
        Node node7 = new Node(SIGN, false);
        Node node8 = new Node(NUMBER, true);
        node8.next.add(node8);
        node7.next.add(node8);
        node6.next.add(node7);
        node6.next.add(node8);
        node5.next.add(node6);
        node5.next.add(node5);
        node4.next.add(node5);
        node1.next.add(node4);
        node1.next.add(node2);
        node2.next.add(node3);
        node2.next.add(node2);
        node2.next.add(node6);
        node3.next.add(node5);
        node3.next.add(node6);
        root.add(node1);
        root.add(node2);
        root.add(node4);
    }
}
