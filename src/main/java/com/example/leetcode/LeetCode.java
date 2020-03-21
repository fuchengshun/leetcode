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
        if (row ==9) {
            System.out.println(8888);
            return;
        }
//        if (board[row][col] != '.') {
//            if (col + 1 < board[row].length)
//                backtrack3(board, row, col + 1);
//            else
//                backtrack3(board, row + 1, 0);
//        }

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
}
