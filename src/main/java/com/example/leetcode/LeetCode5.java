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

    public int minDays(int n, Map<Integer, Integer> map) {
        if (map.get(n) != null) {
            return map.get(n);
        }
        if (n == 1) {
            return 1;
        } else if (n == 2 || n == 3) {
            return 2;
        }
        int ans = minDays(n - 1, map) + 1;
        if (n % 2 == 0) {
            ans = Math.min(ans, minDays(n / 2, map) + 1);
        }
        if (n % 3 == 0) {
            ans = Math.min(ans, minDays(n / 3, map) + 1);
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
