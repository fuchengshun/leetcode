package com.example.leetcode;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LeetCode {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode peek = stack.pop();
                list.add(peek.val);
                cur = peek.right;
            }
        }
        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode peek = stack.pop();
                cur = peek.right;
            }
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        TreeNode cur = root, lastVisit = null;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.peek();
                if (cur.right == null || cur.right == lastVisit) {
                    stack.pop();
                    list.add(cur.val);
                    lastVisit = cur;
                    cur = null;
                } else {
                    cur = cur.right;
                }
            }
        }
        return list;
    }

    List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
// 选择列表：nums 中不存在于 track 的那些元素
// 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> temp = initList(n);
        backtrack2(temp, 0);
        return result;
    }

    private void backtrack2(List<String> board, int row) {
        if (row == board.size()) {
            result.add(new ArrayList<>(board));
            return;
        }
        for (int col = 0; col < board.get(row).length(); col++) {
            if (!isValid(board, row, col))
                continue;
            char[] chars = board.get(row).toCharArray();
            chars[col] = 'Q';
            board.set(row, new String(chars));
            backtrack2(board, row + 1);
            char[] chars2 = board.get(row).toCharArray();
            chars2[col] = '.';
            board.set(row, new String(chars2));
        }
    }

    private boolean isValid(List<String> board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col) == 'Q')
                return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.get(row).length(); i--, j++) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }

    private List<String> initList(int n) {
        StringBuilder s = new StringBuilder();
        List<String> r = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            s.append('.');
        }
        for (int i = 0; i < n; i++) {
            r.add(new String(s));
        }
        return r;
    }

    public void solveSudoku(char[][] board) {
        backtrack3(board, 0, 0);
    }

    private void backtrack3(char[][] board, int row, int col) {
        if (row == 9) {
            System.out.println(8888);
            return;
        }
        if (board[row][col] != '.') {
            if (col + 1 < board[row].length)
                backtrack3(board, row, col + 1);
            else
                backtrack3(board, row + 1, 0);
        }

        for (char i = '1'; i <= '9'; i++) {
            if (!isValid2(board, row, col, i))
                continue;
            board[row][col] = i;
            if (col + 1 < board[row].length)
                backtrack3(board, row, col + 1);
            else
                backtrack3(board, row + 1, 0);
            board[row][col] = '.';
        }
    }

    private boolean isValid2(char[][] board, int row, int col, char c) {
        for (int j = 0; j < 9; j++) {
            if (board[row][j] == c)
                return false;
        }
        for (int j = 0; j < row; j++) {
            if (board[j][col] == c)
                return false;
        }
        int left = row < 3 ? row : row < 6 ? 3 : 6, right = left + 3, up = col < 3 ? col : col < 6 ? 3 : 6, down = up + 3;
        for (int i = left; i < right; i++) {
            for (int j = up; j < down; j++) {
                if (board[j][i] == c)
                    return false;
            }
        }
        return true;
    }

    public int fib(int n) {
        int[] table = new int[n];
        return f(table, n);
    }

    public int f(int[] table, int n) {
        if (n < 2) {
            return n;
        }
        if (table[n - 1] == 0) {
            table[n - 1] = f(table, n - 1) + f(table, n - 2);
        }
        return table[n - 1];
    }

    public int coinChange2(int[] dp, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (dp[amount] != 0) {
            return dp[amount];
        }
        double maxValue = Double.MAX_VALUE;
        for (int coin : coins) {
            int temp = coinChange2(dp, coins, amount - coin);
            if (temp < maxValue && temp != -1) {
                maxValue = temp;
            }
        }
        dp[amount] = maxValue == Double.MAX_VALUE ? -1 : (int) maxValue + 1;
        return dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        return coinChange2(dp, coins, amount);
//        if (amount == 0)
//            return 0;
//        help(coins, new ArrayList<>(), amount);
//        return c.size() == 0 ? -1 : c.size();
    }

    List<Integer> c = new ArrayList<>();

    public void help(int[] coins, List<Integer> choose, int amount) {
        if (sum(choose) >= amount) {
            if (sum(choose) == amount && (c.size() == 0 || c.size() > choose.size())) {
                c = new ArrayList<>(choose);
            }
            return;
        }
        for (int coin : coins) {
//            if (!isValid3(choose, amount, coin))
//                continue;
            choose.add(coin);
            help(coins, choose, amount);
            choose.remove(choose.size() - 1);
        }
    }

