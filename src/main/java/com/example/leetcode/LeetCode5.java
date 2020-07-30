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

    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return null;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateTrees(start, i - 1);
            List<TreeNode> right = generateTrees(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right -= 1;
            }
        }
        return numbers[left];
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        distanceK(root, null, map);
        HashSet<TreeNode> set = new HashSet<>();
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(target);
        set.add(target);
        while (!deque.isEmpty() && K >= 0) {
            for (int size = deque.size(); size > 0; size--) {
                TreeNode p = deque.poll();
                if (K == 0) {
                    ans.add(p.val);
                }
                for (TreeNode n : new TreeNode[]{p.left, p.right, map.get(p)}) {
                    if (n != null && !set.contains(n)) {
                        deque.add(n);
                        set.add(n);
                    }
                }
            }
            K--;
        }
        return ans;
    }

    private void distanceK(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> map) {
        if (root == null) {
            return;
        }
        map.put(root, parent);
        distanceK(root.left, root, map);
        distanceK(root.right, root, map);
    }

    public int candy(int[] ratings) {
        int[] a = new int[ratings.length];
        int[] b = new int[ratings.length];
        Arrays.fill(a, 1);
        Arrays.fill(b, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                a[i] = a[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                b[i] = b[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < ratings.length; i++) {
            ans += Math.max(a[i], b[i]);
        }
        return ans;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            if (ints.length - 1 >= 0 && ints[ints.length - 1] < target) {
                continue;
            }
            for (int anInt : ints) {
                if (anInt == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public String simplifyPath(String path) {
        String[] split = path.split("/+");
        LinkedList<String> stack = new LinkedList<>();
        for (int i = 1; i < split.length; i++) {
            if ("..".equals(split[i])) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!".".equals(split[i])) {
                stack.push(split[i]);
            }
        }
        Collections.reverse(stack);
        return "/" + String.join("/", stack);
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reverse = reorderListReverse(slow.next);
        slow.next = null;
        reorderListMerge(head, reverse);
    }

    private void reorderListMerge(ListNode a, ListNode b) {
        ListNode start = new ListNode(0), cur = start;
        while (a != null && b != null) {
            cur.next = a;
            cur = cur.next;
            a = a.next;
            cur.next = b;
            cur = cur.next;
            b = b.next;
        }
        cur.next = a != null ? a : b;
        a = start.next;
    }

    private ListNode reorderListReverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reorderListReverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        char[] c = s.toCharArray();
        for (int i = 1; i < c.length; i++) {
            int sum = (c[i - 1] - '0') * 10 + c[i] - '0';
            if (sum == 10 || sum == 20) {
                dp[i] = i - 2 < 0 ? 1 : dp[i - 2];
            } else if (sum > 10 && sum <= 26) {
                dp[i] = dp[i - 1] + (i - 2 < 0 ? 1 : dp[i - 2]);
            } else if (sum < 10 && sum > 0) {
                dp[i] = dp[i - 1];
            } else if (sum > 26 && sum % 10 != 0) {
                dp[i] = dp[i - 1];
            }
        }
        return dp[s.length() - 1];
    }

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                dp[i][j] = Math.min((i - 1 < 0 ? Integer.MAX_VALUE : dp[i - 1][j]), (j - 1 < 0 ? Integer.MAX_VALUE : dp[i][j - 1])) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int maximumSwap(int num) {
        char[] s = String.valueOf(num).toCharArray();
        char[] r = s.clone();
        Arrays.sort(r);
        int i = 0, j = s.length - 1;
        while (i < s.length && s[i] == r[j]) {
            i++;
            j--;
        }
        if (i == s.length) {
            return num;
        }
        int k = s.length - 1;
        while (s[k] != r[j]) {
            k--;
        }
        char temp = s[i];
        s[i] = s[k];
        s[k] = temp;
        return Integer.parseInt(String.valueOf(s));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int i = 0, j = 0;
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();
        while (i < nums.length) {
            while (!deque.isEmpty() && nums[deque.peekFirst()] <= nums[i]) {
                deque.pollFirst();
            }
            deque.addFirst(i);
            if (i >= k - 1) {
                if (!deque.isEmpty() && deque.peekLast() == i - k) {
                    deque.pollLast();
                }
                ans[j++] = nums[deque.peekLast()];
            }
            i++;
        }
        return ans;
    }

    public boolean canMeasureWater(int x, int y, int z) {
        LinkedList<int[]> linkedList = new LinkedList<>();
        Set<Long> visited = new HashSet<>();
        linkedList.add(new int[]{0, 0});
        visited.add(0L);
        while (!linkedList.isEmpty()) {
            int[] pop = linkedList.pop();
            if (pop[0] == z || pop[1] == z || pop[0] + pop[1] == z) {
                return true;
            }
            canMeasureWaterHelper(0, pop[1], linkedList, visited);
            canMeasureWaterHelper(x, pop[1], linkedList, visited);
            canMeasureWaterHelper(pop[0], 0, linkedList, visited);
            canMeasureWaterHelper(pop[0], y, linkedList, visited);
            canMeasureWaterHelper(Math.min(pop[0] + pop[1], x), pop[0] + pop[1] < x ? 0 : pop[0] + pop[1] - x, linkedList, visited);
            canMeasureWaterHelper(pop[0] + pop[1] < y ? 0 : pop[0] + pop[1] - y, Math.min(pop[0] + pop[1], y), linkedList, visited);
        }
        return false;
    }

    private void canMeasureWaterHelper(int curX, int curY, LinkedList<int[]> linkedList, Set<Long> visited) {
        long hash = curX + ((long) curY << 32);
        if (!visited.contains(hash)) {
            linkedList.add(new int[]{curX, curY});
            visited.add(hash);
        }
    }

    public int maxProfit(int[] prices) {
        int[][][] dp = new int[prices.length][3][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 2; j > 0; j--) {
                if (i == 0) {
                    dp[i][j][1] = -prices[i];
                    dp[i][j][0] = 0;
                } else {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                }
            }
        }
        return prices.length - 1 < 0 ? 0 : dp[prices.length - 1][2][0];
    }

    public RandomNode copyRandomList(RandomNode head) {
        HashMap<RandomNode, RandomNode> map = new HashMap<>();
        RandomNode start = new RandomNode(0), cur = start, h = head;
        while (h != null) {
            cur.next = new RandomNode(h.val);
            cur = cur.next;
            map.put(h, cur);
            h = h.next;
        }
        cur = start.next;
        h = head;
        while (h != null) {
            cur.random = map.get(h.random);
            cur = cur.next;
            h = h.next;
        }
        return start.next;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        lexicalOrderDf(n, 0, ans);
        return ans;
    }

    private void lexicalOrderDf(int n, int m, List<Integer> ans) {
        if (m > n) {
            return;
        }
        if (m != 0) {
            ans.add(m);
        }
        for (int i = m == 0 ? 1 : 0; i < 10; i++) {
            lexicalOrderDf(n, m * 10 + i, ans);
        }
    }

    /**
     * 输入:
     * formula = "H2O"
     * 输出: "H2O"
     * 解释:
     * 原子的数量是 {'H': 2, 'O': 1}。
     * <p>
     * 输入:
     * formula = "Mg(OH)2"
     * 输出: "H2MgO2"
     * 解释:
     * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
     * <p>
     * 输入:
     * formula = "K4(ON(SO3)2)2"
     * 输出: "K4N2O14S4"
     * 解释:
     * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-atoms
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param formula
     * @return
     */
    public String countOfAtoms(String formula) {
        HashMap<String, Integer> map = countOfAtomsHelper(formula);
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key);
            sb.append(map.get(key) == 1 ? "" : map.get(key));
        }
        return sb.toString();
    }

