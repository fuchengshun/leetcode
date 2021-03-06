package com.example.leetcode;


import java.util.*;

import static java.util.Comparator.*;

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

    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * <p>
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     * <p>
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * <p>
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     * <p>
     * 示例:
     * <p>
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decode-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        while (s.indexOf(']') != -1) {
            int k = s.indexOf(']'), i = k - 1;
            while (s.charAt(i) != '[') {
                i--;
            }
            //举例 e100[ab] ,k是]的下标，i是[的下标，m是e的下标
            int m = i - 1, counts = 0, n = 1;
            while (m >= 0 && s.charAt(m) >= '0' && s.charAt(m) <= '9') {
                counts += (s.charAt(m) - '0') * n;
                n *= 10;
                m--;
            }
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < counts; j++) {
                temp.append(s, i + 1, k);
            }
            s = s.replace(s.substring(m + 1, k + 1), temp.toString());
        }
        return s;
    }

    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * <p>
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     * <p>
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，
     * 则你的函数不需要进行转换，即无法进行有效转换。
     * <p>
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     * <p>
     * 提示：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，
     * 请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: "42"
     * 输出: 42
     * 示例 2:
     * <p>
     * 输入: "   -42"
     * 输出: -42
     * 解释: 第一个非空白字符为 '-', 它是一个负号。
     *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
     * 示例 3:
     * <p>
     * 输入: "4193 with words"
     * 输出: 4193
     * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
     * 示例 4:
     * <p>
     * 输入: "words and 987"
     * 输出: 0
     * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     * 因此无法执行有效的转换。
     * 示例 5:
     * <p>
     * 输入: "-91283472332"
     * 输出: -2147483648
     * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
     *      因此返回 INT_MIN (−231) 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (myAtoiOver(sb, c)) {
                break;
            }
            if (c != ' ') {
                sb.append(c);
            }
        }
        return myAtoiCreate(sb);
    }

    //"±1000000000000005555555555"
    private int myAtoiCreate(StringBuilder sb) {
        long ans = 0, multi = 1;
        long symbol = sb.length() > 0 && sb.charAt(0) == '-' ? -1 : 1;
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '-' || sb.charAt(i) == '+') {
                break;
            }
            ans += (sb.charAt(i) - '0') * multi;
            if (ans * symbol > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (ans * symbol < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            multi = multi > Integer.MAX_VALUE ? Integer.MAX_VALUE : multi * 10;
        }
        return (int) (ans * symbol);
    }

    private boolean myAtoiOver(StringBuilder sb, char c) {
        if (!(c >= '0' && c <= '9' || c == '-' || c == '+')) {
            return sb.length() != 0 || c != ' ';
        }
        if (c == '-' || c == '+') {
            return sb.length() != 0;
        }
        return false;
    }

    /**
     * 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。
     * 初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，
     * 或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，
     * 小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。
     * 小球位于编号 0 处的弹簧时不能再向左弹。
     * <p>
     * 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，
     * 即向右越过编号 N-1 的弹簧。
     * <p>
     * 示例 1：
     * <p>
     * 输入：jump = [2, 5, 1, 1, 1, 1]
     * <p>
     * 输出：3
     * <p>
     * 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
     * <p>
     * 限制：
     * <p>
     * 1 <= jump.length <= 10^6
     * 1 <= jump[i] <= 10000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param jump
     * @return
     */
    public int minJump(int[] jump) {
        if (jump == null || jump.length == 0) {
            return 0;
        }
        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);
        jump[0] = -1;
        int ans = 0;
        while (!deque.isEmpty()) {
            ans++;
            for (int i = deque.size(); i > 0; i--) {
                Integer poll = deque.poll();
                if (poll == null) {
                    continue;
                }
                if (poll + jump[poll] >= jump.length) {
                    return ans;
                }
                for (int j = 0; j < poll; j++) {
                    if (jump[j] != -1) {
                        deque.add(j);
                        jump[j] = -1;
                    }
                }
                if (jump[poll + jump[poll]] != -1) {
                    deque.add(poll + jump[poll]);
                    jump[poll + jump[poll]] = -1;
                }
            }
        }
        return ans;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * 示例 2:
     * <p>
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i1 = 0, i2 = 0, ans = 0;
        for (int num : nums) {
            ans = Math.max(i1, i2 + num);
            i2 = i1;
            i1 = ans;
        }
        return ans;
    }

    /**
     * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
     * <p>
     * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：target = 9
     * 输出：[[2,3,4],[4,5]]
     * 示例 2：
     * <p>
     * 输入：target = 15
     * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
     *  
     * <p>
     * 限制：
     * <p>
     * 1 <= target <= 10^5
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        int k = (target - 1) / 2, left = 0, right = 0, sum = 1;
        List<int[]> list = new ArrayList<>();
        while (right < k + 1) {
            if (sum == target) {
                int[] temp = new int[right - left + 1];
                for (int i = 0, v = left + 1; i < temp.length; i++) {
                    temp[i] = v++;
                }
                list.add(temp);
                right++;
                sum += right + 1;
            }
            if (sum > target) {
                sum -= left + 1;
                left++;
            }
            if (sum < target) {
                right++;
                sum += right + 1;
            }
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 给定一个整数 n，返回 n! 结果尾数中零的数量。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 3
     * 输出: 0
     * 解释: 3! = 6, 尾数中没有零。
     * 示例 2:
     * <p>
     * 输入: 5
     * 输出: 1
     * 解释: 5! = 120, 尾数中有 1 个零.
     * 说明: 你算法的时间复杂度应为 O(log n) 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }
        return trailingZeroes(n / 5) + n / 5;
    }

    /**
     * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
     * <p>
     * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     * <p>
     * 你允许：
     * <p>
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
     * 示例 1: (From the famous "Die Hard" example)
     * <p>
     * 输入: x = 3, y = 5, z = 4
     * 输出: True
     * 示例 2:
     * <p>
     * 输入: x = 2, y = 6, z = 5
     * 输出: False
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/water-and-jug-problem
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public boolean canMeasureWater(int x, int y, int z) {
        LinkedList<int[]> linkedList = new LinkedList<>();
        HashMap<String, Boolean> visited = new HashMap<>();
        linkedList.add(new int[]{0, 0});
        while (!linkedList.isEmpty()) {
            int[] pop = linkedList.pop();
            if (pop[0] == z || pop[1] == z || pop[0] + pop[1] == z) {
                return true;
            }
            canMeasureWaterHelper(0, pop[1], linkedList, visited);
            canMeasureWaterHelper(x, pop[1], linkedList, visited);
            canMeasureWaterHelper(pop[0], 0, linkedList, visited);
            canMeasureWaterHelper(pop[0], y, linkedList, visited);
            canMeasureWaterHelper(Math.min(pop[0] + pop[1], x), pop[0] + pop[1] < x ? 0 : pop[0] + pop[1] - x, linkedList, visited);
            canMeasureWaterHelper(pop[0] + pop[1] < y ? 0 : pop[0] + pop[1] - y, Math.min(pop[0] + pop[1], y), linkedList, visited);
        }
        return false;
    }

    private void canMeasureWaterHelper(int curX, int curY, LinkedList<int[]> linkedList, HashMap<String, Boolean> visited) {
        String key = curX + " " + curY;
        if (visited.get(key) == null) {
            linkedList.add(new int[]{curX, curY});
            visited.put(key, true);
        }
    }

    /**
     * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     * <p>
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
     * 则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: n = 5, m = 3
     * 输出: 3
     * 示例 2：
     * <p>
     * 输入: n = 10, m = 17
     * 输出: 2
     *  
     * <p>
     * 限制：
     * <p>
     * 1 <= n <= 10^5
     * 1 <= m <= 10^6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (m + lastRemaining(n - 1, m)) % n;
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     *  
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     *  
     * <p>
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     *  
     * <p>
     * 进阶：
     * <p>
     * 你可以运用递归和迭代两种方法解决这个问题吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/symmetric-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode q, TreeNode p) {
        if (q == null) {
            return p == null;
        }
        if (p == null) {
            return false;
        }
        return p.val == q.val && isSymmetricHelper(q.left, p.right) && isSymmetricHelper(q.right, p.left);
    }

    /**
     * 给定一个二叉树，原地将它展开为一个单链表。
     * <p>
     *  
     * <p>
     * 例如，给定二叉树
     * <p>
     * 1
     * / \
     * 2   5
     * / \   \
     * 3   4   6
     * 将其展开为：
     * <p>
     * 1
     * \
     * 2
     * \
     * 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(list, root);
        for (int i = 0; i + 1 < list.size(); i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i + 1);
        }
    }

    private void preorderTraversal(List<TreeNode> list, TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root);
        preorderTraversal(list, root.left);
        preorderTraversal(list, root.right);
    }

    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-search
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    List<Position> path = new ArrayList<>();
                    path.add(new Position(i, j));
                    if (existHelper(board, word, path)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean existHelper(char[][] board, String word, List<Position> path) {
        if (path.size() == word.length()) {
            return true;
        }
        Position position = path.get(path.size() - 1);
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : direction) {
            if (inArea(position.x + dir[0], position.y + dir[1], board.length, board[0].length) && board[position.x + dir[0]][position.y + dir[1]] == word.charAt(path.size()) && !path.contains(new Position(position.x + dir[0], position.y + dir[1]))) {
                path.add(new Position(position.x + dir[0], position.y + dir[1]));
                if (existHelper(board, word, path)) {
                    return true;
                }
                path.remove(path.size() - 1);
            }
        }
        return false;
    }

    private boolean inArea(int x, int y, int row, int col) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }
        ArrayList<Boolean> ans = new ArrayList<>();
        for (int candy : candies) {
            ans.add(candy + extraCandies >= max);
        }
        return ans;
    }

    /**
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            deque.push(cur);
            cur = cur.next;
        }
        while (!deque.isEmpty()) {
            if (deque.pop().val != head.val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode cur = slow, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        while (pre != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        return true;
    }

    /**
     * 反转一个单链表。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     * 进阶:
     * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
     * <p>
     * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     * <p>
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
     *  
     * <p>
     * 提示：
     * <p>
     * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 1 <= numCourses <= 10^5
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        int[] count = new int[numCourses];
        for (int[] p : prerequisites) {
            count[p[0]]++;
            list.get(p[1]).add(p[0]);
        }
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                deque.add(i);
            }
        }
        while (!deque.isEmpty()) {
            numCourses--;
            for (Integer i : list.get(deque.pop())) {
                if (--count[i] == 0) {
                    deque.add(i);
                }
            }
        }
        return numCourses == 0;
    }

    /**
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     * <p>
     * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
     * <p>
     * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 2, [[1,0]]
     * 输出: [0,1]
     * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
     * 示例 2:
     * <p>
     * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
     * 输出: [0,1,2,3] or [0,2,1,3]
     * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
     * 说明:
     * <p>
     * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] count = new int[numCourses];
        int[] ans = new int[numCourses];
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            lists.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            count[p[0]]++;
            lists.get(p[1]).add(p[0]);
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                deque.add(i);
            }
        }
        int index = 0;
        while (!deque.isEmpty()) {
            int pop = deque.pop();
            ans[index++] = pop;
            numCourses--;
            for (Integer i : lists.get(pop)) {
                if (--count[i] == 0) {
                    deque.add(i);
                }
            }
        }
        return numCourses == 0 ? ans : new int[]{};
    }

    /**
     * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
     * <p>
     * 要求时间复杂度为O(n)。
     * <p>
     *  
     * <p>
     * 示例1:
     * <p>
     * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= arr.length <= 10^5
     * -100 <= arr[i] <= 100
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] preSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            preSum[i] = sum;
        }
        int low = Integer.MAX_VALUE, ans = Integer.MIN_VALUE;
        for (int i = 0; i < preSum.length; i++) {
            low = Math.min(low, i - 1 < 0 ? 0 : preSum[i - 1]);
            ans = Math.max(ans, preSum[i] - low);
        }
        return ans;
    }

    public int maxSubArray2(int[] nums) {
        int ans = nums[0], pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i] + Math.max(pre, 0);
            ans = Math.max(temp, ans);
            pre = temp;
        }
        return ans;
    }

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 示例 1:
     * s = "abc", t = "ahbgdc"
     * <p>
     * 返回 true.
     * <p>
     * 示例 2:
     * s = "axc", t = "ahbgdc"
     * <p>
     * 返回 false.
     * <p>
     * 后续挑战 :
     * <p>
     * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
     * 在这种情况下，你会怎样改变代码？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/is-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int m = 0, n = 0;
        while (m < s.length() && n < t.length()) {
            if (t.charAt(n) == s.charAt(m)) {
                m++;
            }
            n++;
        }
        return m >= s.length();
    }

    /**
     * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
     * <p>
     * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，
     * 其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
     * <p>
     * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
     * <p>
     * 示例 1：
     * <p>
     * 输入：N = 10, K = 1, W = 10
     * 输出：1.00000
     * 说明：爱丽丝得到一张卡，然后停止。
     * 示例 2：
     * <p>
     * 输入：N = 6, K = 1, W = 10
     * 输出：0.60000
     * 说明：爱丽丝得到一张卡，然后停止。
     * 在 W = 10 的 6 种可能下，她的得分不超过 N = 6 分。
     * 示例 3：
     * <p>
     * 输入：N = 21, K = 17, W = 10
     * 输出：0.73278
     * 提示：
     * <p>
     * 0 <= K <= N <= 10000
     * 1 <= W <= 10000
     * 如果答案与正确答案的误差不超过 10^-5，则该答案将被视为正确答案通过。
     * 此问题的判断限制时间已经减少。
     *
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        if (K == 0) {
            return 0.0;
        }
        double[] dp = new double[K + W];
        for (int i = K; i <= N; i++) {
            dp[i] = 1.0;
        }
        for (int i = 1; i <= W; i++) {
            dp[K - 1] += dp[K - 1 + i] / W;
        }
        for (int i = K - 2; i >= 0; i--) {
            dp[i] = dp[i + 1] - (dp[i + W + 1] - dp[i + 1]) / W;
        }
        return dp[0];
    }

    /**
     * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     * <p>
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     * <p>
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     * <p>
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：2
     * 输出：true
     * 解释：爱丽丝选择 1，鲍勃无法进行操作。
     * 示例 2：
     * <p>
     * 输入：3
     * 输出：false
     * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/divisor-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        return divisorGameHelper(N, new Boolean[N + 1]);
    }

    private boolean divisorGameHelper(int N, Boolean[] dp) {
        if (dp[N] != null) {
            return dp[N];
        }
        for (int i = 1; i < N; i++) {
            if (N % i == 0 && !divisorGameHelper(N - i, dp)) {
                dp[N] = true;
                return true;
            }
        }
        dp[N] = false;
        return false;
    }

    /**
     * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     * <p>
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     * <p>
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     * <p>
     * 示例 1:
     * <p>
     * 输入: cost = [10, 15, 20]
     * 输出: 15
     * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
     *  示例 2:
     * <p>
     * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出: 6
     * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
     * 注意：
     * <p>
     * cost 的长度将会在 [2, 1000]。
     * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        Integer[] dp = new Integer[cost.length];
        return Math.min(minCostClimbingStairsHelper(cost, cost.length - 1, dp), minCostClimbingStairsHelper(cost, cost.length - 2, dp));
    }

    /**
     * 到达index，最少体力数
     */
    private int minCostClimbingStairsHelper(int[] cost, int index, Integer[] dp) {
        if (index == 0 || index == 1) {
            return cost[index];
        }
        if (dp[index] != null) {
            return dp[index];
        }
        dp[index] = Math.min(minCostClimbingStairsHelper(cost, index - 1, dp), minCostClimbingStairsHelper(cost, index - 2, dp)) + cost[index];
        return dp[index];
    }

    /**
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，
     * 计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     * <p>
     * 示例1:
     * <p>
     * 输入：n = 3
     * 输出：4
     * 说明: 有四种走法
     * 示例2:
     * <p>
     * 输入：n = 5
     * 输出：13
     * 提示:
     * <p>
     * n范围在[1, 1000000]之间
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/three-steps-problem-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        if (n < 4) {
            return n == 3 ? 4 : n;
        }
        long p1 = 1L, p2 = 2L, p3 = 4L, ans = 0;
        for (int i = 4; i <= n; i++) {
            ans = (p3 % 1000000007 + p2 % 1000000007 + p1 % 1000000007) % 1000000007;
            p1 = p2;
            p2 = p3;
            p3 = ans;
        }
        return (int) ans;
    }

    /**
     * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
     * <p>
     * 示例：
     * <p>
     * 输入： [-2,1,-3,4,-1,2,1,-5,4]
     * 输出： 6
     * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶：
     * <p>
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/contiguous-sequence-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int pre = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = Math.max(pre, 0) + nums[i];
            ans = Math.max(ans, temp);
            pre = temp;
        }
        return ans;
    }

    /**
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     * <p>
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        int start = 0, ans = 0, temp = 0;
        deque.push(s.charAt(start++));
        while (start < s.length()) {
            if (s.charAt(start) == ')' && !deque.isEmpty() && deque.peek() == '(') {
                deque.pop();
                temp += 2;
                ans = Math.max(ans, temp);
            } else {
                deque.push(s.charAt(start));
                temp = 0;
            }
            start++;
        }
        return ans;
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * 示例 2：
     * <p>
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     *  
     * <p>
     * 限制：
     * <p>
     * 0 <= matrix.length <= 100
     * 0 <= matrix[i].length <= 100
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] ans = new int[matrix.length * matrix[0].length];
        int left = 0, right = matrix[0].length - 1, up = 0, down = matrix.length - 1, index = 0;
        while (left <= right && up <= down) {
            for (int i = left; i <= right && up <= down; i++) {
                ans[index++] = (matrix[up][i]);
            }
            up++;
            for (int i = up; i <= down && left <= right; i++) {
                ans[index++] = (matrix[i][right]);
            }
            right--;
            for (int i = right; i >= left && up <= down; i--) {
                ans[index++] = (matrix[down][i]);
            }
            down--;
            for (int i = down; i >= up && left <= right; i--) {
                ans[index++] = (matrix[i][left]);
            }
            left++;
        }
        return ans;
    }

    /**
     * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，
     * 并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     * <p>
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：["a==b","b!=a"]
     * 输出：false
     * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
     * 示例 2：
     * <p>
     * 输出：["b==a","a==b"]
     * 输入：true
     * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
     * 示例 3：
     * <p>
     * 输入：["a==b","b==c","a==c"]
     * 输出：true
     * 示例 4：
     * <p>
     * 输入：["a==b","b!=c","c==a"]
     * 输出：false
     * 示例 5：
     * <p>
     * 输入：["c==c","b==d","x!=z"]
     * 输出：true
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= equations.length <= 500
     * equations[i].length == 4
     * equations[i][0] 和 equations[i][3] 是小写字母
     * equations[i][1] 要么是 '='，要么是 '!'
     * equ
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        UnionFind unionFind = new UnionFind(128);
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                unionFind.union(equation.charAt(0), equation.charAt(3));
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!' && unionFind.findRoot(equation.charAt(0)) == unionFind.findRoot(equation.charAt(3))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，
     * B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
     * <p>
     * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，
     * 否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [[1,1,0],
     * [1,1,0],
     * [0,0,1]]
     * 输出: 2
     * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
     * 第2个学生自己在一个朋友圈。所以返回2。
     * 示例 2:
     * <p>
     * 输入:
     * [[1,1,0],
     * [1,1,1],
     * [0,1,1]]
     * 输出: 1
     * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
     * 注意：
     * <p>
     * N 在[1,200]的范围内。
     * 对于所有学生，有M[i][i] = 1。
     * 如果有M[i][j] = 1，则有M[j][i] = 1。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/friend-circles
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        UnionFind unionFind = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < unionFind.roots.length; i++) {
            if (i == unionFind.roots[i]) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(grid.length * grid[0].length);
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                for (int[] d : direction) {
                    if (numIslandsValidate(grid, i + d[0], j + d[1]) && grid[i][j] == grid[i + d[0]][j + d[1]]) {
                        unionFind.union(i * grid[i].length + j, (i + d[0]) * grid[i + d[0]].length + j + d[1]);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < unionFind.roots.length; i++) {
            if (i == unionFind.roots[i] && grid[i / grid[0].length][i % grid[0].length] == 1) {
                ans++;
            }
        }
        return ans;
    }

    private boolean numIslandsValidate(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
    }

    /**
     * 在本问题中, 树指的是一个连通且无环的无向图。
     * <p>
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * <p>
     * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
     * <p>
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。
     * 答案边 [u, v] 应满足相同的格式 u < v。
     * <p>
     * 示例 1：
     * <p>
     * 输入: [[1,2], [1,3], [2,3]]
     * 输出: [2,3]
     * 解释: 给定的无向图为:
     * 1
     * / \
     * 2 - 3
     * 示例 2：
     * <p>
     * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
     * 输出: [1,4]
     * 解释: 给定的无向图为:
     * 5 - 1 - 2
     * |   |
     * 4 - 3
     * 注意:
     * <p>
     * 输入的二维数组大小在 3 到 1000。
     * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/redundant-connection
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for (int[] edge : edges) {
            if (unionFind.isConnected(edge[0], edge[1])) {
                if (edge[1] < edge[0]) {
                    return new int[]{edge[1], edge[0]};
                }
                return new int[]{edge[0], edge[1]};
            }
            unionFind.union(edge[0], edge[1]);
        }
        return null;
    }

    /**
     * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，
     * 其中 connections[i] = [a, b] 连接了计算机 a 和 b。
     * <p>
     * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
     * <p>
     * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，
     * 并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
     * <p>
     * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
     * 输出：1
     * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @param connections
     * @return
     */
    public int makeConnected(int n, int[][] connections) {
        UnionFind unionFind = new UnionFind(n);
        int removable = 0;
        for (int[] con : connections) {
            if (unionFind.isConnected(con[0], con[1])) {
                removable++;
            } else {
                unionFind.union(con[0], con[1]);
            }
        }
        int group = 0;
        for (int i = 0; i < unionFind.roots.length; i++) {
            if (unionFind.roots[i] == i) {
                group++;
            }
        }
        return group - 1 > removable ? -1 : group - 1;
    }

    /**
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     * <p>
     * 要求算法的时间复杂度为 O(n)。
     * <p>
     * 示例:
     * <p>
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (Integer next : set) {
            if (set.contains(next - 1)) {
                continue;
            }
            int temp = 0;
            while (set.contains(next)) {
                temp++;
                next++;
            }
            ans = Math.max(ans, temp);
        }
        return ans;
    }

    /**
     * 给定一个整数矩阵，找出最长递增路径的长度。
     * <p>
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums =
     * [
     * [9,9,4],
     * [6,6,8],
     * [2,1,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径为 [1, 2, 6, 9]。
     * 示例 2:
     * <p>
     * 输入: nums =
     * [
     * [3,4,5],
     * [3,2,6],
     * [2,2,1]
     * ]
     * 输出: 4
     * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] count = new int[matrix.length][matrix[0].length];
        int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int[] d : direction) {
                    if (longestIncreasingPathVerify(matrix, i + d[0], j + d[1]) && matrix[i + d[0]][j + d[1]] < matrix[i][j]) {
                        count[i][j]++;
                    }
                }
            }
        }
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i].length; j++) {
                if (count[i][j] == 0) {
                    deque.add(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        while (!deque.isEmpty()) {
            ans++;
            for (int size = deque.size(); size > 0; size--) {
                int[] poll = deque.poll();
                for (int[] d : direction) {
                    if (longestIncreasingPathVerify(matrix, poll[0] + d[0], poll[1] + d[1]) && matrix[poll[0] + d[0]][poll[1] + d[1]] > matrix[poll[0]][poll[1]]) {
                        if (--count[poll[0] + d[0]][poll[1] + d[1]] == 0) {
                            deque.add(new int[]{poll[0] + d[0], poll[1] + d[1]});
                        }
                    }
                }
            }
        }
        return ans;
    }

    private boolean longestIncreasingPathVerify(int[][] matrix, int i, int j) {
        return i >= 0 && j >= 0 && i < matrix.length && j < matrix[i].length;
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int datum = nums[left], i = left, j = right;
        while (i < j) {
            while (nums[j] >= datum && i < j) {
                j--;
            }
            while (nums[i] <= datum && i < j) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[left] = nums[i];
        nums[i] = datum;
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    /**
     * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
     * <p>
     * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
     * <p>
     * 返回区域的数目。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        int width = grid.length + 1;
        UnionFind unionFind = new UnionFind(width * width);
        for (int i = 1; i < width; i++) {
            unionFind.union(i - 1, i);
            unionFind.union(width * (width - 1) + i - 1, width * (width - 1) + i);
            unionFind.union((i - 1) * width, i * width);
            unionFind.union((i - 1) * width + width - 1, i * width + width - 1);
        }
        int ans = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == '/') {
                    if (unionFind.isConnected(i * width + j + 1, (i + 1) * width + j)) {
                        ans++;
                    }
                    unionFind.union(i * width + j + 1, (i + 1) * width + j);
                }
                if (grid[i].charAt(j) == '\\') {
                    if (unionFind.isConnected(i * width + j, (i + 1) * width + j + 1)) {
                        ans++;
                    }
                    unionFind.union(i * width + j, (i + 1) * width + j + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     * <p>
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *  
     * <p>
     * 提示：
     * <p>
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
     * 你可以按任意顺序返回答案
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        priorityQueue.addAll(map.keySet());
        int[] ans = new int[k];
        for (int i = 0; i < k && !priorityQueue.isEmpty(); i++) {
            ans[i] = priorityQueue.poll();
        }
        return ans;
    }

    /**
     * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
     * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * matrix = [
     * [ 1,  5,  9],
     * [10, 11, 13],
     * [12, 13, 15]
     * ],
     * k = 8,
     * <p>
     * 返回 13。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                priorityQueue.add(anInt);
            }
        }
        for (int i = 0; i < k - 1; i++) {
            priorityQueue.poll();
        }
        return priorityQueue.poll();
    }

    /**
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     * <p>
     * （这里，平面上两点之间的距离是欧几里德距离。）
     * <p>
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * 示例 2：
     * <p>
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((b, a) -> a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1]);
        for (int[] point : points) {
            if (queue.size() < K) {
                queue.add(new int[]{point[0], point[1]});
            } else if (point[0] * point[0] + point[1] * point[1] < queue.peek()[0] * queue.peek()[0] + queue.peek()[1] * queue.peek()[1]) {
                queue.poll();
                queue.add(new int[]{point[0], point[1]});
            }
        }
        int[][] ans = new int[queue.size()][2];
        for (int i = 0; !queue.isEmpty(); i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }

    /**
     * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，
     * 而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
     * <p>
     * 示例 1:
     * <p>
     * 输入: k = 5
     * <p>
     * 输出: 9
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/get-kth-magic-number-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        queue.add(1L);
        set.add(1L);
        for (int i = 0; i < k - 1; i++) {
            Long poll = queue.poll();
            if (!set.contains(poll * 3)) {
                set.add(poll * 3);
                queue.add(poll * 3);
            }
            if (!set.contains(poll * 5)) {
                set.add(poll * 5);
                queue.add(poll * 5);
            }
            if (!set.contains(poll * 7)) {
                set.add(poll * 7);
                queue.add(poll * 7);
            }
        }
        return queue.peek().intValue();
    }

    /**
     * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
     * <p>
     * 示例：
     * <p>
     * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
     * 输出： [1,2,3,4]
     * 提示：
     * <p>
     * 0 <= len(arr) <= 100000
     * 0 <= k <= min(100000, len(arr))
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i : arr) {
            if (queue.size() < k) {
                queue.add(i);
            } else if (i < queue.peek()) {
                queue.poll();
                queue.add(i);
            }
        }
        int[] ans = new int[queue.size()];
        for (int i = 0; !queue.isEmpty(); i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }

    /**
     * 有一堆石头，每块石头的重量都是正整数。
     * <p>
     * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。
     * 那么粉碎的可能结果如下：
     * <p>
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：[2,7,4,1,8,1]
     * 输出：1
     * 解释：
     * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
     * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
     * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
     * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/last-stone-weight
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            queue.add(stone);
        }
        while (queue.size() >= 2) {
            queue.add(Math.abs(queue.poll() - queue.poll()));
        }
        return queue.poll();
    }

    /**
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * "tree"
     * <p>
     * 输出:
     * "eert"
     * <p>
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        int[] words = new int[128];
        for (char c : s.toCharArray()) {
            words[c]++;
        }
        PriorityQueue<Character> queue = new PriorityQueue<>((a, b) -> words[b] - words[a]);
        for (char i = 0; i < words.length; i++) {
            if (words[i] > 0) {
                queue.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character poll = queue.poll();
            for (int i = 0; i < words[poll]; i++) {
                sb.append(poll);
            }
        }
        return sb.toString();
    }
}

