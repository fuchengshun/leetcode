package com.example.leetcode;


import java.util.*;

public class LeetCode {
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
            if (max == null || max.equals(stack.peek().getValue())){
                Map.Entry<Integer, Integer> pop = stack.pop();
                list.add(pop.getKey());
                max=pop.getValue();
            }
            else
                break;
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i]=list.get(i);
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
}
