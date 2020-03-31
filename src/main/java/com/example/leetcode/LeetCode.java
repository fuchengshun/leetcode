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
        Integer[] dp = new Integer[nums.length + 1];
        return lengthOfLIS2(dp, nums, nums.length - 1);
    }

    private int lengthOfLIS2(Integer[] dp, int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (i == 0) {
            return 1;
        }
        if (dp[i] != null) {
            return dp[i];
        }
        int max = 1;
        for (int j = 0; j < i; j++) {
            int temp = lengthOfLIS2(dp, nums, j);
            if (nums[i] > nums[j]) {
                temp += 1;
            }
            max = Math.max(max, temp);
        }
        dp[i] = max;
        return dp[i];
    }
}
