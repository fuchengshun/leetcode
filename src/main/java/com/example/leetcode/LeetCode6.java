package com.example.leetcode;

import com.example.leetcode.unionfind.UnionFind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeetCode6 {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<List<Integer>> findSubsequences(int[] nums) {
        Integer[] m = new Integer[nums.length];
        for (int i = 0; i < m.length; i++) {
            m[i] = i;
        }
        Arrays.sort(m, Comparator.comparingInt(a -> nums[a]));
        List<List<Integer>> ans = new ArrayList<>();
        findSubsequencesDfs(m, nums, 0, ans, new ArrayList<>());
        return ans;
    }

    private void findSubsequencesDfs(Integer[] m, int[] nums, int start, List<List<Integer>> ans, List<Integer> path) {
        if (path.size() > 1) {
            ans.add(path.stream().map(a -> nums[m[a]]).collect(Collectors.toList()));
        }
        for (int i = start; i < m.length; i++) {
            boolean b = !path.isEmpty() && m[i] < m[path.get(path.size() - 1)];
            boolean a = i > start && i - 1 >= 0 && nums[m[i - 1]] == nums[m[i]] && !path.contains(i - 1);
            if (b || a) {
                continue;
            }
            path.add(i);
            findSubsequencesDfs(m, nums, i + 1, ans, path);
            path.remove(path.size() - 1);
        }
    }

    public String[] permutation(String s) {
        ArrayList<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        permutationDfs(chars, ans, new StringBuilder(), new boolean[s.length()]);
        return ans.toArray(new String[0]);
    }

    private void permutationDfs(char[] chars, ArrayList<String> ans, StringBuilder s, boolean[] used) {
        if (s.length() == chars.length) {
            ans.add(s.toString());
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i] || i - 1 >= 0 && chars[i - 1] == chars[i] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            s.append(chars[i]);
            permutationDfs(chars, ans, s, used);
            s.deleteCharAt(s.length() - 1);
            used[i] = false;
        }
    }

    public String minNumber(int[] nums) {
        return Arrays.stream(nums).mapToObj(String::valueOf).sorted((a, b) -> (a + b).compareTo(b + a)).collect(Collectors.joining(""));
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] m = new int[26], n = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            m[s1.charAt(i) - 'a']++;
        }
        int i = 0, j = 0;
        while (j < s2.length()) {
            n[s2.charAt(j++) - 'a']++;
            while (checkInclusionCheck(m, n)) {
                if (checkInclusionMatch(m, n)) {
                    return true;
                }
                n[s2.charAt(i++) - 'a']--;
            }
        }
        return false;
    }

    private boolean checkInclusionCheck(int[] m, int[] n) {
        for (int i = 0; i < 26; i++) {
            if (m[i] > n[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkInclusionMatch(int[] m, int[] n) {
        for (int i = 0; i < 26; i++) {
            if (m[i] != n[i]) {
                return false;
            }
        }
        return true;
    }

    public String arrangeWords(String text) {
        String collect = Arrays.stream(text.toLowerCase().split("\\s+")).sorted((a, b) -> a.length() - b.length()).collect(Collectors.joining(""
        ));
        return collect.substring(0, 1).toUpperCase() + collect.substring(1);
    }

    public String permutation2(String S) {
        char[] chars = S.toCharArray();
        Arrays.sort(chars);
        return permutation2Dfs(new boolean[S.length()], new StringBuilder(), chars);
    }

    private String permutation2Dfs(boolean[] used, StringBuilder sb, char[] chars) {
        if (chars.length == sb.length()) {
            return sb.toString();
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i] || i - 1 >= 0 && chars[i - 1] == chars[i] && !used[i - 1] || sb.length() > 0 && sb.charAt(sb.length() - 1) == chars[i]) {
                continue;
            }
            used[i] = true;
            sb.append(chars[i]);
            String s = permutation2Dfs(used, sb, chars);
            if (!s.isEmpty()) {
                return s;
            }
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
        return "";
    }

    public String[] permutation3(String S) {
        ArrayList<String> ans = new ArrayList<>();
        char[] chars = S.toCharArray();
        permutation3Dfs(ans, new StringBuilder(), new boolean[S.length()], chars);
        return ans.toArray(new String[0]);
    }

    private void permutation3Dfs(ArrayList<String> ans, StringBuilder sb, boolean[] used, char[] chars) {
        if (sb.length() == chars.length) {
            ans.add(sb.toString());
        }
        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {
                used[i] = true;
                sb.append(chars[i]);
                permutation3Dfs(ans, sb, used, chars);
                sb.deleteCharAt(sb.length() - 1);
                used[i] = false;
            }
        }
    }

    public String restoreString(String s, int[] indices) {
        int[] ints = IntStream.of(5, 6, 1).sorted().toArray();
        return Arrays.stream(indices).sorted().mapToObj(a -> String.valueOf(s.charAt(a))).collect(Collectors.joining(""));
    }

    public String reverseWords(String s) {
        return Arrays.stream(s.split("\\s+")).map(a -> new StringBuilder(s).reverse().toString()).collect(Collectors.joining(" "));
    }

    public int getMaxLen(int[] nums) {
        int[][] dp = new int[nums.length][2];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                dp[i][0] = 0;
                dp[i][1] = 0;
            } else if (nums[i] > 0) {
                dp[i][0] = i == 0 ? 1 : dp[i - 1][0] + 1;
                dp[i][1] = i == 0 ? 0 : dp[i - 1][1] > 0 ? dp[i - 1][1] + 1 : 0;
            } else {
                dp[i][0] = i == 0 ? 0 : dp[i - 1][1] > 0 ? dp[i - 1][1] + 1 : 0;
                dp[i][1] = i == 0 ? 1 : dp[i - 1][0] + 1;
            }
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }

    public int swimInWater(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        PriorityQueue<int[]> pr = new PriorityQueue<>(Comparator.comparingInt(a -> grid[a[0]][a[1]]));
        boolean[][] used = new boolean[m][n];
        pr.add(new int[]{0, 0});
        used[0][0] = true;
        while (!pr.isEmpty()) {
            int[] p = pr.poll();
            ans = Math.max(ans, grid[p[0]][p[1]]);
            if (p[0] == m - 1 && p[1] == n - 1) {
                return ans;
            }
            for (int[] d : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                int x = p[0] + d[0], y = p[1] + d[1];
                if (x < 0 || y < 0 || x >= m || y >= n || used[x][y]) {
                    continue;
                }
                pr.add(new int[]{x, y});
                used[x][y] = true;
            }
        }
        return -1;
    }

    public int largestComponentSize(int[] A) {
        int max = Arrays.stream(A).max().getAsInt();
        UnionFind unionFind = new UnionFind(max + 1);
        for (int num : A) {
            double upBound = Math.sqrt(num);
            for (int i = 2; i <= upBound; i++) {
                if (num % i == 0) {
                    unionFind.union(num, i);
                    unionFind.union(num, num / i);
                }
            }
        }
        int[] count = new int[max + 1];
        int ans = 0;
        for (int i : A) {
            ans = Math.max(ans, ++count[unionFind.find(i)]);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        int max = 0,temp=0;
        for (char t : tasks) {
            max = Math.max(max, ++count[t - 'A']);
        }
        for (int i : count) {
            if (i==max){
                temp++;
            }
        }
        return Math.max (tasks.length,(max-1)*(n+1)+temp);
    }
}
