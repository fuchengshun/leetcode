package com.example.leetcode;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode create(int[] nums) {
        ListNode pre = new ListNode(0), cur = pre;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return pre.next;
    }

    @Override
    public String toString() {
        ListNode temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            sb.append(temp.val);
            if (temp.next != null) {
                sb.append("->");
            }
            temp = temp.next;
        }
        return sb.toString();
    }
}
