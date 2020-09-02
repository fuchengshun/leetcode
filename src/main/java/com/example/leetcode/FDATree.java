package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FDATree {
    public static final String SIGN = "+-";
    public static final String E = "Ee";
    public static final String DOT = ".";
    public static final String NUMBER = "0123456789";
    List<Node> root = new ArrayList<>();

    static class Node {
        String value;
        List<Node> next = new ArrayList<>();

        public Node(String value) {
            this.value = value;
        }
    }

    public FDATree() {
        Node node20 = new Node(NUMBER);
        node20.next.add(node20);

        Node node2 = new Node(DOT);
        node2.next.add(node20);

        root.add(node2);

        Node node10 = new Node(NUMBER);
        node10.next.add(node2);
        node10.next.add(node10);

        Node node1 = new Node(NUMBER);
        node1.next.add(node10);

        Node node110 = new Node(SIGN);
        node110.next.add(node20);

        Node node11 = new Node(E);
        node11.next.add(node110);
        node11.next.add(node20);
        node1.next.add(node11);

        Node node120 = new Node(NUMBER);
        node120.next.add(node11);

        Node node12 = new Node(DOT);
        node12.next.add(node120);
        node1.next.add(node12);

        root.add(node1);

        Node node0 = new Node(SIGN);
        node0.next.add(node1);
        root.add(node0);
    }

    public boolean check(Node node, String s, int i) {
        if (i >= s.length()) {
            return true;
        }
        if (node.value.indexOf(s.charAt(i)) == -1) {
            return false;
        }
        for (Node n : node.next) {
            if (check(n, s, i + 1)) {
                return true;
            }
        }
        return false;
    }
}
