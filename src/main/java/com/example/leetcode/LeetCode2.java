package com.example.leetcode;


public class LeetCode2 {
    /**
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     * <p>
     * 示例：
     * <p>
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     * 说明：
     * <p>
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int left = 0, right = 0, minLength = Integer.MAX_VALUE, distance = t.length(), ansLeft = 0;
        int[] need = new int[128], contain = new int[128];
        for (char c : t.toCharArray()) {
            need[c]++;
        }
        while (right < s.length()) {
            //扩大窗口
            contain[s.charAt(right)]++;
            if (contain[s.charAt(right)] <= need[s.charAt(right)]) {
                distance--;
            }
            right++;
            while (distance == 0) {
                //缩小窗口
                if (contain[s.charAt(left)] <= need[s.charAt(left)]) {
                    if (minLength > right - left) {
                        minLength = right - left;
                        ansLeft = left;
                    }
                    distance++;
                }
                contain[s.charAt(left)]--;
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(ansLeft, ansLeft + minLength);
    }

    /**
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
     * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,4,2,2]
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: [3,1,3,4,2]
     * 输出: 3
     * 说明：
     * <p>
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (slow != fast);
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 输出: 1->1->2->3->4->4->5->6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergeKListsHelp(ans, list);
        }
        return ans;
    }

    private ListNode mergeKListsHelp(ListNode node1, ListNode node2) {
        ListNode start = new ListNode(0), cur = start;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                cur.next = node1;
                cur = cur.next;
                node1 = node1.next;
            } else {
                cur.next = node2;
                cur = cur.next;
                node2 = node2.next;
            }
        }
        if (node1 != null) {
            cur.next = node1;
        }
        if (node2 != null) {
            cur.next = node2;
        }
        return start.next;
    }

    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。
     * <p>
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 给你这个链表：1->2->3->4->5
     * <p>
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * <p>
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     * <p>
     *  
     * <p>
     * 说明：
     * <p>
     * 你的算法只能使用常数的额外空间。
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = reverseKGroupHelper(head, k), cur = start;
        for (int i = 0; i < k && cur != null; i++) {
            cur = cur.next;
        }
        if (cur != null) {
            cur.next = reverseKGroup(cur, k);
        }
        return start;
    }

    /**
     * 翻转前k个元素
     */
    public ListNode reverseKGroupHelper(ListNode head, int k) {
        return null;
    }
}