//    private boolean isValid3(List<Integer> choose, int amount, int coin) {
//        sum(choose)
//    }

    private long sum(List<Integer> choose) {
        long total = 0;
        for (Integer integer : choose) {
            total += integer;
        }
        return total;
    }

    /**
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     */
    Map<String[], Boolean> dp = new HashMap<>();

    public boolean isMatch(String text, String pattern) {
        return isMatch2(dp, text, pattern);
//        return isMatch2(text, pattern);
    }

    public boolean isMatch2(Map<String[], Boolean> dp, String text, String pattern) {
//    public boolean isMatch2(String text, String pattern) {
        String[] strings = {text, pattern};
        if (dp.get(strings) != null) {
            return dp.get(strings);
        }
        if (pattern.isEmpty()) {
            return text.isEmpty();
        }
        boolean firstMatch = !text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');
        boolean r;
        if (pattern.length() > 1 && pattern.charAt(1) == '*') {
            r = isMatch2(dp, text, pattern.substring(2)) || firstMatch && isMatch2(dp, text.substring(1), pattern);
//            r = isMatch2(text, pattern.substring(2)) || firstMatch && isMatch2(text.substring(1), pattern);
        } else {
            r = firstMatch && isMatch2(dp, text.substring(1), pattern.substring(1));
//            r = firstMatch && isMatch2(text.substring(1), pattern.substring(1));
        }
        dp.put(strings, r);
        return r;
    }

    public int minDistance(String word1, String word2) {
        Integer[][] dp = new Integer[word1.length()][word2.length()];
        return minDistance2(dp, word1, word2, word1.length() - 1, word2.length() - 1);
    }

    public int minDistance2(Integer[][] dp, String word1, String word2, int i, int j) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            dp[i][j] = minDistance2(dp, word1, word2, i - 1, j - 1);
        } else {
            //删除
            int i1 = minDistance2(dp, word1, word2, i - 1, j) + 1;
            //插入
            int i2 = minDistance2(dp, word1, word2, i, j - 1) + 1;
            int i3 = minDistance2(dp, word1, word2, i - 1, j - 1) + 1;
            dp[i][j] = Math.min(i1, Math.min(i2, i3));
        }
        return dp[i][j];
    }

    public int minDistance222(String word1, String word2) {
        Integer[][] dp = new Integer[word1.length()][word2.length()];
        return minDistance22(dp, word1, word2, word1.length() - 1, word2.length() - 1);
    }

    private int minDistance22(Integer[][] dp, String word1, String word2, int i, int j) {
        if (i < 0) {
            return j + 1;
        }
        if (j < 0) {
            return i + 1;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            dp[i][j] = minDistance22(dp, word1, word2, i - 1, j - 1);
        } else {
            int i1 = minDistance22(dp, word1, word2, i - 1, j) + 1;
            int i2 = minDistance22(dp, word1, word2, i, j - 1) + 1;
            dp[i][j] = Math.min(i1, i2);
        }
        return dp[i][j];
    }

    public int lengthOfLIS(int[] nums) {
        Integer[] dp = new Integer[nums.length];
        lengthOfLIS2(dp, nums, nums.length - 1);
        int max = 0;
        for (Integer integer : dp) {
            max = Math.max(max, integer == null ? 0 : integer);
        }
        return max;
    }

    private int lengthOfLIS2(Integer[] dp, int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            dp[i] = 1;
            return 1;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int max = 1;
        for (int j = 0; j < i; j++) {
            int temp = lengthOfLIS2(dp, nums, j);
            if (nums[i] > nums[j]) {
                max = Math.max(max, temp + 1);
            }
        }
        dp[i] = max;
        return dp[i];
    }

    public int lengthOfLIS3(int[] nums) {
        int[] dp = new int[nums.length];
        // dp 数组全都初始化为 1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int value : dp) {
            res = Math.max(res, value);
        }
        return res;
    }

    /**
     * 输入: [1,2,3,1]
     * 输出: 4
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        return rob2(new Integer[nums.length], nums, 0);
    }

    private int rob2(Integer[] dp, int[] nums, int i) {
        if (i >= nums.length)
            return 0;
        if (dp[i] != null)
            return dp[i];
        dp[i] = Math.max(nums[i] + rob2(dp, nums, i + 2), rob2(dp, nums, i + 1));
        return dp[i];
    }

    /**
     * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
     * 输入: 3
     * 输出: 3
     *
     * @param n
     * @return
     */
