package com.example.leetcode;

import java.util.*;

public class LeetCode7 {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = first;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }

    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] ans = new int[nums.length];
        for (int i = 0, j = 0, k = (nums.length + 1) / 2; i < nums.length; i += 2) {
            ans[i] = nums[j++];
            if (i + 1 < nums.length) {
                ans[i + 1] = nums[k++];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        partitionDfs(s, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void partitionDfs(String s, List<List<String>> ans, ArrayList<String> path, int cur) {
        if (cur >= s.length()) {
            ans.add(new ArrayList<>(path));
        }
        for (int i = 0; cur + i < s.length(); i++) {
            if (partitionCheck(s, cur, cur + i)) {
                path.add(s.substring(cur, cur + i + 1));
                partitionDfs(s, ans, path, cur + i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean partitionCheck(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public int minCut(String s) {
        Integer[][] dp = new Integer[s.length()][s.length()];
        boolean[][] checkPalindrome = new boolean[s.length()][s.length()];
        for (int right = 0; right < s.length(); right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || checkPalindrome[left + 1][right - 1])) {
                    checkPalindrome[left][right] = true;
                }
            }
        }
        return minCutDp(checkPalindrome, dp, s, 0, s.length() - 1);
    }

    private int minCutDp(boolean[][] memo, Integer[][] dp, String s, int i, int j) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (s.length() < 2) {
            return 0;
        }
        if (memo[i][j]) {
            return dp[i][j] = 0;
        }
        int max = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            max = Math.min(max, minCutDp(memo, dp, s, i, k) + minCutDp(memo, dp, s, k + 1, j) + 1);
        }
        dp[i][j] = max;
        return max;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<String, List<Integer>> map = new HashMap<>();
//        HashMap<String, Boolean> used = new HashMap<>();
        boolean[] used = new boolean[wordList.size()];
        for (int i = 0; i < wordList.size(); i++) {
            if (ladderLengthCheck(beginWord, wordList.get(i))) {
                List<Integer> a = map.getOrDefault(beginWord, new ArrayList<>());
                a.add(i);
                map.put(beginWord, a);
            }
            for (int j = i + 1; j < wordList.size(); j++) {
                if (ladderLengthCheck(wordList.get(i), wordList.get(j))) {
                    List<Integer> a = map.getOrDefault(wordList.get(i), new ArrayList<>());
                    a.add(j);
                    map.put(wordList.get(i), a);
                    List<Integer> b = map.getOrDefault(wordList.get(j), new ArrayList<>());
                    b.add(i);
                    map.put(wordList.get(j), b);
                }
            }
        }
        LinkedList<String> li = new LinkedList<>();
        li.add(beginWord);
//        used.put(beginWord, true);
        int ans = 0;
        while (!li.isEmpty()) {
            ans++;
            for (int size = li.size(); size > 0; size--) {
                String p = li.poll();
                if (p.equals(endWord)) {
                    return ans;
                }
                for (Integer s : map.get(p)) {
//                    if (!used.containsKey(s)){
                    if (!used[s]) {
                        li.add(wordList.get(s));
                        used[s] = true;
                    }
                }
            }
        }
        return 0;
    }

    private boolean ladderLengthCheck(String s, String t) {
        int i = 0, count = 0;
        while (i < s.length()) {
            if (s.charAt(i) != t.charAt(i)) {
                count++;
            }
            i++;
        }
        return count <= 1;
    }

    public int lengthOfLIS(int[] nums) {
        int ans = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] =Math.max(dp[i], nums[i] <= nums[j] ? 1 : dp[j] + 1);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public boolean isNumber(String s) {
        return new FDATree().isNumber(s);
    }


}
