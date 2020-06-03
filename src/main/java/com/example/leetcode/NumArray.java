package com.example.leetcode;

class NumArray {
    private int[] nums;
    private int[] preSum;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.preSum = new int[nums.length];
        int sum = 0;
        for (int k = 0; k < nums.length; k++) {
            sum += nums[k];
            preSum[k] = sum;
        }
    }

    public int sumRange(int i, int j) {
        return preSum[j] - preSum[i] + nums[i];
    }
}
