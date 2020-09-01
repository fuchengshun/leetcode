package com.example.leetcode;

import java.util.*;

public class IOTest {
    public static void main(String[] ars) {
        Point[] Edge=new Point[6];
        Edge[0]=new Point(7,2);
        Edge[1]=new Point(6,1);
        Edge[2]=new Point(5,2);
        Edge[3]=new Point(1,2);
        Edge[4]=new Point(4,6);
        Edge[5]=new Point(6,3);
        int sovle = new IOTest().solve(7,Edge , new int[]{0,0,1,0,1,0,0});
    }

    public int[] sovle(int[] a, int n, int m) {
        // write code here
        int[] b = a.clone();
        Arrays.sort(b);
        int i = -1, j = -1;
        for (int k = 0; k < a.length; k++) {
            if (a[k] == b[a.length - m]) {
                i = k;
            }
            if (a[k] == b[a.length - n]) {
                j = k;
            }
        }
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        return a;
    }

    public int solve(int n, Point[] Edge, int[] f) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (Point p : Edge) {
            List<Integer> v = map.getOrDefault(p.x, new ArrayList<>());
            v.add(p.y);
            map.put(p.x, v);
            List<Integer> value = map.getOrDefault(p.y, new ArrayList<>());
            value.add(p.x);
            map.put(p.y, value);
        }
        boolean[] visit = new boolean[n + 1];
        return solveDfs(map, visit, f, 1, 2);
        // write code here
    }

    private int solveDfs(HashMap<Integer, List<Integer>> map, boolean[] visit, int[] f, int i, int k) {
        visit[i] = true;
        if (f[i - 1] == 1) {
            k--;
        }
        if (k < 0) {
            return 0;
        }
        int ans = 0;
        List<Integer> list = map.get(i);
        boolean allVisit = true;
        for (Integer m : list) {
            if (!visit[m]) {
                allVisit = false;
                ans += solveDfs(map, visit, f, m, k);
            }
        }
        if (allVisit) {
            return 1;
        }
        return ans;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
