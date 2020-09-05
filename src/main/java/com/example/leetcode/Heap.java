package com.example.leetcode;

import java.util.Arrays;

public class Heap {
    int[] a=new int[10];
    int index = -1;

    public void build(int[] b) {
        for (int i : b) {
            push(i);
        }
    }

    public void sort(int[] array) throws Exception {
        build(array);
        for (int i = 0; i < array.length && !isEmply(); i++) {
            array[i] = pop();
        }
    }

    public void push(int n) {
        if (++index >= a.length) {
            a = Arrays.copyOf(a, Math.max(a.length * 2, 10));
        }
        a[index] = n;
        heapifyUp(index);
    }

    public int pop() throws Exception {
        if (isEmply()) {
            throw new Exception();
        }
        int ans = a[0];
        a[0] = a[index--];
        heapifyDown(0);
        return ans;
    }

    // i left index right
    private void heapifyDown(int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        if (index > 0 && index >= left && a[i] > (index < right ? a[left] : Math.min(a[left], a[right]))) {
            int j = index < right ? left : a[left] < a[right] ? left : right;
            swap(i, j);
            heapifyDown(j);
        }
    }

    private void heapifyUp(int i) {
        int parent = (i - 1) / 2;
        if (i > 0 && a[parent] > a[i]) {
            swap(parent, i);
            heapifyUp(parent);
        }
    }

    private void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public boolean isEmply() {
        return index == -1;
    }
}
