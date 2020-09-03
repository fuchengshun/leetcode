package com.example.lintcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LintCode {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = nums[i];
            dp[i][i][1] = 0;
        }
//        int[] sum = new int[n + 1];
//        for (int i = 0, temp = 0; i < n; i++) {
//            temp += nums[i];
//            sum[i + 1] = temp;
//        }
        for (int k = 1; k < n; k++) {
            for (int i = 0, j = k; i < n && j < n; i++,j++) {
                dp[i][j][0] = Math.max(dp[i + 1][j][1] + nums[i], dp[i][j - 1][1] + nums[j]);
//                dp[i][j][1] = sum[j + 1] - sum[i] - dp[i][j][0];
                dp[i][j][1] = Math.min(dp[i + 1][j][0],dp[i][j-1][0]);
            }
        }
        return dp[0][n - 1][0] >= dp[0][n - 1][1];
    }
}
