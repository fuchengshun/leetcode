package com.example.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;
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

    public List<String> findItinerary(List<List<String>> tickets) {
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
}
