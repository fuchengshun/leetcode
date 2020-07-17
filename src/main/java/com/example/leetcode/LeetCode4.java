package com.example.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LeetCode4 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 给定 nums = [3,2,2,3], val = 3,
     * <p>
     * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2:
     * <p>
     * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
     * <p>
     * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     * <p>
     * 注意这五个元素可为任意顺序。
     * <p>
     * 你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }

    /**
     * 实现 strStr() 函数。
     * <p>
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle
     * 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:
     * <p>
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * <p>
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                i++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    /**
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "III"
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: "IV"
     * 输出: 4
     * 示例 3:
     * <p>
     * 输入: "IX"
     * 输出: 9
     * 示例 4:
     * <p>
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     * <p>
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/roman-to-integer
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && map.get(s.charAt(i)) < map.get(s.charAt(i + 1))) {
                ans += map.get(s.charAt(i + 1)) - map.get(s.charAt(i));
                i++;
            } else {
                ans += map.get(s.charAt(i));
            }
        }
        return ans;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * 示例 2:
     * <p>
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/plus-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum < 10 ? sum : sum - 10;
            carry = sum < 10 ? 0 : 1;
        }
        if (carry == 0) {
            return digits;
        }
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }

    /**
     * 12. 整数转罗马数字
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0) {
            if (nums[i] <= num) {
                num -= nums[i];
                sb.append(romans[i]);
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     * <p>
     * 注意：
     * <p>
     * 答案中不可以包含重复的四元组。
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     * <p>
     * 满足要求的四元组集合为：
     * [
     * [-1,  0, 0, 1],
     * [-2, -1, 1, 2],
     * [-2,  0, 0, 2]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/4sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int a = 0; a <= nums.length - 4; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int b = a + 1; b <= nums.length - 3; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int c = b + 1, d = nums.length - 1;
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum < target) {
                        c++;
                    } else if (sum > target) {
                        d--;
                    } else {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        while (c < d && nums[c] == nums[c + 1]) {
                            c++;
                        }
                        while (c < d && nums[d] == nums[d - 1]) {
                            d--;
                        }
                        d--;
                        c++;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    /**
     * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，
     * 长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
     * <p>
     * 返回的长度需要从小到大排列。
     * <p>
     * 示例：
     * <p>
     * 输入：
     * shorter = 1
     * longer = 2
     * k = 3
     * 输出： {3,4,5,6}
     * 提示：
     * <p>
     * 0 < shorter <= longer
     * 0 <= k <= 100000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/diving-board-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[]{};
        } else if (shorter == longer) {
            return new int[]{shorter * k};
        }
        int[] ans = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            ans[i] = longer * i + shorter * (k - i);
        }
        return ans;
    }

    /**
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
     * <p>
     * 注意：整数序列中的每一项将表示为一个字符串。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-and-say
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            int j = i;
            sb.append(s.charAt(j));
            while (i >= 0 && s.charAt(i) == s.charAt(j)) {
                i--;
            }
            sb.append(j - i);
        }
        return sb.reverse().toString();
    }

    /**
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
     * 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
     * <p>
     * 如果不存在最后一个单词，请返回 0 。
     * <p>
     * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 输入: "Hello World"
     * 输出: 5
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/length-of-last-word
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        for (int i = s.toCharArray().length - 1; i >= 0 && s.charAt(i) != ' '; i--) {
            sb.append(s.charAt(i));
        }
        return sb.length();
    }

    /**
     * 找出数组中重复的数字。
     * <p>
     * <p>
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
     * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * <p>
     * 示例 1：
     * <p>
     * 输入：
     * [2, 3, 1, 0, 2, 5, 3]
     * 输出：2 或 3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }

    /**
     * 实现 int sqrt(int x) 函数。
     * <p>
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     * <p>
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sqrtx
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        long left = 1, right = x;
        while (left < right) {
            long mid = (right + left) / 2;
            if (mid * mid > x) {
                right = mid - 1;
            } else if (mid * mid < x) {
                left = mid + 1;
            } else {
                return (int) mid;
            }
        }
        return left * left > x ? (int) left - 1 : (int) left;
    }

    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
     * 请问该机器人能够到达多少个格子？
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 2：
     * <p>
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     * 提示：
     * <p>
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        int ans = 0;
        int[][] visited = new int[m][n];
        LinkedList<int[]> deque = new LinkedList<>();
        deque.add(new int[]{0, 0});
        visited[0][0] = 1;
        while (!deque.isEmpty()) {
            ans++;
            int[] p = deque.pop();
            for (int[] d : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                int i = p[0] + d[0];
                int j = p[1] + d[1];
                if (movingCountChecked(i, j, m, n, k) && visited[i][j] == 0) {
                    deque.add(new int[]{i, j});
                    visited[i][j] = 1;
                }
            }
        }
        return ans;
    }

    private boolean movingCountChecked(int i, int j, int m, int n, int k) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        int sum = 0;
        while (i > 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j > 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum <= k;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，
     * 即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。
     * 这个链表的倒数第3个节点是值为4的节点。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * <p>
     * 返回链表 4->5.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
     * <p>
     * 示例 1：
     * <p>
     * 输入: s = "leetcode"
     * 输出: false
     * 示例 2：
     * <p>
     * 输入: s = "abc"
     * 输出: true
     * 限制：
     * <p>
     * 0 <= len(s) <= 100
     * 如果你不使用额外的数据结构，会很加分。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/is-unique-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        char[] chars = astr.toCharArray();
        Arrays.sort(chars);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "We are happy."
     * 输出："We%20are%20happy."
     *  
     * <p>
     * 限制：
     * <p>
     * 0 <= s 的长度 <= 10000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     * <p>
     * <p>
     * <p>
     * 在杨辉三角中，每个数是它左上方和右上方的数的和。
     * <p>
     * 示例:
     * <p>
     * 输入: 5
     * 输出:
     * [
     * [1],
     * [1,1],
     * [1,2,1],
     * [1,3,3,1],
     * [1,4,6,4,1]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/pascals-triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }
        if (numRows == 1) {
            ans.add(Arrays.asList(1));
            return ans;
        }
        List<List<Integer>> list = generate(numRows - 1);
        ArrayList<Integer> temp = new ArrayList<>();
        List<Integer> last = list.get(list.size() - 1);
        for (int i = 0; i < last.size(); i++) {
            temp.add(i == 0 ? 1 : last.get(i) + last.get(i - 1));
        }
        temp.add(1);
        list.add(temp);
        return list;
    }

    /**
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     * <p>
     * 不占用额外内存空间能否做到？
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * <p>
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 现有矩阵 matrix 如下：
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * <p>
     * 给定 target = 20，返回 false。
     * <p>
     *  
     * <p>
     * 限制：
     * <p>
     * 0 <= n <= 1000
     * <p>
     * 0 <= m <= 1000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
            if (matrix[i][0] == target) {
                return true;
            } else if (matrix[i][0] < target) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
     * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
     * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
     * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
     * <p>
     * 注意：本题相对原题稍作改动，只需返回未识别的字符数
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：
     * dictionary = ["looked","just","like","her","brother"]
     * sentence = "jesslookedjustliketimherbrother"
     * 输出： 7
     * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
     * 提示：
     * <p>
     * 0 <= len(sentence) <= 1000
     * dictionary中总字符数不超过 150000。
     * 你可以认为dictionary和sentence中只包含小写字母。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/re-space-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    public int respace(String[] dictionary, String sentence) {
        HashSet<String> set = new HashSet<>(Arrays.asList(dictionary));
        int[] dp = new int[sentence.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= sentence.length(); i++) {
            for (int k = 0; k < i; k++) {
                dp[i] = Math.min(dp[i], dp[k] + (set.contains(sentence.substring(k, i)) ? 0 : i - k));
            }
        }
        return dp[sentence.length()];
    }

    /**
     * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
     * <p>
     * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
     * <p>
     * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
     * <p>
     * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入: words = ["time", "me", "bell"]
     * 输出: 10
     * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= words.length <= 2000
     * 1 <= words[i].length <= 7
     * 每个单词都是小写字母 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/short-encoding-of-words
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String s : words) {
            for (int k = 1; k < s.length(); k++) {
                set.remove(s.substring(k));
            }
        }
        int ans = 0;
        for (String s : set) {
            ans += s.length() + 1;
        }
        return ans;
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * 示例 2:
     * <p>
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * 示例 3:
     * <p>
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 说明:
     * <p>
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/powx-n
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? myPowPositive(x, N) : 1 / myPowPositive(x, -N);
    }

    private double myPowPositive(double x, long n) {
        if (n == 0) {
            return 1;
        }
        double half = myPowPositive(x, n / 2);
        return n % 2 == 0 ? half * half : half * half * x;
    }

    /**
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     * <p>
     * 示例 1:
     * <p>
     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * <p>
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-image
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     */
    public void rotateII(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->1->2
     * 输出: 1->2
     * 示例 2:
     * <p>
     * 输入: 1->1->2->3->3
     * 输出: 1->2->3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            if (pre != null && cur.val == pre.val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
     * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: s = "abcdefg", k = 2
     * 输出: "cdefgab"
     * 示例 2：
     * <p>
     * 输入: s = "lrloseumgh", k = 6
     * 输出: "umghlrlose"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    /**
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回它的最小深度  2.
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * <p>
     * 说明:
     * 1 ≤ m ≤ n ≤ 链表长度。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
     * 输出: 1->4->3->2->5->NULL
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        LinkedList<ListNode> deque = new LinkedList<>();
        ListNode start = new ListNode(0), cur = start;
        for (int i = 1; i < m && head != null; i++) {
            cur.next = head;
            head = head.next;
            cur = cur.next;
        }
        for (int i = m; i <= n && head != null; i++) {
            deque.push(head);
            head = head.next;
        }
        while (!deque.isEmpty()) {
            cur.next = deque.pop();
            cur = cur.next;
        }
        cur.next = head;
        return start.next;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        deque.add(root);
        boolean toLeft = false;
        while (!deque.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int size = deque.size(); size > 0; size--) {
                TreeNode poll = deque.poll();
                temp.add(poll.val);
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
            }
            if (toLeft) {
                Collections.reverse(temp);
            }
            toLeft = !toLeft;
            ans.add(temp);
        }
        return ans;
    }

    public ListNode deleteDuplicatesII(ListNode head) {
        LinkedList<ListNode> deque = new LinkedList<>();
        while (head != null) {
            if (deque.isEmpty() || deque.peek().val != head.val) {
                deque.push(head);
                head = head.next;
            } else {
                ListNode pop = deque.pop();
                while (head != null && head.val == pop.val) {
                    head = head.next;
                }
            }
        }
        ListNode ans = null;
        while (!deque.isEmpty()) {
            ListNode pop = deque.pop();
            pop.next = ans;
            ans = pop;
        }
        return ans;
    }

    /**
     * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质：
     * counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     * <p>
     * 示例:
     * <p>
     * 输入: [5,2,6,1]
     * 输出: [2,1,1,0]
     * 解释:
     * 5 的右侧有 2 个更小的元素 (2 和 1).
     * 2 的右侧仅有 1 个更小的元素 (1).
     * 6 的右侧有 1 个更小的元素 (1).
     * 1 的右侧有 0 个更小的元素.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int[] indexes = new int[nums.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = i;
        }
        int[] temp = new int[nums.length];
        int[] ans = new int[indexes.length];
        mergeSort(0, indexes.length - 1, nums, indexes, temp, ans);
        ArrayList<Integer> list = new ArrayList<>();
        for (int an : ans) {
            list.add(an);
        }
        return list;
    }

    private void mergeSort(int left, int right, int[] nums, int[] indexes, int[] temp, int[] ans) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(left, mid, nums, indexes, temp, ans);
        mergeSort(mid + 1, right, nums, indexes, temp, ans);
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[indexes[i]] < nums[indexes[j]]) {
                ans[indexes[i]] += j - mid - 1;
                temp[k++] = indexes[i++];
            } else {
                temp[k++] = indexes[j++];
            }
        }
        while (i <= mid) {
            ans[indexes[i]] += right - mid;
            temp[k++] = indexes[i++];
        }
        while (j <= right) {
            temp[k++] = indexes[j++];
        }
        k = 0;
        while (left <= right) {
            indexes[left++] = temp[k++];
        }
    }

    /**
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * <p>
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * <p>
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 示例:
     * <p>
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            //0-持有，1-不持有且冷冻，2-不持有且没有冷冻
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

    /**
     * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：head = [1,3,2]
     * 输出：[2,3,1]
     *  
     * <p>
     * 限制：
     * <p>
     * 0 <= 链表长度 <= 10000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        LinkedList<ListNode> deque = new LinkedList<>();
        while (head != null) {
            deque.push(head);
            head = head.next;
        }
        int[] ans = new int[deque.size()];
        int i = 0;
        while (!deque.isEmpty()) {
            ans[i++] = deque.pop().val;
        }
        return ans;
    }

    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int live = 0;
                for (int[] d : new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}) {
                    if (gameOfLifeCheck(d[0] + i, d[1] + j, board) && (board[d[0] + i][d[1] + j] & 1) == 1) {
                        live++;
                    }
                }
                if ((board[i][j] & 1) == 1) {
                    if (!(live < 2 || live > 3)) {
                        board[i][j] = 3;
                    }
                } else if (live == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private boolean gameOfLifeCheck(int i, int j, int[][] board) {
        return i >= 0 && j >= 0 && i < board.length && j < board[i].length;
    }

    public void sortColors(int[] nums) {
        int i = 0, j = 0, k = nums.length - 1;
        while (i <= k) {
            if (nums[i] == 2) {
                sortColorsSwap(nums, i, k--);
            } else if (nums[i] == 0) {
                sortColorsSwap(nums, i++, j++);
            } else {
                i++;
            }
        }
    }

    private void sortColorsSwap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        int j = i;
        while (j < nums.length && i - 1 >= 0 && nums[j] > nums[i - 1]) {
            j++;
        }
        if (i - 1 >= 0 && j - 1 >= 0) {
            int temp = nums[i - 1];
            nums[i - 1] = nums[j - 1];
            nums[j - 1] = temp;
        }
        Arrays.sort(nums, i, nums.length);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<Integer> deque = new LinkedList<>();
        while (l1 != null) {
            deque.push(l1.val);
            l1 = l1.next;
        }
        LinkedList<Integer> deque2 = new LinkedList<>();
        while (l2 != null) {
            deque2.push(l2.val);
            l2 = l2.next;
        }
        ListNode ans = null;
        int carry = 0;
        while (!deque.isEmpty() || !deque2.isEmpty() || carry != 0) {
            int sum = (deque.isEmpty() ? 0 : deque.pop()) + (deque2.isEmpty() ? 0 : deque2.pop()) + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = ans;
            ans = node;
        }
        return ans;
    }

    public int fib(int n) {
        int i = 0, j = 1, sum = 0;
        for (int k = 2; k <= n; k++) {
            sum = (i + j) % 1000000007;
            i = j;
            j = sum;
        }
        return n < 2 ? n : sum;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        return list.stream().mapToInt(a -> a).toArray();
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int size = deque.size(); size > 0; size--) {
                TreeNode pop = deque.pop();
                temp.add(pop.val);
                if (pop.left != null) {
                    deque.add(pop.left);
                }
                if (pop.right != null) {
                    deque.add(pop.right);
                }
            }
            ans.add(temp);
        }
        Collections.reverse(ans);
        return ans;
    }

    /**
     * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
     * <p>
     *  
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     *  
     * <p>
     * 限制：
     * <p>
     * 0 <= 节点个数 <= 5000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    /**
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     * <p>
     * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
     * <p>
     *  
     * <p>
     * 例如，给定三角形：
     * <p>
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return minimumTotalDfs(triangle, 0, 0, new Integer[triangle.size()][triangle.get(triangle.size() - 1).size()]);
    }

    public int minimumTotalII(List<List<Integer>> triangle) {
        int m = triangle.size(), n = triangle.get(m - 1).size();
        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                dp[i][j] = (i + 1 >= m ? 0 : Math.min(dp[i + 1][j], dp[i + 1][j + 1])) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    private int minimumTotalDfs(List<List<Integer>> triangle, int level, int c, Integer[][] memo) {
        if (level == triangle.size()) {
            return 0;
        }
        if (memo[level][c] != null) {
            return memo[level][c];
        }
        int i = minimumTotalDfs(triangle, level + 1, c, memo);
        int j = minimumTotalDfs(triangle, level + 1, c + 1, memo);
        return memo[level][c] = Math.min(i, j) + triangle.get(level).get(c);
    }

    public void rotate(int[] nums, int k) {
        int count = nums.length;
        k = k % nums.length;
        for (int i = 0; i < nums.length && count > 0; i++) {
            int index = i, temp = nums[index];
            do {
                int next = (index - k + nums.length) % nums.length;
                nums[index] = next == i ? temp : nums[next];
                index = next;
                count--;
            } while (index != i);
        }
    }

    public boolean isAnagram(String s, String t) {
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[right] % 2 == 0) {
                right--;
            } else if (nums[left] % 2 == 1) {
                left++;
            } else {
                int temp = nums[left];
                nums[left++] = nums[right];
                nums[right--] = temp;
            }
        }
        return nums;
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isBalanced(root.left) || !isBalanced(root.right)) {
            return false;
        }
        return Math.abs(isBalancedHeight(root.left) - isBalancedHeight(root.right)) <= 1;
    }

    private int isBalancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(isBalancedHeight(root.left), isBalancedHeight(root.right)) + 1;
    }

    public boolean CheckPermutation(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * <p>
     *  
     * <p>
     * 例如，给出
     * <p>
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *  
     * <p>
     * 限制：
     * <p>
     * 0 <= 节点个数 <= 5000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int i, int j, int[] inorder, int m, int n) {
        if (i > j) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[i]);
        for (int k = m; k <= n; k++) {
            if (preorder[i] == inorder[k]) {
                root.left = buildTreeHelper(preorder, i + 1, i + k - m, inorder, m, k - 1);
                root.right = buildTreeHelper(preorder, i + 1 + k - m, j, inorder, k + 1, n);
            }
        }
        return root;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode start = new ListNode(0), cur = head, pre = start;
        start.next = cur;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                return start.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return start.next;
    }

    public int numJewelsInStones(String J, String S) {
        int[] arr = new int[128];
        for (int i = 0; i < S.length(); i++) {
            arr[S.charAt(i)]++;
        }
        int ans = 0;
        for (int i = 0; i < J.length(); i++) {
            ans += arr[J.charAt(i)];
        }
        return ans;
    }

    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        } else if (root.left == null && root.right == null && root.val == sum) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            ans.add(temp);
            return ans;
        }
        List<List<Integer>> L = pathSum(root.left, sum - root.val);
        List<List<Integer>> R = pathSum(root.right, sum - root.val);
        for (List<Integer> integers : L) {
            integers.add(0, root.val);
        }
        for (List<Integer> integers : R) {
            integers.add(0, root.val);
        }
        ans.addAll(L);
        ans.addAll(R);
        return ans;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);
        return root;
    }

    public int numWays(int n) {
        if (n < 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    public int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(Math.max(dp[i - j] * j, (i - j) * j), dp[i]);
            }
        }
        return dp[n];
    }

    public int[] printNumbers(int n) {
        int pow = (int) Math.pow(10, n);
        int[] ans = new int[pow - 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[][] r = new boolean[10][9], c = new boolean[10][9], b = new boolean[10][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int number = board[i][j] - '0';
                if (r[number][i] || c[number][j] || b[number][i / 3 + j / 3 * 3]) {
                    return false;
                }
                r[number][i] = true;
                c[number][j] = true;
                b[number][i / 3 + j / 3 * 3] = true;
            }
        }
        return true;
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        if (rowIndex == 0) {
            return ans;
        }
        List<Integer> row = getRow(rowIndex - 1);
        for (int i = 1; i < row.size(); i++) {
            ans.add(row.get(i) + row.get(i - 1));
        }
        ans.add(1);
        return ans;
    }

    public int hammingDistance(int x, int y) {
        int ans = 0;
        int z = x ^ y;
        for (int i = 0; i < 32; i++) {
            ans += (z & 1) == 1 ? 1 : 0;
            z >>= 1;
        }
        return ans;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int missingNumberII(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i] ^ i;
        }
        return ans ^ nums.length;
    }

    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

    public int minArray(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i - 1] > numbers[i]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int num : nums) {
                if (num < nums[i]) {
                    temp++;
                }
            }
            indexes[i] = temp;
        }
        return indexes;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            if (ints.length > 0 && ints[ints.length - 1] < target) {
                continue;
            }
            for (int anInt : ints) {
                if (anInt == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n & 1;
            n >>>= 1;
        }
        return ans;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, ans = 0;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                int i = mid, j = mid;
                while (i >= 0 && nums[i] == nums[mid]) {
                    i--;
                }
                while (j < nums.length && nums[j] == nums[mid]) {
                    j++;
                }
                return j - i - 1;
            }
        }
        return ans;
    }

    public int game(int[] guess, int[] answer) {
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans += guess[i] == answer[i] ? 1 : 0;
        }
        return ans;
    }

    public int numberOfSteps(int num) {
        int ans = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
            ans++;
        }
        return ans;
    }

    public int subtractProductAndSum(int n) {
        int mul = 1, sum = 0;
        while (n > 0) {
            sum += n % 10;
            mul *= n % 10;
            n /= 10;
        }
        return mul - sum;
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        }
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        t1.val += t2.val;
        return t1;
    }

    public int kthToLast(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.val;
    }

    public int firstUniqChar(String s) {
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (letters[s.charAt(i) - 'a'] == 0) {
                return i;
            }
        }
        return -1;
    }

    public int[][] updateMatrix(int[][] matrix) {
        boolean[][] visit = new boolean[matrix.length][matrix[0].length];
        LinkedList<int[]> deque = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    deque.add(new int[]{i, j});
                    visit[i][j] = true;
                }
            }
        }
        int depth = 0;
        while (!deque.isEmpty()) {
            for (int size = deque.size(); size > 0; size--) {
                int[] p = deque.poll();
                for (int[] d : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                    if (p[0] + d[0] >= matrix.length || p[0] + d[0] < 0 || p[1] + d[1] < 0 || p[1] + d[1] >= matrix[0].length || visit[p[0] + d[0]][p[1] + d[1]]) {
                        continue;
                    }
                    matrix[p[0] + d[0]][p[1] + d[1]] += depth;
                    deque.add(new int[]{p[0] + d[0], p[1] + d[1]});
                    visit[p[0] + d[0]][p[1] + d[1]] = true;
                }
            }
            depth++;
        }
        return matrix;
    }

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        } else if (root.left == null && root.right == null) {
            ans.add(String.valueOf(root.val));
            return ans;
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        for (String s : left) {
            ans.add(root.val + "->" + s);
        }
        for (String s : right) {
            ans.add(root.val + "->" + s);
        }
        return ans;
    }

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode partition = partition(head.next, x);
        ListNode start = new ListNode(0), pre = start;
        start.next = partition;
        while (partition != null && partition.val < x && head.val >= x) {
            pre = partition;
            partition = partition.next;
        }
        head.next = partition;
        pre.next = head;
        return start.next;
    }

    public int kthLargest(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        kthLargestDfs(root, k, queue);
        return queue.peek();
    }

    private void kthLargestDfs(TreeNode root, int k, PriorityQueue<Integer> queue) {
        if (root == null) {
            return;
        }
        if (queue.size() < k) {
            queue.add(root.val);
        } else if (root.val > queue.peek()) {
            queue.poll();
            queue.add(root.val);
        }
        kthLargestDfs(root.left, k, queue);
        kthLargestDfs(root.right, k, queue);
    }

    public String reverseVowels(String s) {
        List<Character> list = Arrays.asList('a', 'e', 'o', 'u', 'i', 'A', 'E', 'O', 'U', 'I');
        int left = 0, right = s.length() - 1;
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            if (!list.contains(s.charAt(left))) {
                left++;
            } else if (!list.contains(s.charAt(right))) {
                right--;
            } else {
                char temp = sb.charAt(left);
                sb.setCharAt(left++, sb.charAt(right));
                sb.setCharAt(right--, temp);
            }
        }
        return sb.toString();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        ListNode node = mergeTwoLists(l1.next, l2);
        ListNode start = new ListNode(0), pre = start;
        start.next = node;
        while (node != null && l1.val > node.val) {
            pre = node;
            node = node.next;
        }
        l1.next = node;
        pre.next = l1;
        return start.next;
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeavesDfs(root, false);
    }

    private int sumOfLeftLeavesDfs(TreeNode root, boolean left) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null && left) {
            return root.val;
        }
        return sumOfLeftLeavesDfs(root.left, true) + sumOfLeftLeavesDfs(root.right, false);
    }

    public char firstUniqCharII(String s) {
        int[] letters = new int[128];
        for (char c : s.toCharArray()) {
            letters[c]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (letters[s.charAt(i)] == 1) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            int temp = 0;
            while (num > 0) {
                num /= 10;
                temp++;
            }
            ans += (temp & 1) == 0 ? 1 : 0;
        }
        return ans;
    }

    public void solve(char[][] board) {
        LinkedList<int[]> deque = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) && board[i][j] == 'O') {
                    board[i][j] = 'o';
                    deque.add(new int[]{i, j});
                }
            }
        }
        while (!deque.isEmpty()) {
            int[] p = deque.pop();
            for (int[] d : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                int i = d[0] + p[0];
                int j = d[1] + p[1];
                if (i >= 0 && j >= 0 && i < board.length && j < board[0].length && board[i][j] == 'O') {
                    board[i][j] = 'o';
                    deque.add(new int[]{i, j});
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                    deque.add(new int[]{i, j});
                } else if (board[i][j] == 'o') {
                    board[i][j] = 'O';
                    deque.add(new int[]{i, j});
                }
            }
        }
    }

    public boolean canPermutePalindrome(String s) {
        int[] letters = new int[128];
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        for (char c : chars) {
            letters[c]++;
        }
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && chars[i] == chars[i - 1]) {
                continue;
            }
            ans += (letters[s.charAt(i)] & 1) == 1 ? 1 : 0;
        }
        return ans <= 1;
    }

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid - 1;
            }
        }
        return nums[left];
    }

    public int majorityElement(int[] nums) {
        int ans = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                ans = num;
            }
            count += ans == num ? 1 : -1;
        }
        return ans;
    }

    public int balancedStringSplit(String s) {
        int ans = 0;
        int[] counts = new int[26];
        for (int k = 0; k < s.length(); k++) {
            counts[s.charAt(k) - 'A']++;
            if (counts['L' - 'A'] == counts['R' - 'A']) {
                ans++;
            }
        }
        return ans;
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetricHelper(root, root);
    }

    private boolean isSymmetricHelper(TreeNode q, TreeNode p) {
        if (q == null) {
            return p == null;
        } else if (p == null) {
            return false;
        }
        return p.val == q.val && isSymmetricHelper(q.left, p.right) && isSymmetricHelper(q.right, p.left);
    }

    public int[] sortArrayByParity(int[] A) {
        int left = 0, right = A.length - 1;
        while (left < right) {
            if ((A[right] & 1) == 1) {
                right--;
            } else if ((A[left] & 1) == 0) {
                left++;
            } else {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;
            }
        }
        return A;
    }

    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans = (ans << 1) | head.val;
            head = head.next;
        }
        return ans;
    }

    public boolean detectCapitalUse(String word) {
        boolean lowerCase = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 'a' && word.charAt(i) <= 'z') {
                lowerCase = true;
            }
        }
        for (int i = word.length() - 1; i >= 0; i--) {
            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z' && i != 0 && lowerCase) {
                return false;
            }
        }
        return true;
    }

    public int titleToNumber(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans *= i == 0 ? 1 : 26;
            ans += s.charAt(i) - 'A' + 1;
        }
        return ans;
    }

    public boolean isStraight(int[] nums) {
        int min = 14, max = -1;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (set.contains(num)) {
                return false;
            }
            set.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return max - min < 5;
    }
}
