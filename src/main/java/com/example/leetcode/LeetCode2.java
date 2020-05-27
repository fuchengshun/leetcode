package com.example.leetcode;


import org.omg.CORBA.ARG_IN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode headTemp = head;
        for (int i = 0; i < k; i++) {
            stack.add(headTemp);
            if (headTemp == null) {
                return head;
            }
            headTemp = headTemp.next;
        }
        ListNode start = new ListNode(0), cur = start;
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        if (cur != null) {
            cur.next = reverseKGroup(headTemp, k);
        }
        return start.next;
    }

    /**
     * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：A = [4,5,0,-2,-3,1], K = 5
     * 输出：7
     * 解释：
     * 有 7 个子数组满足其元素之和可被 K = 5 整除：
     * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length <= 30000
     * -10000 <= A[i] <= 10000
     * 2 <= K <= 10000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int tempSum = 0, ans = 0;
        for (int i = 0; i < A.length; i++) {
            tempSum += A[i];
            int key = (tempSum % K + K) % K;
            ans += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return ans;
    }

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     * <p>
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        ArrayList<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        if (list.size() == 0) {
            return null;
        }
        ListNode start = new ListNode(0), cur = start;
        k %= list.size();
        for (int i = 0; i < list.size(); i++) {
            cur.next = list.get((list.size() - k + i) % list.size());
            cur = cur.next;
        }
        cur.next = null;
        return start.next;
    }

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * <p>
     * 说明：解集不能包含重复的子集。
     * <p>
     * 示例:
     * <p>
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     * [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        subsetsDfs(nums, new ArrayList<>(), ans);
        return ans;
    }

    public void subsetsDfs(int[] nums, ArrayList<Integer> path, List<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (Integer num : path) {
                if (num != null) {
                    temp.add(num);
                }
            }
            ans.add(temp);
            return;
        }
        path.add(nums[path.size()]);
        subsetsDfs(nums, path, ans);
        path.remove(path.size() - 1);
        path.add(null);
        subsetsDfs(nums, path, ans);
        path.remove(path.size() - 1);
    }

    /**
     * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
     * <p>
     * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
     * <p>
     * 格雷编码序列必须以 0 开头。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: 2
     * 输出: [0,1,3,2]
     * 解释:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     * <p>
     * 对于给定的 n，其格雷编码序列并不唯一。
     * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
     * <p>
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
     * 示例 2:
     * <p>
     * 输入: 0
     * 输出: [0]
     * 解释: 我们定义格雷编码序列必须以 0 开头。
     *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
     *      因此，当 n = 0 时，其格雷编码序列为 [0]。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/gray-code
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        if (n == 0) {
            ArrayList<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }
        List<Integer> ans = grayCode(n - 1);
        for (int i = ans.size() - 1; i >= 0; i--) {
            ans.add(ans.get(i) + (1 << n - 1));
        }
        return ans;
    }
}
