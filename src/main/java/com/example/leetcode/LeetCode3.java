package com.example.leetcode;

import java.util.*;

public class LeetCode3 {
    /**
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * <p>
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * <p>
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * @param nums
     * @return
     */
    public int[] dailyTemperatures(int[] nums) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peek()] < nums[i]) {
                ans[deque.peek()] = i - deque.peek();
                deque.pop();
            }
            deque.push(i);
        }
        return ans;
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * <p>
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> deque = new LinkedList<>();
        int[] h = Arrays.copyOf(heights, heights.length + 1);
        int ans = 0;
        for (int i = 0; i < h.length; i++) {
            while (!deque.isEmpty() && h[i] < h[deque.peek()]) {
                int height = heights[deque.pop()];
                while (!deque.isEmpty() && heights[deque.peek()] == height) {
                    deque.pop();
                }
                int width = deque.isEmpty() ? i : i - deque.peek() - 1;
                ans = Math.max(ans, width * height);
            }
            deque.push(i);
        }
        return ans;
    }

    /**
     * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * <p>
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * 解释:
     * 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
     * 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
     * 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
     * 示例 2:
     * <p>
     * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
     * 输出: [3,-1]
     * 解释:
     *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
     * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
     *  
     * <p>
     * 提示：
     * <p>
     * nums1和nums2中所有元素是唯一的。
     * nums1和nums2 的数组大小都不超过1000。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-i
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for (int i = 0; i < nums1.length; i++) {
            Integer index = map.get(nums1[i]);
            while (index < nums2.length && nums1[i] >= nums2[index]) {
                index++;
            }
            ans[i] = index == nums2.length ? -1 : nums2[index];
        }
        return ans;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new LinkedList<>();
        for (int num : nums2) {
            while (!deque.isEmpty() && num > deque.peek()) {
                map.put(deque.pop(), num);
            }
            deque.push(num);
        }
        while (!deque.isEmpty()) {
            map.put(deque.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * <p>
     * <p>
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        LinkedList<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                Integer pop = stack.pop();
                while (!stack.isEmpty() && stack.peek().equals(pop)) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    ans += (Math.min(height[stack.peek()], height[i]) - height[pop]) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
     * <p>
     * 注意:
     * <p>
     * num 的长度小于 10002 且 ≥ k。
     * num 不会包含任何前导零。
     * 示例 1 :
     * <p>
     * 输入: num = "1432219", k = 3
     * 输出: "1219"
     * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
     * 示例 2 :
     * <p>
     * 输入: num = "10200", k = 1
     * 输出: "200"
     * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
     * 示例 3 :
     * <p>
     * 输入: num = "10", k = 2
     * 输出: "0"
     * 解释: 从原数字移除所有的数字，剩余为空就是0。
     * https://leetcode-cn.com/problems/remove-k-digits/
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : num.toCharArray()) {
            while (!stack.isEmpty() && c < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0') {
            i++;
        }
        return i < sb.length() ? sb.substring(i) : "0";
    }

    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * <p>
     * 你找到的子数组应是最短的，请输出它的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     * <p>
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int left = 0, right = nums.length - 1;
        while (left < nums.length && nums[left] == copy[left]) {
            left++;
        }
        while (right >= 0 && nums[right] == copy[right]) {
            right--;
        }
        return Math.max(right - left + 1, 0);
    }

    /**
     * 给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。
     * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: "bcabc"
     * 输出: "abc"
     * 示例 2:
     * <p>
     * 输入: "cbacdcbc"
     * 输出: "acdb"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] letter = new int[128];
        for (char c : s.toCharArray()) {
            letter[c]++;
        }
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (stack.contains(c)) {
                letter[c]--;
                continue;
            }
            while (!stack.isEmpty() && c < stack.peek() && letter[stack.peek()] > 1) {
                letter[stack.peek()]--;
                stack.pop();
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int x = 0; x < nums.length - 2; x++) {
            if (x > 0 && nums[x] == nums[x - 1]) {
                continue;
            }
            int y = x + 1, z = nums.length - 1;
            while (y < z) {
                int sum = nums[x] + nums[y] + nums[z];
                if (sum > 0) {
                    z--;
                } else if (sum < 0) {
                    y++;
                } else {
                    ans.add(Arrays.asList(nums[x], nums[y], nums[z]));
                    z--;
                    while (y < z && nums[z + 1] == nums[z]) {
                        z--;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
     * <p>
     * 每次转换只能改变一个字母。
     * 转换后得到的单词必须是字典中的单词。
     * 说明:
     * <p>
     * 如果不存在这样的转换序列，返回一个空列表。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     * <p>
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * <p>
     * 输出:
     * [
     * ["hit","hot","dot","dog","cog"],
     *   ["hit","hot","lot","log","cog"]
     * ]
     * 示例 2:
     * <p>
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * <p>
     * 输出: []
     * <p>
     * 解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-ladder-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Deque<String> deque = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        HashMap<String, Set<String>> map = new HashMap<>();
        HashSet<String> wordSet = new HashSet<>(wordList);
        deque.add(beginWord);
        set.add(beginWord);
        boolean find = false;
        while (!deque.isEmpty() && !find) {
            HashSet<String> setTemp = new HashSet<>();
            for (int i = deque.size(); i > 0; i--) {
                String poll = deque.poll();
                for (int j = 0; j < poll.length(); j++) {
                    for (char k = 'a'; k <= 'z'; k++) {
                        char[] chars = poll.toCharArray();
                        chars[j] = k;
                        String word = new String(chars);
                        if (wordSet.contains(word) && !set.contains(word)) {
                            if (!endWord.equals(word)) {
                                setTemp.add(word);
                            } else {
                                find = true;
                            }
                            Set<String> stringSet = map.getOrDefault(poll, new HashSet<>());
                            stringSet.add(word);
                            map.put(poll, stringSet);
                            deque.add(word);
                        }
                    }
                }
            }
            set.addAll(setTemp);
        }
        List<List<String>> ans = new ArrayList<>();
        findLaddersDfs(ans, new ArrayList<>(), map, beginWord, endWord);
        return ans;
    }

    private void findLaddersDfs(List<List<String>> ans, List<String> path, HashMap<String, Set<String>> map, String key, String target) {
        if (key == null) {
            return;
        }
        path.add(key);
        if (key.equals(target)) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (map.get(key) == null) {
            return;
        }
        for (String s : map.get(key)) {
            findLaddersDfs(ans, new ArrayList<>(path), map, s, target);
        }
    }

    /**
     * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
     * <p>
     * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
     * <p>
     * 返回一对观光景点能取得的最高分。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：[8,1,5,2,6]
     * 输出：11
     * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
     *  
     * <p>
     * 提示：
     * <p>
     * 2 <= A.length <= 50000
     * 1 <= A[i] <= 1000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0, mx = A[0];
        for (int j = 1; j < A.length; j++) {
            ans = Math.max(ans, mx + A[j] - j);
            mx = Math.max(mx, j + A[j]);
        }
        return ans;
    }

    public int calPoints(String[] ops) {
        LinkedList<String> stack = new LinkedList<>();
        stack.add("0");
        int ans = 0;
        for (String s : ops) {
            if (!s.equals("C") && !s.equals("D") && !s.equals("+")) {
                ans += Integer.parseInt(s);
                stack.push(s);
            } else if (s.equals("C")) {
                ans -= Integer.parseInt(stack.pop());
            } else if (s.equals("+")) {
                String s1 = stack.pop();
                String s2 = stack.pop();
                int sum = Integer.parseInt(s1) + Integer.parseInt(s2);
                ans += sum;
                stack.push(s2);
                stack.push(s1);
                stack.push(String.valueOf(sum));
            } else {
                int i = Integer.parseInt(stack.peek()) * 2;
                ans += i;
                stack.push(String.valueOf(i));
            }
        }
        return ans;
    }

    /**
     * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,0]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: [3,4,-1,1]
     * 输出: 2
     * 示例 3:
     * <p>
     * 输入: [7,8,9,11,12]
     * 输出: 1
     * <p>
     * <p>
     * 提示：
     * <p>
     * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                firstMissingPositiveSwap(nums, i, nums[i] - 1);
            }
        }
        int index = 0;
        while (index < nums.length && nums[index] == index + 1) {
            index++;
        }
        return index + 1;
    }

    private void firstMissingPositiveSwap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     * <p>
     * 示例1:
     * <p>
     * 输入：[1, 2, 3, 3, 2, 1]
     * 输出：[1, 2, 3]
     * 示例2:
     * <p>
     * 输入：[1, 1, 1, 1, 2]
     * 输出：[1, 2]
     * 提示：
     * <p>
     * 链表长度在[0, 20000]范围内。
     * 链表元素在[0, 20000]范围内。
     * 进阶：
     * <p>
     * 如果不得使用临时缓冲区，该怎么解决？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode target = head;
        while (target != null) {
            ListNode cur = target.next, pre = target;
            while (cur != null) {
                if (target.val == cur.val) {
                    pre.next = cur.next;
                } else {
                    pre = cur;
                }
                cur = cur.next;
            }
            target = target.next;
        }
        return head;
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
        if (s == null || s.length() ==0) {
            return 0;
        }
        int[] dp = new int[s.length()];
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                dp[i] = 0;
            } else if (s.charAt(i - 1) == '(') {
                dp[i] = (i - 2 < 0 ? 0 : dp[i - 2] )+ 2;
            } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