//    public int minSteps(int n) {
//        int[] dp = new int[n+1];
//        for (int i = 0; i < n+1; i++) {
//
//        }
//    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
                continue;
            }
            if (stack.empty())
                return false;
            Character peek = stack.peek();
            if (map.containsKey(peek)) {
                if (s.charAt(i) != map.get(peek)) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m-- + n-- - 1;
        while (n >= 0 || m >= 0) {
            if (m < 0) {
                nums1[p--] = nums2[n--];
                continue;
            }
            if (n < 0) {
                nums1[p--] = nums1[m--];
                continue;
            }
            if (nums1[m] > nums2[n]) {
                nums1[p--] = nums1[m--];
            } else {
                nums1[p--] = nums2[n--];
            }
        }
    }

    /**
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        Integer[] dp = new Integer[nums.length];
        dp[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i] + nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int maxSubArray2(int[] nums, int i, Integer[] dp) {
        if (i == 0) {
            dp[0] = nums[0];
            return nums[0];
        }
        if (dp[i] != null) {
            return dp[i];
        }
        dp[i] = Math.max(nums[i], nums[i] + maxSubArray2(nums, i - 1, dp));
        return dp[i];
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int buy = prices[0];
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i - 1 >= 0) {
                buy = Math.min(buy, prices[i - 1]);
            }
            maxProfit = Math.max(maxProfit, prices[i] - buy);
        }
        return maxProfit;
    }

    public int maxProfit2(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i - 1 >= 0 && prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        s = s.toLowerCase();
        while (l < r) {
            while (isNotNumberOrLetter(s.charAt(l)) && l < r) {
                l++;
            }
            while (isNotNumberOrLetter(s.charAt(r)) && l < r) {
                r--;
            }
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean isNotNumberOrLetter(char c) {
        return ((c < '0' || c > '9') && (c < 'a' || c > 'z'));
    }

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] > target) {
                r--;
            }
            if (numbers[l] + numbers[r] < target) {
                l++;
            }
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            }
        }
        return new int[]{-1, -1};
    }

    public int majorityElement(int[] nums) {
        int candidate = nums[0], count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            int temp = n;
            while (temp % 5 == 0 && temp != 0) {
                count++;
                temp /= 5;
            }
            n--;
        }
        return count;
    }

    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans <<= 1;
            ans |= n & 1;
            n >>= 1;
        }
        return ans;
    }

    public int hammingWeight(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) {
                ans++;
            }
            n >>= 1;
        }
        return ans;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode pre = sentinel, cru = head;
        while (cru != null) {
            if (cru.val == val) {
                pre.next = cru.next;
            } else {
                pre = cru;
            }
            cru = cru.next;
        }
        return sentinel.next;
    }

    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode sentinel = new ListNode(0);
        ListNode ans = sentinel;
        while (!stack.empty()) {
            sentinel.next = stack.pop();
            sentinel = sentinel.next;
        }
        sentinel.next = null;
        return ans.next;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null && Math.abs(map.get(nums[i]) - i) <= k) {
                return true;
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = root.left;
            root.left = invertTree(root.right);
            root.right = invertTree(temp);
        }
        return root;
    }

    public int subarraySum(int[] nums, int k) {
        int ans = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (Integer num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    /**
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * <p>
     * 找出路径和等于给定数值的路径总数。
     * <p>
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * <p>
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * <p>
     * 示例：
     * <p>
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     * <p>
     * 10
     * /  \
     * 5   -3
     * / \    \
     * 3   2   11
     * / \   \
     * 3  -2   1
     * <p>
     * 返回 3。和等于 8 的路径有:
     * <p>
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/path-sum-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param sum
     * @return
     */

    public int pathSum(TreeNode root, int sum) {
        return preSum(root, 0, sum);
    }

    Map<Integer, Integer> map = new HashMap<>();

    private int preSum(TreeNode root, int preSum, int sum) {
        int ans = 0;
        if (root == null)
            return ans;
        preSum += root.val;
        if (preSum == sum)
            ans++;
        ans += map.getOrDefault(preSum - sum, 0);
        map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        ans += preSum(root.left, preSum, sum) + preSum(root.right, preSum, sum);
        map.put(preSum, map.get(preSum) - 1);
        return ans;
    }

    /**
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     * <p>
     * 假定 BST 有如下定义：
     * <p>
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     * 例如：
     * 给定 BST [1,null,2,2],
     * <p>
     * 1
     * \
     * 2
     * /
     * 2
     * 返回[2].
     * <p>
     * 提示：如果众数超过1个，不需考虑输出顺序
     * <p>
     * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(map, root);
        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        Stack<Map.Entry<Integer, Integer>> stack = new Stack<>();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            if (stack.empty() || stack.peek().getValue() <= next.getValue())
                stack.push(next);
        }
        ArrayList<Integer> list = new ArrayList<>();
        Integer max = null;
        while (!stack.empty()) {
            if (max == null || max.equals(stack.peek().getValue())) {
                Map.Entry<Integer, Integer> pop = stack.pop();
                list.add(pop.getKey());
                max = pop.getValue();
            } else
                break;
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    private void dfs(Map<Integer, Integer> map, TreeNode root) {
        if (null == root)
            return;
        dfs(map, root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        dfs(map, root.right);
    }

    public int subarraySum2(int[] nums, int k) {
        int ans = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            sum += num;
            if (sum == k) {
                ans++;
            }
            ans += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2]
     * 示例 2:
     * <p>
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [9,4]
     * 说明:
     * <p>
     * 输出结果中的每个元素一定是唯一的。
     * 我们可以不考虑输出结果的顺序。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, true);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.get(i) != null) {
                list.add(i);
                map.put(i, null);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 16
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: 5
     * 输出: false
     * 进阶：
     * 你能不使用循环或者递归来完成本题吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/power-of-four
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {

    }

    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     * <p>
     * 输入: "  hello world!  "
     * 输出: "world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     * <p>
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] words = s.split(" +");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public int[] sortArray(int[] nums) {
//        int[] temp = new int[nums.length];
//        mergeSort(nums, 0, nums.length - 1, temp);
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        mergeArray(nums, left, mid, right, temp);
    }

    private void mergeArray(int[] nums, int left, int mid, int right, int[] temp) {
        int i = left, j = mid + 1, p = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[p++] = nums[i++];
            } else {
                temp[p++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[p++] = nums[i++];
        }
        while (j <= right) {
            temp[p++] = nums[j++];
        }
        p = 0;
        while (left <= right) {
            nums[left++] = temp[p++];
        }
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left >= right)
            return;
        int i = left, j = right, x = nums[right];
        while (i < j) {
            while (i < j && nums[j] > x)
                j--;
            if (i < j)
                nums[i++] = nums[j];
            while (i < j && nums[i] < x)
                i++;
            if (i < j)
                nums[j--] = nums[i];
        }
        nums[i] = x;
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    public boolean hasGroupsSizeX(int[] deck) {
        int[] ints = new int[10000];
        for (int i : deck) {
            ints[i]++;
        }
        int ans = 0;
        for (int anInt : ints) {
            if (anInt != 0) {
                ans = gcb(ans, anInt);
            }
        }
        return ans > 1;
    }

    public int gcb(int a, int b) {
        return a == 0 ? b : gcb(b % a, a);
    }

    /**
     * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     * <p>
     * 返回使 A 中的每个值都是唯一的最少操作次数。
     * <p>
     * 示例 1:
     * <p>
     * 输入：[1,2,2]
     * 输出：1
     * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
     * 示例 2:
     * <p>
     * 输入：[3,2,1,2,1,7]
     * 输出：6
     * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
     * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
     * 提示：
     * <p>
     * 0 <= A.length <= 40000
     * 0 <= A[i] < 40000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int ans = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= A[i]) {
                ans += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return ans;
    }

    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     * <p>
     *  
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int orangesRotting(int[][] grid) {
        Deque<Point> deque = new LinkedList<>();
        int fresh = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    deque.add(new Point(i, j));
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        int depth = 0;
        while (fresh > 0 && !deque.isEmpty()) {
            depth++;
            for (int i = deque.size(); i > 0; i--) {
                Point point = deque.poll();
                if (point.x + 1 < grid.length && grid[point.x + 1][point.y] == 1) {
                    grid[point.x + 1][point.y] = 2;
                    fresh--;
                    deque.add(new Point(point.x + 1, point.y));
                }
                if (point.x - 1 >= 0 && grid[point.x - 1][point.y] == 1) {
                    grid[point.x - 1][point.y] = 2;
                    fresh--;
                    deque.add(new Point(point.x - 1, point.y));
                }
                if (point.y + 1 < grid[point.x].length && grid[point.x][point.y + 1] == 1) {
                    grid[point.x][point.y + 1] = 2;
                    fresh--;
                    deque.add(new Point(point.x, point.y + 1));
                }
                if (point.y - 1 >= 0 && grid[point.x][point.y - 1] == 1) {
                    grid[point.x][point.y - 1] = 2;
                    fresh--;
                    deque.add(new Point(point.x, point.y - 1));
                }
            }
        }
        return fresh > 0 ? -1 : depth;
    }

    public int maxDepth(Node root) {
        Deque<Node> deque = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        int ans = 0;
        deque.add(root);
        while (!deque.isEmpty()) {
            ans++;
            for (int i = deque.size(); i > 0; i--) {
                Node node = deque.poll();
                if (node != null) {
                    List<Node> children = node.children;
                    for (Node child : children) {
                        if (child != null) {
                            deque.add(child);
                        }
                    }
                }
            }
        }
        return ans;
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode[]> deque = new LinkedList<>();
        deque.add(new TreeNode[]{null, root});
        while (!deque.isEmpty()) {
            int need = 0;
            TreeNode parent = null;
            for (int i = deque.size(); i > 0; i--) {
                TreeNode[] nodes = deque.poll();
                if (nodes[1].right != null) {
                    deque.add(new TreeNode[]{nodes[1], nodes[1].right});
                }
                if (nodes[1].left != null) {
                    deque.add(new TreeNode[]{nodes[1], nodes[1].left});
                }
                if (nodes[1].val == x && need == 0) {
                    need = y;
                    parent = nodes[0];
                }
                if (nodes[1].val == y && need == 0) {
                    need = x;
                    parent = nodes[0];
                }
                if (need != 0 && nodes[1].val == need && !nodes[0].equals(parent)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numRookCaptures(char[][] board) {
        int ans = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    for (int k = i + 1; k < board.length; k++) {
                        if (board[k][j] == 'p') {
                            ans++;
                            break;
                        } else if (board[k][j] == 'B') {
                            break;
                        }
                    }
                    for (int k = j + 1; k < board[i].length; k++) {
                        if (board[i][k] == 'p') {
                            ans++;
                            break;
                        } else if (board[i][k] == 'B') {
                            break;
                        }
                    }
                    for (int k = i - 1; k >= 0; k--) {
                        if (board[k][j] == 'p') {
                            ans++;
                            break;
                        } else if (board[k][j] == 'B') {
                            break;
                        }
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        if (board[i][k] == 'p') {
                            ans++;
                            break;
                        } else if (board[i][k] == 'B') {
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public boolean canThreePartsEqualSum(int[] A) {
        int[] memo = new int[A.length];
        int total = 0;
        for (int i = 0; i < A.length; i++) {
            total += A[i];
            memo[i] = total;
        }
        if (total % 3 != 0) {
            return false;
        }
        for (int i = 0; i < memo.length; i++) {
            for (int j = i + 1; memo[i] == total / 3 && j < memo.length - 1; j++) {
                if (total / 3 == memo[j] - memo[i] && total / 3 == memo[memo.length - 1] - memo[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public String gcdOfStrings(String str1, String str2) {
        if ("".equals(str2)) {
            return str1;
        }
        if ("".equals(str1)) {
            return str2;
        }
        if (str1.length() >= str2.length()) {
            String replace = str1.replace(str2, "");
            if (replace.equals(str1)) {
                return "";
            }
            return gcdOfStrings(str2, replace);
        }
        return gcdOfStrings(str2, str1);
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] ints = new int[num_people];
        int temp = 0;
        while (candies > 0) {
            for (int i = 0; i < ints.length && candies > 0; i++) {
                temp++;
                ints[i] += candies - temp >= 0 ? temp : candies;
                candies -= temp;
            }
        }
        return ints;
    }

    public int countCharacters(String[] words, String chars) {
        int[] memo = new int[26];
        for (char c : chars.toCharArray()) {
            memo[c - 'a'] += 1;
        }
        int ans = 0;
        for (String word : words) {
            int[] temp = Arrays.copyOf(memo, memo.length);
            for (int i = 0; i < word.length(); i++) {
                if (temp[word.charAt(i) - 'a'] <= 0) {
                    break;
                } else {
                    temp[word.charAt(i) - 'a']--;
                    if (i == word.length() - 1) {
                        ans += word.length();
                    }
                }
            }
        }
        return ans;
    }

    public int maxDistance(int[][] grid) {
        LinkedList<int[]> linkedList = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                //陆地
                if (grid[i][j] == 1) {
                    linkedList.add(new int[]{i, j});
                }
            }
        }
        if (linkedList.isEmpty() || linkedList.size() == grid.length * grid[0].length) {
            return -1;
        }
        int depth = 0;
        while (!linkedList.isEmpty()) {
            depth++;
            for (int i = linkedList.size(); i > 0; i--) {
                int[] poll = linkedList.poll();
                int r = poll[0];
                int c = poll[1];
                grid[r][c] += 2;
                if (c + 1 < grid[r].length && grid[r][c + 1] == 0) {
                    linkedList.add(new int[]{r, c + 1});
                    grid[r][c + 1] += 2;
                }
                if (c - 1 >= 0 && grid[r][c - 1] == 0) {
                    linkedList.add(new int[]{r, c - 1});
                    grid[r][c - 1] += 2;
                }
                if (r + 1 < grid.length && grid[r + 1][c] == 0) {
                    linkedList.add(new int[]{r + 1, c});
                    grid[r + 1][c] += 2;
                }
                if (r - 1 >= 0 && grid[r - 1][c] == 0) {
                    linkedList.add(new int[]{r - 1, c});
                    grid[r - 1][c] += 2;
                }
            }
        }
        return depth - 1;
    }

    public String compressString(String S) {
        if (S.length() == 0) {
            return S;
        }
        int slow = 0, fast = 1;
        StringBuilder ans = new StringBuilder();
        while (fast < S.length()) {
            if (S.charAt(slow) != S.charAt(fast)) {
                ans.append(S.charAt(slow)).append(fast - slow);
                slow = fast;
            }
            fast++;
        }
        ans.append(S.charAt(slow)).append(fast - slow);
        return ans.length() < S.length() ? ans.toString() : S;
    }

    public void merge3(int[] A, int m, int[] B, int n) {
        int i = m - 1, j = n - 1, p = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[p--] = A[i--];
            } else {
                A[p--] = B[j--];
            }
        }
        while (i >= 0) {
            A[p--] = A[i--];
        }
        while (j >= 0) {
            A[p--] = B[j--];
        }
    }

    public int massage(int[] nums) {
        return massage2(new Integer[nums.length], nums, 0);
    }

    public int massage2(Integer[] memo, int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        memo[start] = Math.max(massage2(memo, nums, start + 1), massage2(memo, nums, start + 2) + nums[start]);
        return memo[start];
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort3(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    private void quickSort3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = arr[left], i = left, j = right;
        while (i < j) {
            while (i < j && arr[j] >= pivot) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j];
            }
            while (i < j && arr[i] <= pivot) {
                i++;
            }
            if (i < j) {
                arr[j--] = arr[i];
            }
        }
        arr[i] = pivot;
        quickSort3(arr, left, i - 1);
        quickSort3(arr, i + 1, right);
    }

    // 确定有限状态自动机，状态枚举
    enum DFA {
        START,
        SIGNED,
        NUMBER,
        END;
    }

    class Automaton {

        // 自动机初始状态
        private DFA state = DFA.START;

        // 记录状态流转
        private Map<DFA, DFA[]> map;

        // 记录符号位
        private char sign = '+';

        // 记录结果
        private int result = 0;

        // 判断终止条件
        private boolean flag = true;

        public Automaton() {
            map = new HashMap<>();
            map.put(DFA.START, new DFA[]{DFA.START, DFA.SIGNED, DFA.NUMBER, DFA.END});
            map.put(DFA.SIGNED, new DFA[]{DFA.END, DFA.END, DFA.NUMBER, DFA.END});
            map.put(DFA.NUMBER, new DFA[]{DFA.END, DFA.END, DFA.NUMBER, DFA.END});
            map.put(DFA.END, new DFA[]{DFA.END, DFA.END, DFA.END, DFA.END});
        }

        public int getResult() {
            return result;
        }

        public boolean getFlag() {
            return flag;
        }

        // 处理状态变化
        public int getIndex(char c) {
            if (c == ' ') return 0;
            if (c == '+' || c == '-') return 1;
            if (c >= '0' && c <= '9') return 2;
            return 3;
        }

        // 计算当前结果
        public void getInteger(char c) {

            // 跟踪当前状态
            state = map.get(state)[getIndex(c)];

            switch (state) {

                // 注意溢出判断
                case NUMBER:
                    if (sign == '+' && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && c - '0' > 7))) {
                        result = Integer.MAX_VALUE;
                        flag = false;
                        break;
                    } else if (sign == '-' && (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && c - '0' > 8))) {
                        result = Integer.MIN_VALUE;
                        flag = false;
                        break;
                    }
                    result = (sign == '+') ? (result * 10 + c - '0') : (result * 10 - (c - '0'));
                    break;

                case SIGNED:
                    sign = c;
                    break;

                case END:
                    flag = false;
                    break;

                default:
                    break;
            }

        }
    }

    // 字符串转换整数
    public int myAtoi(String str) {

        // 特殊情况处理
        if (str == null || str.length() == 0) {
            return 0;
        }

        Automaton automaton = new Automaton();

        for (int i = 0; i < str.length(); i++) {
            if (automaton.getFlag()) {
                automaton.getInteger(str.charAt(i));
            } else {
                break;
            }
        }

        return automaton.getResult();
    }

    public int lastRemaining(int n, int m) {
        int deleted = 0;
        int cur = 0;
        int pre = 0;
        int[] memo = new int[n];
        while (deleted != n - 1) {
            int temp = m;
            while (temp > 0) {
                if (memo[cur] == 0) {
                    pre = cur;
                    cur++;
                    temp--;
                }
            }
            memo[pre] = 1;
            deleted++;
        }
        return cur;
    }

    public String reformat(String s) {
        StringBuilder sb = new StringBuilder();
        Deque<Character> temp1 = new LinkedList<>();
        Deque<Character> temp2 = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                temp1.add(c);
            } else {
                temp2.add(c);
            }
        }
        if (Math.abs(temp1.size() - temp2.size()) > 1) {
            return "";
        }
        while (temp1.size() > 0 && temp2.size() > 0) {
            if (temp1.size() > temp2.size()) {
                sb.append(temp1.poll());
                sb.append(temp2.poll());
            } else {
                sb.append(temp2.poll());
                sb.append(temp1.poll());
            }
        }
        if (!temp1.isEmpty()) {
            sb.append(temp1.poll());
        }
        if (!temp2.isEmpty()) {
            sb.append(temp2.poll());
        }
        return sb.toString();
    }

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int ans = 0;
        Deque<int[]> deque = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (char j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    deque.add(new int[]{i, j});
                    ans++;
                    while (!deque.isEmpty()) {
                        int[] poll = deque.poll();
                        int r = poll[0];
                        int c = poll[1];
                        grid[r][c] = '0';
                        if (r + 1 < grid.length && grid[r + 1][c] == '1') {
                            grid[r + 1][c] = '0';
                            deque.add(new int[]{r + 1, c});
                        }
                        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                            deque.add(new int[]{r - 1, c});
                            grid[r - 1][c] = '0';
                        }
                        if (c + 1 < grid[i].length && grid[r][c + 1] == '1') {
                            deque.add(new int[]{r, c + 1});
                            grid[r][c + 1] = '0';
                        }
                        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                            deque.add(new int[]{r, c - 1});
                            grid[r][c - 1] = '0';
                        }
                    }
                }
            }
        }
        return ans;
    }

    public int numIslands2(char[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    numIslands2dfs(i, j, grid);
                }
            }
        }
        return ans;
    }

    private void numIslands2dfs(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        numIslands2dfs(i - 1, j, grid);
        numIslands2dfs(i + 1, j, grid);
        numIslands2dfs(i, j + 1, grid);
        numIslands2dfs(i, j - 1, grid);
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k。
     * <p>
     * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     * <p>
     * 请返回这个数组中「优美子数组」的数目。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,2,1,1], k = 3
     * 输出：2
     * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
     * 示例 2：
     * <p>
     * 输入：nums = [2,4,6], k = 1
     * 输出：0
     * 解释：数列中不包含任何奇数，所以不存在优美子数组。
     * 示例 3：
     * <p>
     * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * 输出：16
     *
     * @param nums
     * @param k
     * @return
     */
    public int numberOfSubarrays(int[] nums, int k) {
        int temp = 0, ans = 0;
        int[] memo = new int[nums.length];
        Integer[] m2 = new Integer[50001];
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                temp++;
            }
            memo[i] = temp;
            //第一次
            if (m2[temp] == null) {
                m2[temp] = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int b = (nums[i] & 1) == 1 ? 1 : 0;
            int c = memo[i] + k - b;
            Integer r = m2[c];
            if (r == null) {
                continue;
            }
            Integer r2 = m2[c + 1];
            if (r2 == null) {
                ans += nums.length - r;
            } else {
                ans += r2 - r;
            }
        }
        return ans;
    }

    public int[][] imageSmoother(int[][] M) {
        int[][] ans = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                int[] temp = new int[2];
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        imageSmootherHelper(temp, M, i + k, j + l);
                    }
                }
                ans[i][j] = temp[1] / temp[0];
            }
        }
        return ans;
    }

    private void imageSmootherHelper(int[] temp, int[][] m, int i, int j) {
        if (i < 0 || j < 0 || i >= m.length || j >= m[i].length) {
            return;
        }
        temp[0]++;
        temp[1] += m[i][j];
    }

    /**
     * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
     * <p>
     * 示例:
     * <p>
     * 输入: 3
     * 输出: 5
     * 解释:
     * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
     * <p>
     * 1         3     3      2      1
     * \       /     /      / \      \
     * 3     2     1      1   3      2
     * /     /       \                 \
     * 2     1         2                 3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        return numTrees2(new Integer[n + 1], n);
    }

    public int numTrees2(Integer[] dp, int n) {
        if (n == 0)
            return 1;
        if (dp[n] != null)
            return dp[n];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += numTrees2(dp, i - 1) * numTrees2(dp, n - i);
        }
        dp[n] = ans;
        return ans;
    }

    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * <p>
     * 说明：
     * <p>
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     * <p>
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     * <p>
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     * <p>
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak2(new Boolean[s.length()][s.length()], s, wordDict, 0, s.length() - 1);
    }

    private boolean wordBreak2(Boolean[][] dp, String s, List<String> wordDict, int start, int end) {
        if (dp[start][end] != null) {
            logger.info("start:{},end:{},ans:{}", start, end, dp[start][end]);
            return dp[start][end];
        }
        if (wordDict.contains(s.substring(start, end + 1))) {
            dp[start][end] = true;
            logger.info("start:{},end:{},ans:{}", start, end, dp[start][end]);
            return true;
        }
        for (int i = start; i < end; i++) {
            if (wordBreak2(dp, s, wordDict, start, i) && wordBreak2(dp, s, wordDict, i + 1, end)) {
                dp[start][end] = true;
                logger.info("start:{},end:{},ans:{}", start, end, dp[start][end]);
                return true;
            }
        }
        dp[start][end] = false;
        logger.info("start:{},end:{},ans:{}", start, end, dp[start][end]);
        return false;
    }

    /**
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字）。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     * <p>
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int ans = nums[0];
        int preMin = nums[0];
        int preMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = preMin;
            preMin = Math.min(preMax * nums[i], Math.min(preMin * nums[i], nums[i]));
            preMax = Math.max(temp * nums[i], Math.max(preMax * nums[i], nums[i]));
            ans = Math.max(ans, preMax);
        }
        return ans;
    }

    /**
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     * <p>
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums: [1, 1, 1, 1, 1], S: 3
     * 输出: 5
     * 解释:
     * <p>
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     * <p>
     * 一共有5种方法让最终目标和为3。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        return findTargetSumWays2(nums, S, nums.length - 1);
    }

    private int findTargetSumWays2(int[] nums, int S, int i) {
        if (i == 0)
            return Math.abs(S) != nums[0] ? 0 : S == 0 ? 2 : 1;
        return findTargetSumWays2(nums, S - nums[i], i - 1) + findTargetSumWays2(nums, S + nums[i], i - 1);
    }

    /**
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     * <p>
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abc"
     * 输出: 3
     * 解释: 三个回文子串: "a", "b", "c".
     * 示例 2:
     * <p>
     * 输入: "aaa"
     * 输出: 6
     * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindromic-substrings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int ans = 0;
        Boolean[][] dp = new Boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (countSubstrings2(dp, s, i, j)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean countSubstrings2(Boolean[][] dp, String s, int i, int j) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                dp[i][j] = false;
                return false;
            }
            i++;
            j--;
        }
        dp[i][j] = true;
        return true;
    }

    /**
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i >> 1] + i % 2;
        }
        return ans;
    }

    public int maxScore(String s) {
        int temp0 = 0, temp1 = 0, ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            temp0 = 0;
            temp1 = 0;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == '0') {
                    temp0++;
                }
            }
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == '1') {
                    temp1++;
                }
            }
            ans = Math.max(ans, temp0 + temp1);
        }
        return ans;
    }

    public int maxScoreII(int[] cardPoints, int k) {
        int[] memo = new int[cardPoints.length];
        int temp = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < cardPoints.length; i++) {
            temp += cardPoints[i];
            memo[i] = temp;
        }
        //i∈[0,i+L-k-1]
        if (k == cardPoints.length) {
            return temp;
        }
        for (int i = 0; i <= k; i++) {
            min = Math.min(min, memo[i + cardPoints.length - k - 1] - memo[i] + cardPoints[i]);
        }
        return temp - min;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                if (target > nums[mid] && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }

    public int[] singleNumbers(int[] nums) {
        int a = 0, b = 0, c = 0;
        for (int num : nums) {
            a ^= num;
        }
        int temp = 1;
        while ((temp & a) == 0) {
            temp <<= 1;
        }
        for (int num : nums) {
            if ((num & temp) == temp)
                b ^= num;
            else
                c ^= num;
        }
        return new int[]{b, c};
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
    public int maxProfitIV(int[] prices) {
        return maxProfitIV(prices, 0, 0, 0b001 | 0b100);
    }

    /**
     * 从第i天开始买卖能获得最大的利润
     *
     * @param prices 价格
     * @param i      第i天
     * @param k      能进行的操作，买入：0b001,卖出0b010，不操作，0b100
     * @return 最大利润
     */
    private int maxProfitIV(int[] prices, int i, int hold, int k) {
        int ans = 0;
        //买入
        if ((k & 0b001) == 0b001) {
            k = 0b010 | 0b100;
            ans = Math.max(ans, maxProfitIV(prices, i + 1, prices[i], k));
        }
        //卖出
        if ((k & 0b010) == 0b010) {
            k = 0b001 | 0b100;
            ans = Math.max(ans, maxProfitIV(prices, i + 1, 0, k) + hold);
        }
        //不操作
        if ((k & 0b100) == 0b100) {
            ans = Math.max(ans, maxProfitIV(prices, i + 1, hold, k));
        }
        return ans;
    }

    public int findInMountainArray(int target, MountainArray mountainArr) {
        int ans = -1;
        int topIndex = findMountainTop(mountainArr, 0, mountainArr.length() - 1);
        ans = findMountainFront(target, mountainArr, 0, topIndex);
        if (ans != -1)
            return ans;
        return findMountainBack(target, mountainArr, topIndex, mountainArr.length() - 1);
    }

    private int findMountainBack(int target, MountainArray mountainArr, int left, int right) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (mountainArr.get(mid) == target)
                return mid;
            else if (mountainArr.get(mid) > target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    private int findMountainFront(int target, MountainArray mountainArr, int left, int right) {
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (mountainArr.get(mid) == target)
                return mid;
            else if (mountainArr.get(mid) > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    private int findMountainTop(MountainArray mountainArr, int left, int right) {
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (mountainArr.get(mid + 1) > mountainArr.get(mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left == right ? left : -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 2};
        int target = 0;
        MountainArray mountainArray = new MountainArrayImpl(arr);

        LeetCode solution = new LeetCode();
        int res = solution.findInMountainArray(target, mountainArray);
        System.out.println(res);
    }

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     * <p>
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/happy-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        int fast = n, slow = n;
        do {
            fast = isHappy2(fast);
            fast = isHappy2(fast);
            slow = isHappy2(slow);
        } while (fast != slow);
        return fast == 1;
    }

    private int isHappy2(int n) {
        int ans = 0;
        while (n > 0) {
            ans += (n % 10) * (n % 10);
            n /= 10;
        }
        return ans;
    }

    /**
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            fast = nums[fast];
            fast = nums[fast];
            slow = nums[slow];
        } while (fast != slow);
        fast = 0;
        do {
            fast = nums[fast];
            slow = nums[slow];
        } while (fast != slow);
        return fast;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * <p>
     * 示例：
     * <p>
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * <p>
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * <p>
     * 给定的 n 保证是有效的。
     * <p>
     * 进阶：
     * <p>
     * 你能尝试使用一趟扫描实现吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1), slow = sentinel, fast = sentinel;
        sentinel.next = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return sentinel.next;
    }

    /**
     * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，
     * 你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
     * <p>
     * 火车票有三种不同的销售方式：
     * <p>
     * 一张为期一天的通行证售价为 costs[0] 美元；
     * 一张为期七天的通行证售价为 costs[1] 美元；
     * 一张为期三十天的通行证售价为 costs[2] 美元。
     * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，
     * 那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
     * <p>
     * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
     * 输出：11
     * 解释：
     * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
     * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
     * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
     * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
     * 你总共花了 $11，并完成了你计划的每一天旅行。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        //dp[i] = min(dp[i-1]+cost[0],dp[i-7]+cost[1],dp[i-30]+cost[2])
        boolean[] travel = new boolean[days[days.length - 1] + 1];
        int[] dp = new int[days[days.length - 1] + 1];
        for (int day : days) {
            travel[day] = true;
        }
        for (int i = 1; i < dp.length; ++i) {
            if (!travel[i]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = Math.min(getdp(i - 1, dp) + costs[0], Math.min(getdp(i - 7, dp) + costs[1], getdp(i - 30, dp) + costs[2]));
            }
        }
        return dp[days[days.length - 1]];
    }

    public int mincostTicketsII(int[] days, int[] costs) {
        boolean[] travel = new boolean[days[days.length - 1] + 1];
        for (int day : days) {
            travel[day] = true;
        }
        int[] dp = new int[days[days.length - 1] + 1];
        return mincostTicketsRecursive(days, costs, days[days.length - 1], dp, travel);
    }

    public int mincostTicketsRecursive(int[] days, int[] costs, int day, int[] dp, boolean[] travel) {
        if (day < 0) {
            System.out.printf("day:%d,ans:%d\n", day, 0);
            return 0;
        }
        if (dp[day] != 0) {
            System.out.printf("day:%d,ans:%d\n", day, dp[day]);
            return dp[day];
        }
        if (!travel[day]) {
            dp[day] = mincostTicketsRecursive(days, costs, day - 1, dp, travel);
        } else {
            int i1 = mincostTicketsRecursive(days, costs, day - 1, dp, travel) + costs[0];
            int i7 = mincostTicketsRecursive(days, costs, day - 7, dp, travel) + costs[1];
            int i30 = mincostTicketsRecursive(days, costs, day - 30, dp, travel) + costs[2];
            dp[day] = Math.min(i1, Math.min(i7, i30));
        }
        System.out.printf("day:%d,ans:%d\n", day, dp[day]);
        return dp[day];
    }

    int getdp(int index, int[] dp) {
        if (index < 0) {
            return 0;
        } else {
            return dp[index];
        }
    }

    /**
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
     * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     * <p>
     * 示例 1:
     * 给定的树 s:
     * <p>
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     * 给定的树 t：
     * <p>
     * 4
     * / \
     * 1   2
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     * <p>
     * 示例 2:
     * 给定的树 s：
     * <p>
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     * /
     * 0
     * 给定的树 t：
     * <p>
     * 4
     * / \
     * 1   2
     * 返回 false。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        }
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        } else if (t == null) {
            return false;
        }
        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * <p>
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:
     * 5
     * / \
     * 1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    Boolean isValidBSTAns = true;
    Integer isValidBSTPre = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left) || root.val <= isValidBSTPre) {
            return false;
        }
        isValidBSTPre = root.val;
        return isValidBST(root.right);
    }

    private void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            if (root.val <= isValidBSTPre) {
                isValidBSTAns = false;
                return;
            }
            isValidBSTPre = root.val;
            inOrderTraversal(root.right);
        }
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
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
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[i] > 0) {
                    break;
                }
                int sum = nums[left] + nums[right] + nums[i];
                if (sum == 0) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    right--;
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }

    /**
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * <p>
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     * <p>
     * 输出: 4
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximal-square
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] memo = new int[matrix.length][matrix[0].length];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                memo[i][j] = matrix[i][j] == '0' ? 0 : i - 1 < 0 ? 1 : memo[i - 1][j] + 1;
                ans = Math.max(ans, maximalSquareHelper(memo, i, j));
            }
        }
        return ans * ans;
    }

    private int maximalSquareHelper(int[][] memo, int i, int j) {
        int ans = 0, minHeight = Integer.MAX_VALUE, temp = j;
        while (temp >= 0 && memo[i][temp] != 0) {
            minHeight = Math.min(minHeight, memo[i][temp]);
            if (Math.min(minHeight, j - temp + 1) <= ans) {
                return ans;
            }
            ans = Math.min(minHeight, j - temp + 1);
            temp--;
        }
        return ans;
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * <p>
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * <p>
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     * <p>
     * 示例:
     * <p>
     * 输入: [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 说明:
     * <p>
     * 假设你总是可以到达数组的最后一个位置。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
//    public int jump(int[] nums) {
//        return jumpDp(nums, nums.length - 1, new Integer[nums.length]);
//    }
//
//    private int jumpDp(int[] nums, int index, Integer[] dp) {
//        if (index == 0) {
//            return 0;
//        }
//        if (dp[index] != null) {
//            return dp[index];
//        }
//        int ans = Integer.MAX_VALUE;
//        for (int i = 0; i < index; i++) {
//            if (i + nums[i] >= index) {
//                ans = Math.min(ans, jumpDp(nums, i, dp) + 1);
//            }
//        }
//        dp[index] = ans;
//        return ans;
//    }
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);
        int[] visit = new int[nums.length];
        int ans = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Integer index = deque.poll();
                if (index >= nums.length - 1) {
                    return ans;
                }
                for (int j = 0; j < nums[index] && index + j < nums.length; j++) {
                    if (visit[index + j] == 1) {
                        continue;
                    }
                    visit[index + j] = 1;
                    deque.add(index + j);
                }
            }
            ans++;
        }
        return ans;
    }

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
     * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return null;
    }

    private void lowestCommonAncestorDfs(TreeNode root, TreeNode p, TreeNode q) {
//        commonAncestor(root.left);
    }

    private Boolean commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return p == null && q == null;
        }
        if (q == root || p == root) {
            return true;
        }
        return commonAncestor(root.left, p, q) || commonAncestor(root.right, p, q);
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
     * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
     * 返回这三个数的和。假定每组输入只存在唯一答案。
     * <p>
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     * <p>
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int temp = nums[left] + nums[right] + nums[i];
                if (temp == target) {
                    return target;
                } else if (temp > target) {
                    right--;
                } else {
                    left++;
                }
                if (Math.abs(temp - target) < Math.abs(ans - target)) {
                    ans = temp;
                }
            }
        }
        return ans;
    }

    /**
     * 给定一个非空二叉树，返回其最大路径和。
     * <p>
     * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3]
     * <p>
     * 1
     * / \
     * 2   3
     * <p>
     * 输出: 6
     * 示例 2:
     * <p>
     * 输入: [-10,9,20,null,null,15,7]
     * <p>
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * <p>
     * 输出: 42
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    int maxPathSumAns = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumDfs(root);
        return maxPathSumAns;
    }

    private void maxPathSumDfs(TreeNode root) {
        if (root == null) {
            return;
        }
        int sum = Math.max(maxPathSum2(root.left), 0) + Math.max(maxPathSum2(root.right), 0) + root.val;
        maxPathSumAns = Math.max(maxPathSumAns, sum);
        System.out.printf("root:%d,left:%s,right:%s,val:%d\n", root.val, root.left == null ? "null" : root.left.val, root.right == null ? "null" : root.right.val, sum);
        maxPathSumDfs(root.left);
        maxPathSumDfs(root.right);
    }

    /**
     * 包含root节点，只包含root的左子树或右子树，路径和的最大值,
     *
     * @param root
     * @return
     */
    private int maxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }
//        System.out.printf("root:%d,left:%s,right:%s,val:%d\n", root.val, root.left == null ? "null" : root.left.val, root.right == null ? "null" : root.right.val, Math.max(Math.max(0, maxPathSum2(root.left)), maxPathSum2(root.right)) + root.val);
        return Math.max(Math.max(0, maxPathSum2(root.left)), maxPathSum2(root.right)) + root.val;
    }

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     * <p>
     * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,1]
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: false
     * 示例 3:
     * <p>
     * 输入: [1,1,1,3,3,4,3,2,4,2]
     * 输出: true
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/contains-duplicate
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) != null) {
                return true;
            }
            map.put(num, true);
        }
        return false;
    }

    /**
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * <p>
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     * 3
     * / \
     * 1   4
     * \
     *    2
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * 输出: 3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        kthSmallestDfs(root, k, ans);
        return ans.get(k - 1).val;
    }

    private void kthSmallestDfs(TreeNode root, int k, List<TreeNode> ans) {
        if (root == null || ans.size() == k) {
            return;
        }
        kthSmallestDfs(root.left, k, ans);
        ans.add(root);
        kthSmallestDfs(root.right, k, ans);
    }

    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     * <p>
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     * <p>
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * <p>
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     * <p>
     * 说明：不允许修改给定的链表。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：tail connects to node index 1
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        do {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    /**
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     * <p>
     * 注意：
     * <p>
     * 如果两个链表没有交点，返回 null.
     * 在返回结果后，两个链表仍须保持原有的结构。
     * 可假定整个链表结构中没有循环。
     * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode cur = headA;
        while (cur != null && cur.next != null) {
            cur = cur.next;
        }
        cur.next = headB;
        ListNode slow = headA, fast = headA;
        do {
            if (fast == null || fast.next == null) {
                cur.next = null;
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        fast = headA;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        cur.next = null;
        return fast;
    }

    /**
     * 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。
     * 拿掉最后一块石头的人就是获胜者。你作为先手。
     * 你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
     * <p>
     * 示例:
     * <p>
     * 输入: 4
     * 输出: false
     * 解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
     *      因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/nim-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    /**
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     * 示例 2:
     * <p>
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     * 示例 3:
     * <p>
     * 输入: 218
     * 输出: false
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/power-of-two
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
     * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     * <p>
     * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
     * <p>
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * <p>
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        long temp = 1;
        for (int num : nums) {
            temp *= num;
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (temp / nums[i]);
        }
        return ans;
    }

    /**
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 4->2->1->3
     * 输出: 1->2->3->4
     * 示例 2:
     * <p>
     * 输入: -1->5->3->4->0
     * 输出: -1->0->3->4->5
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = sortList(head.next);
        ListNode start = new ListNode(Integer.MIN_VALUE);
        start.next = node;
        ListNode pre = start, cur = node;
        while (cur != null) {
            if (head.val <= cur.val) {
                head.next = cur;
                pre.next = head;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        pre.next = head;
        return start.next;
    }
}