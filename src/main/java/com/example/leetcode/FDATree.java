package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class FDATree {
    public static final String SIGN = "+-";
    public static final String E = "Ee";
    public static final String DOT = ".";
    public static final String NUMBER = "0123456789";
    List<Node> root = new ArrayList<>();

    public boolean isNumber(String s){
        for (FDATree.Node node : root) {
            if (check(node, s.trim(),0)){
                return true;
            }
        }
        return false;
    }
    //node节点下是否存在一条路径，使得s从下标i开始的字符都在这条路径中
    private boolean check(Node node, String s, int i) {
        //把s字符串遍历完了，说明找到了一条合法的路径
        if (i >= s.length()) {
            return true;
        }
        //下标i对应的字符不在当前节点中，或者i已经到末尾了，但是当前节点不允许作为最后一个节点，说明这次选择的路径不合法
        if (s.length()==0||node.value.indexOf(s.charAt(i)) == -1 || i == s.length() - 1 && !node.canBeLast) {
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
        Node node111 = new Node(NUMBER, true);
        node111.next.add(node111);
        Node node20 = new Node(NUMBER, true);
        node20.next.add(node20);
        Node node2 = new Node(DOT, false);
        node2.next.add(node20);
        root.add(node2);
        Node node10 = new Node(NUMBER, true);
        node10.next.add(node10);
        Node node1 = new Node(NUMBER, true);
        node1.next.add(node10);
        Node node110 = new Node(SIGN, false);
        node110.next.add(node111);
        Node node11 = new Node(E, false);
        node11.next.add(node110);
        node11.next.add(node111);
        node10.next.add(node11);
        node20.next.add(node11);
        node1.next.add(node11);
        Node node12 = new Node(DOT, true);
        node12.next.add(node11);
        node12.next.add(node20);
        node10.next.add(node12);
        node1.next.add(node12);
        root.add(node1);
        Node node0 = new Node(SIGN, false);
        node0.next.add(node1);
        node0.next.add(node2);
        root.add(node0);
    }
}