//    public HashMap<String, Integer> countOfAtomsI(String formula) {
//        HashMap<String, Integer> map = new HashMap<>();
//        LinkedList<Character> deque = new LinkedList<>();
//        for (int i = 0; i < formula.length(); i++) {
//            char c = formula.charAt(i);
//            if (c != ')') {
//                deque.push(c);
//            } else {
//                Character p = deque.pop();
//                StringBuilder sb = new StringBuilder();
//                while (p != '(') {
//                    sb.append(c);
//                    p = deque.pop();
//                }
//                HashMap<String, Integer> temp = countOfAtomsHelper(sb.reverse().toString());
//                int j = ++i;
//                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
//                    i++;
//                }
//                for (String s : temp.keySet()) {
//                    map.put(s, map.getOrDefault(s, 0) + map.get(s) * Integer.parseInt(formula.substring(j,i)));
//                }
//            }
//        }
//        List<String> keys = new ArrayList<>(map.keySet());
//        Collections.sort(keys);
//        StringBuilder sb = new StringBuilder();
//        for (String key : keys) {
//            sb.append(key);
//            sb.append(map.get(key) == 1 ? "" : map.get(key));
//        }
//        return sb.toString();
//    }

    private HashMap<String, Integer> countOfAtomsHelper(String formula) {
        if (formula.length() == 0) {
            return null;
        }
        int i = formula.indexOf('(');
        HashMap<String, Integer> map = new HashMap<>();
        if (i == -1) {
            int m = 0;
            while (m < formula.length()) {
                int n = m + 1;
                while (n < formula.length() && Character.isLowerCase(formula.charAt(n))) {
                    n++;
                }
                String key = formula.substring(m, n);
                if (n < formula.length()) {
                    if (Character.isUpperCase(formula.charAt(n))) {
                        map.put(key, map.getOrDefault(key, 0) + 1);
                        m = n;
                    } else {
                        int k = n + 1;
                        while (k < formula.length() && Character.isDigit(formula.charAt(k))) {
                            k++;
                        }
                        map.put(key, map.getOrDefault(key, 0) + Integer.parseInt(formula.substring(n, k)));
                        m = k;
                    }
                } else {
                    map.put(key, map.getOrDefault(key, 0) + 1);
                    m = n;
                }
            }
            return map;
        }
        int j = i + 1;
        while (formula.charAt(j) != ')') {
            j++;
        }
        HashMap<String, Integer> temp1 = countOfAtomsHelper(formula.substring(0, i));
        for (String s : temp1.keySet()) {
            map.put(s, map.getOrDefault(s, 0) + temp1.get(s));
        }
        HashMap<String, Integer> temp2 = countOfAtomsHelper(formula.substring(i + 1, j));
        int k = j + 1;
        while (k < formula.length() && Character.isDigit(formula.charAt(k))) {
            k++;
        }
        for (String s : temp2.keySet()) {
            map.put(s, map.getOrDefault(s, 0) + temp2.get(s) * Integer.parseInt(formula.substring(j + 1, k)));
        }
        HashMap<String, Integer> temp3 = countOfAtomsHelper(formula.substring(k));
        for (String s : temp3.keySet()) {
            map.put(s, map.getOrDefault(s, 0) + temp3.get(s));
        }
        return map;
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
        LinkedList<int[]> linkedList = new LinkedList<>();
        for (int[] p : people) {
            linkedList.add(p[1], p);
        }
        return linkedList.toArray(new int[people.length][2]);
    }

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int ans = 0, m = heightMap.length, n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.add(new int[]{i, j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            for (int[] d : new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}}) {
                int i = p[0] + d[0];
                int j = p[1] + d[1];
                if (i >= 0 && i < m && j >= 0 && j < n && !visit[i][j]) {
                    ans += Math.max(p[2] - heightMap[i][j], 0);
                    pq.add(new int[]{i, j, Math.max(p[2], heightMap[i][j])});
                    visit[i][j] = true;
                }
            }
        }
        return ans;
    }

    public int numberOfPatterns(int m, int n) {
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(1, 3, 7, 9));
        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(2, 4, 6, 8));
        boolean[] used = new boolean[10];
        int ans = 0;
        for (int i = m; i <= n; i++) {
            ans += numberOfPatternsDfs(i, 0, -1, used, set1, set2);
        }
        return ans;
    }

    private int numberOfPatternsDfs(int length, int curLength, int last, boolean[] used, HashSet<Integer> set1, HashSet<Integer> set2) {
        if (length == curLength) {
            return 1;
        }
        int ans = 0;
        for (int i = 1; i <= 9; i++) {
            if (numberOfPatternsValid(i, last, used, set1, set2)) {
                used[i] = true;
                ans += numberOfPatternsDfs(length, curLength + 1, i, used, set1, set2);
                used[i] = false;
            }
        }
        return ans;
    }

    private boolean numberOfPatternsValid(int i, int last, boolean[] used, HashSet<Integer> set1, HashSet<Integer> set2) {
        if (last == -1) {
            return true;
        }
        if (used[i]) {
            return false;
        }
        if (set1.contains(i) && set1.contains(last) && !used[(i + last) / 2]) {
            return false;
        }
        if (set2.contains(i) && set2.contains(last) && ((i - 1) % 3 == (last - 1) % 3 || (i - 1) / 3 == (last - 1) / 3) && !used[(i + last) / 2]) {
            return false;
        }
        return true;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    public int maxProfitIV(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int m = prices.length;
        int[][][] dp = new int[m][(k <= m / 2 ? k : 1) + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[0];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - (k <= m / 2 ? 1 : 0)][0] - prices[i]);
                }
            }
        }
        return dp[m - 1][k][0];
    }

    public int shortestSubarray(int[] A, int K) {
        int[] preSum = new int[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            preSum[i + 1] = preSum[i] + A[i];
        }
        int ans = Integer.MAX_VALUE, j = 0;
        LinkedList<Integer> deque = new LinkedList<>();
        while (j < preSum.length) {
            while (!deque.isEmpty() && preSum[deque.peekLast()] > preSum[j]) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && preSum[j] - preSum[deque.peek()] >= K) {
                ans = Math.min(ans, j - deque.poll());
            }
            deque.add(j);
            j++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        long ans = 1;
        while (n > 4) {
            ans *= 3;
            ans %= 1000000007;
            n -= 3;
        }
        return (int) ((n * ans) % 1000000007);
    }
}
