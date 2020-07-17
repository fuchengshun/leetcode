package com.example.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.IntStream;

public class LeetCode5 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public int[] decompressRLElist(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i]; j++) {
                list.add(nums[i + 1]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (queue.contains(num)) {
                continue;
            }
            if (queue.size() < 3) {
                queue.add(num);
            } else if (num > queue.peek()) {
                queue.poll();
                queue.add(num);
            }
        }
        if (queue.size() == 3) {
            return queue.peek();
        }
        while (queue.size() > 1) {
            queue.poll();
        }
        return queue.peek();
    }

    public String reverseWords(String s) {
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    public int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum < 10 ? sum : addDigits(sum);
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public char findTheDifference(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) {
                return chars2[i];
            }
        }
        return chars2[chars2.length - 1];
    }

    public boolean isBipartite(int[][] graph) {
        for (int j = 0; j < graph.length; j++) {
            LinkedList<Integer> deque = new LinkedList<>();
            int[] color = new int[graph.length];
            deque.add(j);
            color[j] = 1;
            while (!deque.isEmpty()) {
                Integer p = deque.pop();
                int nextColor = color[p] == 1 ? 2 : 1;
                for (int i : graph[p]) {
                    if (color[i] == 0) {
                        color[i] = nextColor;
                        deque.add(i);
                    } else if (nextColor != color[i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    int isValidBSTPre = Integer.MIN_VALUE;

    private boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val < isValidBSTPre) {
            return false;
        }
        isValidBSTPre = root.val;
        return isValidBST(root.right);
    }

    //       i   k p          m       j
    //输入："1111--2---3---4--5---6---7"
    public TreeNode recoverFromPreorder(String S) {
        return recoverFromPreorder(S, 0, S.length() - 1);
    }

    private TreeNode recoverFromPreorder(String S, int i, int j) {
        logger.info("S:{},i:{},j:{}", S, i, j);
        if (i > j) {
            return null;
        }
        int k = i + 1;
        while (k <= j && S.charAt(k) != '-') {
            k++;
        }
        TreeNode root = new TreeNode(Integer.parseInt(S.substring(i, k)));
        int p = k + 1;
        while (p <= j && S.charAt(p) == '-') {
            p++;
        }
        int m = p + 1, count = 0;
        while (m <= j) {
            if (S.charAt(m) == '-') {
                count++;
            } else if (count == p - k) {
                root.left = recoverFromPreorder(S, p, m - (p - k));
                root.right = recoverFromPreorder(S, m, j);
                break;
            } else {
                count = 0;
            }
            m++;
        }
        if (m > j) {
            root.left = recoverFromPreorder(S, p, j);
        }
        return root;
    }

    //       i   k p          m       j
    //输入："1111--2---3---4--5---6---7"
    public TreeNode recoverFromPreorderII(String S) {
        String[] delimiter = S.split("\\d+"), nums = S.split("-+");
        return dfs(delimiter, nums, 0, nums.length - 1);
    }

    private TreeNode dfs(String[] delimiter, String[] nums, int i, int j) {
        if (i > j) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nums[i]));
        int[] K = IntStream.range(i + 1, j + 1).filter(m -> delimiter[m].equals(delimiter[i + 1])).toArray();
        root.left = dfs(delimiter, nums, i + 1, K.length > 1 ? K[1] - 1 : j);
        root.right = K.length > 1 ? dfs(delimiter, nums, K[1], j) : null;
        return root;
    }

    public String getPermutation(int n, int k) {
        ArrayList<String> ans = new ArrayList<>();
        getPermutation(n, k, new ArrayList<>(), ans);
        return ans.get(k - 1);
    }

    private void getPermutation(int n, int k, List<String> path, List<String> ans) {
        if (path.size() == n) {
            ans.add(String.join("", path));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (path.contains(String.valueOf(i))) {
                continue;
            }
            path.add(String.valueOf(i));
            getPermutation(n, k - 1, path, ans);
            path.remove(path.size() - 1);
        }
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left + 1;
    }

    public int searchInsertII(int[] nums, int target) {
        return (target = Arrays.binarySearch(nums, target)) < 0 ? -target-1 : target;
    }
}
