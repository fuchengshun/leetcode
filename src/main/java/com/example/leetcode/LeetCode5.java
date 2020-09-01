package com.example.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        return (target = Arrays.binarySearch(nums, target)) < 0 ? -target - 1 : target;
    }

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        DisjoinSet<String> djs = new DisjoinSet<>();
        for (String synonym : synonyms) {
            String[] s = synonym.replaceAll("[()]", "").split(",");
            s[0] = djs.find(s[0]);
            s[1] = djs.find(s[1]);
            boolean b = s[0].compareTo(s[1]) > 0;
            djs.union(b ? s[0] : s[1], b ? s[1] : s[0]);
        }
        Map<String, Integer> map = new HashMap<>();
        for (String name : names) {
            String[] s = name.replace(")", "").split("\\(");
            String key = djs.find(s[0]);
            map.put(key, map.getOrDefault(key, 0) + Integer.parseInt(s[1]));
        }
        String[] ans = new String[map.size()];
        int i = 0;
        for (String s : map.keySet()) {
            ans[i++] = s + "(" + map.get(s) + ")";
        }
        return ans;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) || dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                }
            }
        }
        return dp[m][n];
    }

    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int[] d : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                    if (i + d[0] >= grid.length || i + d[0] < 0 || j + d[1] >= grid[i].length || j + d[1] < 0 || grid[i + d[0]][j + d[1]] == 0) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                dp[i][j] = (i - 1 < 0 ? 0 : dp[i - 1][j]) + (j - 1 < 0 ? 0 : dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (canCompleteCircuit(gas, cost, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean canCompleteCircuit(int[] gas, int[] cost, int i) {
        int current = gas[i], k = i;
        do {
            if (cost[i] > current) {
                return false;
            }
            current = current - cost[i] + (i + 1 < gas.length ? gas[i + 1] : gas[0]);
            i = i + 1 < gas.length ? i + 1 : 0;
        } while (k != i);
        return true;
    }

    public String largestNumberII(int[] nums) {
        String[] array = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(array, (a, b) -> (a + b).compareTo(b + a));
        if ("0".equals(array[0])) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        for (String s : array) {
            ans.append(s);
        }
        return ans.toString();
    }

    public String largestNumber(int[] nums) {
        largestNumberSort(nums, 0, nums.length - 1, new int[nums.length]);
        StringBuilder sb = new StringBuilder();
        if (nums[0] == 0) {
            return "0";
        }
        for (int num : nums) {
            sb.append(num);
        }
        return sb.toString();
    }

    private void largestNumberSort(int[] nums, int i, int j, int[] temp) {
        if (i >= j) {
            return;
        }
        int mid = (i + j) >>> 1;
        largestNumberSort(nums, i, mid, temp);
        largestNumberSort(nums, mid + 1, j, temp);
        int k = 0, m = i, n = mid + 1;
        while (m <= mid && n <= j) {
            if (largestNumberCompare(nums[m], nums[n]) <= 0) {
                temp[k++] = nums[m++];
            } else {
                temp[k++] = nums[n++];
            }
        }
        while (m <= mid) {
            temp[k++] = nums[m++];
        }
        while (n <= j) {
            temp[k++] = nums[n++];
        }
        k = 0;
        while (i <= j) {
            nums[i++] = temp[k++];
        }
    }

    private int largestNumberCompare(int i, int j) {
        String a = String.valueOf(i);
        String b = String.valueOf(j);
        return (a + b).compareTo(b + a);
    }

    public boolean divisorGame(int N) {
        return (N & 1) == 0;
    }

    public int splitArray(int[] nums, int m) {
        int sum = 0, ans = Integer.MAX_VALUE, cur = 0, pre = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            pre = cur;
            cur += nums[i];
            if (cur * m > sum) {
                if (Math.abs(cur * m - sum) > Math.abs(pre * m - sum)) {
                    ans = Math.min(ans, pre);
                    cur = nums[i];
                } else {
                    ans = Math.min(ans, cur);
                    cur = 0;
                }
            }
        }
        return ans;

    }

    public boolean threeConsecutiveOdds(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 1) {
                ans++;
                if (ans == 3) {
                    return true;
                }
            } else {
                ans = 0;
            }
        }
        return false;
    }

    public int minOperations(int n) {
        int i = 0, j = n - 1, ans = 0;
        while (i < j) {
            ans += (-2 * i - 1 + 2 * j + 1);
            i++;
            j--;
        }
        return ans / 2;
    }

    public int minDays(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if (n <= 1) {
            return n;
        }
        map.put(n, Math.min(minDays(n / 3, map) + n % 3, minDays(n / 2, map) + n % 2) + 1);
        return map.get(n);
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int i = 1, j = Integer.MAX_VALUE;
        while (i <= j) {
            int mid = i + j >>> 1;
            int n = position.length, count = 1, last = position[0];
            for (int k = 1; k <= n; k++) {
                if (position[k] - last >= mid) {
                    count++;
                    last = position[k];
                }
            }
            if (count > m) {
                i = mid + 1;
            } else if (count < m) {
                j = mid - 1;
            } else {
                return mid;
            }
        }
        return 1;
    }

    public int fixedPoint(int[] A) {
        int i = 0, j = A.length - 1;
        while (i <= j) {
            int mid = (i + j) >>> 1;
            if (A[mid] < mid) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return j + 1 >= A.length || A[j + 1] != j + 1 ? 1 : j + 1;
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] visit = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != word.charAt(0)) {
                    continue;
                }
                List<int[]> path = new ArrayList<>();
                visit[i][j] = true;
                path.add(new int[]{i, j});
                if (existDfs(board, visit, path, word)) {
                    return true;
                }
                visit[i][j] = false;
            }
        }
        return false;
    }

    private boolean existDfs(char[][] board, boolean[][] visit, List<int[]> path, String word) {
        if (path.size() == word.length()) {
            return true;
        }
        int[] p = path.get(path.size() - 1);
        for (int[] d : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
            int x = p[0] + d[0];
            int y = p[1] + d[1];
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || visit[x][y] || board[x][y] != word.charAt(path.size())) {
                continue;
            }
            visit[x][y] = true;
            path.add(new int[]{x, y});
            if (existDfs(board, visit, path, word)) {
                return true;
            }
            visit[x][y] = false;
            path.remove(path.size() - 1);
        }
        return false;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        boolean toRight = true;
        deque.add(root);
        while (!deque.isEmpty()) {
            List<TreeNode> temp = new ArrayList<>();
            for (int size = deque.size(); size > 0; size--) {
                TreeNode p = deque.poll();
                temp.add(p);
                if (p.left != null) {
                    deque.add(p.left);
                }
                if (p.right != null) {
                    deque.add(p.right);
                }
            }
            if (!toRight) {
                Collections.reverse(temp);
            }
            toRight = !toRight;
            ans.add(temp.stream().map(n -> n.val).collect(Collectors.toList()));
        }
        return ans;
    }

    public boolean judgePoint24(int[] nums) {
        ArrayList<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return judgePoint24(list);
    }

    private boolean judgePoint24(ArrayList<Double> list) {
        double E = 1e-6;
        if (list.isEmpty()) {
            return false;
        }
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24) < E;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {
                    ArrayList<Double> list2 = new ArrayList<>();
                    for (int k = 0; k < list.size(); k++) {
                        if (k != j && k != i) {
                            list2.add(list.get(k));
                        }
                    }
                    Double x = list.get(i), y = list.get(j);
                    for (int k = 0; k < 4; k++) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == 0) {
                            list2.add(x * y);
                        } else if (k == 1) {
                            list2.add(x + y);
                        } else if (k == 3) {
                            list2.add(x - y);
                        } else {
                            if (y < E) {
                                continue;
                            } else {
                                list2.add(x / y);
                            }
                        }
                        if (judgePoint24(list2)) {
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode start = new ListNode(0), cur = head, pre = null, tail = new ListNode(0), tailP = tail;
        start.next = cur;
        boolean odd = false;
        while (cur != null) {
            if (odd) {
                tailP.next = cur;
                tailP = tailP.next;
                pre.next = cur.next;
                cur = cur.next;
                tailP.next = null;
            }
            if (cur != null) {
                odd = true;
                pre = cur;
                cur = cur.next;
            }
        }
        if (pre != null) {
            pre.next = tail.next;
        }
        return start.next;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0), smallP = small, big = new ListNode(0), bigP = big;
        while (head != null) {
            if (head.val < x) {
                smallP.next = head;
                smallP = smallP.next;
                head = head.next;
                smallP.next = null;
            } else {
                bigP.next = head;
                bigP = bigP.next;
                head = head.next;
                bigP.next = null;
            }
        }
        smallP.next = big.next;
        return small.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode tail = reverseNode(headA);
        headA.next = headB;
        ListNode slow = tail, fast = tail;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            headA.next = null;
            reverseNode(tail);
            return null;
        }
        fast = tail;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        headA.next = null;
        reverseNode(tail);
        return slow;
    }

    public ListNode reverseNode(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head, pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.right = sortedListToBST(slow.next);
        if (pre != null) {
            pre.next = null;
        }
        if (head != slow) {
            root.left = sortedListToBST(head);
        }
        return root;
    }

    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }

    public String thousandSeparator(int n) {
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
            if ((s.length() - i) % 3 == 0 && i != 0) {
                sb.append('.');
            }
        }
        return sb.reverse().toString();
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] memo = new int[n];
        for (List<Integer> edge : edges) {
            memo[edge.get(1)]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < memo.length; i++) {
            if (memo[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    public int minOperations(int[] nums) {
        int i = 0, j = 0, m = 0, n = 0;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] == 0) {
                n++;
            } else if (nums[k] == 1) {
                i++;
                nums[k] = 0;
            } else if ((nums[k] & 1) == 1) {
                j++;
                nums[k]--;
            } else {
                m++;
            }
        }
        if (n == nums.length) {
            return 0;
        }
        if (m > 0) {
            for (int k = 0; k < nums.length; k++) {
                nums[k] /= 2;
            }
        }
        return minOperations(nums) + i + j;
    }

    public boolean containsCycle(char[][] grid) {
        boolean[] visit = new boolean[26];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (visit[grid[i][j] - 'a']) {
                    continue;
                }
                visit[grid[i][j] - 'a'] = true;
                List<int[]> path = new ArrayList<>();
                path.add(new int[]{i, j});
                boolean[][] visit1 = new boolean[grid.length][grid[0].length];
                visit1[i][j] = true;
                if (containsCycle2(grid, path, visit1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsCycle2(char[][] grid, List<int[]> path, boolean[][] visit) {
        if (path.size() > 3) {
            int[] m = path.get(0);
            int[] n = path.get(path.size() - 1);
            if (m[0] == n[0] && Math.abs(m[1] - n[1]) == 1 || m[1] == n[1] && Math.abs(m[0] - n[0]) == 1) {
                return true;
            }
        }
        int[] p = path.get(path.size() - 1);
        for (int[] d : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
            int x = p[0] + d[0];
            int y = p[0] + d[0];
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != grid[p[0]][p[1]] || visit[x][y]) {
                continue;
            }
            path.add(new int[]{x, y});
            visit[x][y] = true;
            if (containsCycle2(grid, path, visit)) {
                return true;
            }
            visit[x][y] = false;
            path.remove(path.size() - 1);
        }
        return false;
    }

    public List<String> findItinerary2(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            PriorityQueue<String> queue = map.getOrDefault(ticket.get(0), new PriorityQueue<>());
            queue.add(ticket.get(1));
            map.put(ticket.get(0), queue);
        }
        List<String> ans = new ArrayList<>();
        findItineraryDfs(ans, "JFK", map);
        Collections.reverse(ans);
        return ans;
    }

    private void findItineraryDfs(List<String> ans, String cur, Map<String, PriorityQueue<String>> map) {
        while (map.containsKey(cur) && !map.get(cur).isEmpty()) {
            findItineraryDfs(ans, map.get(cur).poll(), map);
        }
        ans.add(cur);
    }

    public int findKthNumber(int n, int k) {
        return findKthNumberDfs(n, k, 1);
    }

    private int findKthNumberDfs(int n, int k, int i) {
        if (k == 1) {
            return i;
        }
        int count = countNode(n, i);
        if (count < k) {
            return findKthNumberDfs(n, k - count, i + 1);
        } else {
            return findKthNumberDfs(n, k - 1, i * 10);
        }
    }

    private int countNode(int n, long i) {
        int count = 0;
        long j = i + 1;
        while (i <= n) {
            count += Math.min(n + 1, j) - i;
            j *= 10;
            i *= 10;
        }
        return count;
    }

    public int minDays2(int n, Map<Integer, Integer> map) {
        if (map.get(n) != null) {
            return map.get(n);
        }
        if (n == 1) {
            return 1;
        } else if (n == 2 || n == 3) {
            return 2;
        }
        int ans = minDays2(n - 1, map) + 1;
        if (n % 2 == 0) {
            ans = Math.min(ans, minDays(n / 2, map) + 1);
        }
        if (n % 3 == 0) {
            ans = Math.min(ans, minDays2(n / 3, map) + 1);
        }
        map.put(n, ans);
        return ans;
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        Deque<int[]> deque = new LinkedList<>();
        boolean[][] visit = new boolean[board.length][board[0].length];
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {1, -1}};
        deque.add(click);
        visit[click[0]][click[1]] = true;
        while (!deque.isEmpty()) {
            int[] p = deque.poll();
            char c = board[p[0]][p[1]];
            if (c == 'M') {
                board[p[0]][p[1]] = 'X';
            } else {
                int count = 0;
                for (int[] d : direction) {
                    if (d[0] + p[0] < 0 || d[0] + p[0] >= board.length || d[1] + p[1] < 0 || d[1] + p[1] >= board[0].length) {
                        continue;
                    }
                    if (board[d[0] + p[0]][d[1] + p[1]] == 'M') {
                        count++;
                    }
                }
                if (count > 0) {
                    board[p[0]][p[1]] = (char) (count + '0');
                } else {
                    board[p[0]][p[1]] = 'B';
                    for (int[] d : direction) {
                        if (d[0] + p[0] < 0 || d[0] + p[0] >= board.length || d[1] + p[1] < 0 || d[1] + p[1] >= board[0].length || visit[d[0] + p[0]][d[1] + p[1]] || board[d[0] + p[0]][d[1] + p[1]] != 'E') {
                            continue;
                        }
                        deque.add(new int[]{d[0] + p[0], d[1] + p[1]});
                        visit[d[0] + p[0]][d[1] + p[1]] = true;
                    }
                }
            }
        }
        return board;
    }

    int minTransfersAns = Integer.MAX_VALUE;

    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] tx : transactions) {
            map.put(tx[0], map.getOrDefault(tx[0], 0) - tx[2]);
            map.put(tx[1], map.getOrDefault(tx[1], 0) + tx[2]);
        }
        List<Integer> list = map.values().stream().filter(a -> a != 0).collect(Collectors.toList());
        List<Integer> li = new ArrayList<>();
        int m = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.contains(-list.get(i))) {
                m++;
            } else {
                li.add(list.get(i));
            }
        }
        minTransfersDfs(li, new LinkedList<>());
        return minTransfersAns + m / 2;
    }

    private void minTransfersDfs(List<Integer> list, Deque<Integer> path) {
        if (path.size() == list.size()) {
            minTransfersAns = Math.min(minTransfersSteps(new ArrayList<>(list), new LinkedList<>(path)), minTransfersAns);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (path.contains(i)) {
                continue;
            }
            if (list.get(i) > 0) {
                path.addLast(i);
                minTransfersDfs(list, path);
                path.removeLast();
            } else {
                path.addFirst(i);
                minTransfersDfs(list, path);
                path.removeFirst();
            }
        }
    }

    private int minTransfersSteps(List<Integer> list, Deque<Integer> path) {
        int ans = 0;
        while (!path.isEmpty()) {
            ans++;
            int k = list.get(path.getFirst()) + list.get(path.getLast());
            if (k >= 0) {
                path.pollFirst();
                list.set(path.getLast(), k);
                if (k == 0) {
                    path.pollLast();
                }
            } else {
                path.pollLast();
                ;
                list.set(path.getFirst(), k);
            }
        }
        return ans;
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return left == 0 ? right + 1 : right == 0 ? left + 1 : Math.min(left, right) + 1;
    }

    public int minimumMoves(int[] arr, int i, int j, Integer[][] dp) {
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (i == j) {
            return 1;
        }
//        for (int k = i; k <= j; k++) {
//            minimumMoves(arr, i, k, dp) + minimumMoves(arr, k + 1, j, dp);
//        }
        return dp[0][arr.length - 1];
    }

    public String boldWords(String[] words, String S) {
        boolean[] bold = new boolean[S.length()];
        for (String word : words) {
            for (int i = 0; i < S.length(); i++) {
                int k = S.indexOf(word, i);
                if (k >= 0) {
                    for (int j = 0; j < word.length(); j++) {
                        bold[k + j] = true;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bold.length; i++) {
            if (bold[i]) {
                if (i == 0 || !bold[i - 1]) {
                    sb.append("<b>");
                }
            } else {
                if (i > 0 && bold[i - 1]) {
                    sb.append("</b>");
                }
            }
            sb.append(S.charAt(i));
        }
        if (bold[S.length() - 1]) {
            sb.append("</b>");
        }
        return sb.toString();
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length(), ans = 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = i - 1 < 0 || j - 1 < 0 ? 1 : dp[i - 1][j - 1] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }

    public boolean repeatedSubstringPattern(String s) {
        return (s + s).indexOf(s, 1) < s.length();
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        boolean modify = false;
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode start = new ListNode(0);
        start.next = head;
        map.put(0, start);
        ListNode cur = head;
        int sum = 0;
        while (cur != null) {
            sum += cur.val;
            if (map.containsKey(sum)) {
                map.get(sum).next = cur.next;
                modify = true;
            } else {
                map.put(sum, cur);
            }
            cur = cur.next;
        }
        if (!modify) {
            return start.next;
        }
        return removeZeroSumSublists(start.next);
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2 == null;
        } else if (t2 == null) {
            return true;
        }
        return t1.val == t2.val && isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    private boolean isSameTree(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2 == null;
        } else if (t2 == null) {
            return true;
        }
        return t1.val == t2.val && isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return head == null;
        } else if (head == null) {
            return true;
        }
        return root.val == head.val && (isSubPath2(head.next, root.left) || isSubPath2(head.next, root.right)) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean isSubPath2(ListNode head, TreeNode root) {
        if (root == null) {
            return head == null;
        } else if (head == null) {
            return true;
        }
        return root.val == head.val && (isSubPath2(head.next, root.left) || isSubPath2(head.next, root.right));
    }

    public void deleteNode(ListNode node) {
        ListNode pre = null;
        while (node != null) {
            if (node.next != null) {
                node.val = node.next.val;
            } else if (pre != null) {
                pre.next = null;
            }
            pre = node;
            node = node.next;
        }
    }

    public boolean isPalindrome(ListNode head) {
        ListNode node = head;
        int k = 0;
        while (head != null) {
            head = head.next;
            k++;
        }
        return isPalindrome2(node, k);
    }

    public boolean isPalindrome3(ListNode head) {
        ListNode node = reverseListNode(head);
        return node == null || node.next == null;
    }

    public ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseListNode(head.next);
        if (node.val == head.val) {
            head.next.next = null;
            return node.next;
        }
        head.next.next = head;
        head.next = null;
        return node;
    }

    private boolean isPalindrome2(ListNode head, int k) {
        if (k <= 1) {
            return true;
        }
        ListNode node = head;
        int m = k;
        while (k - 1 > 0) {
            node = node.next;
            k--;
        }
        return head.val == node.val && isPalindrome2(head.next, m - 2);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode start = new ListNode(0), cur = start;
        int carry = 0;
        while (l1 != null || l2 != null) {
            cur.next = new ListNode((l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry);
            if (cur.next.val > 9) {
                cur.next.val %= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(1);
        }
        return start.next;
    }

    public int[] nextLargerNodes(ListNode head) {
        ListNode cur = head;
        int k = 0;
        while (cur != null) {
            cur = cur.next;
            k++;
        }
        LinkedList<int[]> stack = new LinkedList<>();
        int[] ans = new int[k];
        k = 0;
        while (head != null) {
            if (!stack.isEmpty() && stack.peek()[1] < head.val) {
                ans[stack.pop()[0]] = head.val;
            } else {
                stack.push(new int[]{k, head.val});
                head = head.next;
                k++;
            }
        }
        return ans;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = insertionSortList(head.next), start = new ListNode(0), pre = start;
        start.next = node;
        while (node != null) {
            if (node.val > head.val) {
                pre.next = head;
                head.next = node;
                break;
            }
            pre = node;
            node = node.next;
        }
        if (node == null) {
            pre.next = head;
            head.next = null;
        }
        return start.next;
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        findSubsequences2(nums, -1, new ArrayList<>(), ans);
        return ans;
    }

    private void findSubsequences2(int[] nums, int i, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() > 1) {
            ans.add(new ArrayList<>(path));
        }
        HashSet<Integer> set = new HashSet<>();
        for (int j = i + 1; j < nums.length; j++) {
            if (set.contains(nums[j])) {
                continue;
            }
            set.add(nums[j]);
            if (i > -1 && nums[j] < nums[i]) {
                continue;
            }
            path.add(nums[j]);
            findSubsequences2(nums, j, path, ans);
            path.remove(path.size() - 1);
        }
    }

    public void findSubsequences(int[] nums, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() >= 2) {
            ans.add(path.stream().map(a -> nums[a]).collect(Collectors.toList()));
        }
        for (int i = path.isEmpty() ? 0 : path.get(path.size() - 1) + 1; i < nums.length; i++) {
            boolean b = false;
            for (int j = path.size(); j < i; j++) {
                if (nums[j] == nums[i]) {
                    b = true;
                    break;
                }
            }
            if (b || !path.isEmpty() && nums[i] < nums[path.get(path.size() - 1)]) {
                continue;
            }
            path.add(i);
            findSubsequences(nums, path, ans);
            path.remove(path.size() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        String[] words = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        letterDfs(ans, words, digits, "");
        return ans;
    }

    private void letterDfs(List<String> ans, String[] words, String digits, String s) {
        if (s.length() == digits.length()) {
            ans.add(s);
            return;
        }
        String s1 = words[digits.charAt(s.length()) - '2'];
        for (int i = 0; i < s1.length(); i++) {
            s += s1.charAt(i);
            letterDfs(ans, words, digits, s);
            s = s.substring(0, s.length() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        permuteDfs(ans, nums, new LinkedList<>(), new boolean[nums.length]);
        return ans;
    }

    private void permuteDfs(List<List<Integer>> ans, int[] nums, Deque<Integer> stack, boolean[] used) {
        if (stack.size() == nums.length) {
            ans.add(new ArrayList<>(stack));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            stack.push(nums[i]);
            used[i] = true;
            permuteDfs(ans, nums, stack, used);
            stack.pop();
            used[i] = false;
        }
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String[]>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            PriorityQueue<String[]> queue = map.getOrDefault(ticket.get(0), new PriorityQueue<>());
            queue.add(new String[]{"0", ticket.get(1)});
            map.put(ticket.get(0), queue);
        }
        List<String> ans = new ArrayList<>();
        String temp = "JFK";
        do {
            ans.add(temp);
            if (!map.containsKey(map.get(temp).peek()[1])) {
                String[] poll = map.get(temp).poll();
                poll[0] = "1";
                map.get(temp).add(poll);
            } else {
                temp = map.get(temp).poll()[1];
            }
        } while (temp != null && !map.containsKey(temp) && map.get(temp).isEmpty());
        return ans;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        combinationSumDfs(candidates, 0, target, ans, new ArrayList<>());
        return ans;
    }

    private void combinationSumDfs(int[] candidates, int start, int target, List<List<Integer>> ans, List<Integer> path) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
        }
        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            if (i == start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            combinationSumDfs(candidates, i + 1, target - candidates[i], ans, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        combineDfs(ans, n, k, 0, new ArrayList<>());
        return ans;
    }

    private void combineDfs(List<List<Integer>> ans, int n, int k, int start, ArrayList<Integer> path) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
        }
        for (int i = start; i < n + 1; i++) {
            path.add(i);
            combineDfs(ans, n, k, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        subsetsWithDupDfs(ans, 0, nums, new ArrayList<>());
        return ans;
    }

    private void subsetsWithDupDfs(List<List<Integer>> ans, int i, int[] nums, List<Integer> path) {
        logger.info("i:{},path:{}", i, path.stream().map(String::valueOf).collect(Collectors.joining(",")));
        ans.add(new ArrayList<>(path));
        for (int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            path.add(nums[j]);
            subsetsWithDupDfs(ans, j + 1, nums, path);
            path.remove(path.size() - 1);
        }
    }

    public String getPermutation2(int n, int k) {
        int[] a = new int[10];
        a[0] = 1;
        for (int i = 1; i < 10; i++) {
            a[i] = a[i - 1] * i;
        }
        ;
        List<Integer> path = new ArrayList<>();
        getPermutationDfs(n, k, path, a);
        return path.stream().map(String::valueOf).collect(Collectors.joining(""));
    }

    private void getPermutationDfs(int n, int k, List<Integer> path, int[] a) {
        if (path.size() == n && k == 1) {
            return;
        }
        int b = a[n - 1 - path.size()];
        for (int i = 1; i <= n; i++) {
            if (path.contains(i)) {
                continue;
            }
            if (k > b) {
                k -= b;
                continue;
            }
            path.add(i);
            getPermutationDfs(n, k, path, a);
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        return canVisitAllRoomsDfs(new boolean[rooms.size()], rooms, 0) == rooms.size();
    }

    private int canVisitAllRoomsDfs(boolean[] visit, List<List<Integer>> rooms, int cur) {
        int count = 1;
        visit[cur] = true;
        for (Integer i : rooms.get(cur)) {
            if (!visit[i]) {
                visit[i] = true;
                count += canVisitAllRoomsDfs(visit, rooms, i);
            }
        }
        return count;
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        restoreIpAddressesDfs(s, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void restoreIpAddressesDfs(String s, List<String> ans, ArrayList<String> path, int cur) {
        if (path.size() == 4) {
            ans.add(String.join(".", path));
        }
        int min = Math.max(s.length() - cur - 3 * (4 - path.size() - 1), 1), max = Math.min(3, s.length() - cur - (4 - path.size() - 1));
        for (int length = min; length <= max; length++) {
            if (length > 1 && s.charAt(cur) == '0' || length == 3 && Integer.parseInt(s.substring(cur, cur + length)) > 255) {
                continue;
            }
            path.add(s.substring(cur, cur + length));
            restoreIpAddressesDfs(s, ans, path, cur + length);
            path.remove(path.size() - 1);
        }
    }

    public int longestSubstring(String s, int k) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        ArrayList<Integer> split = new ArrayList<>();
        int max=0;
        for (int i = 0; i < s.length(); i++) {
            int b = count[s.charAt(i) - 'a'];
            max=Math.max(max, b);
            if (b < k) {
                split.add(i);
            }
        }
        if (split.isEmpty()) {
            return s.length();
        }
        if(max<k){
            return 0;
        }
        int ans = 0, pre = 0;
        split.add(s.length());
        for (Integer i : split) {
            ans = i > pre ? Math.max(ans, longestSubstring(s.substring(pre, i), k)) : ans;
            pre = i + 1;
        }
        return ans;
    }
}
