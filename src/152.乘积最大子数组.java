/*
 * @lc app=leetcode.cn id=152 lang=java
 *
 * [152] 乘积最大子数组
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/description/
 *
 * algorithms
 * Medium (40.25%)
 * Likes:    786
 * Dislikes: 0
 * Total Accepted:    97.5K
 * Total Submissions: 241.2K
 * Testcase Example:  '[2,3,-2,4]'
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 *
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */

// @lc code=start
class Solution {

    /**
     * 暴力解法
     */
    public int maxProduct1(int[] nums) {
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int sum = 1;
            for (int j = i; j < nums.length; j++) {
                sum *= nums[j];
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }

    /**
     * 动态规划 最主要的是区分这题是有可能有负数的
     */
    public int maxProduct2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        // dp[i][0]: 以nums[i]结尾的连续子数组的最小值
        // dp[i][1]: 以nums[i]结尾的连续子数组的最大值
        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1]);
            } else {
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][1]);
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][0]);
            }
        }

        int res = dp[0][1];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i][1]);
        }
        return res;

    }

    /**
     * 动态规划 滚动数组优化 使用常数空间
     */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int preMax = nums[0];
        int preMin = nums[0];

        // 滚动变量
        int curMax;
        int curMin;

        int res = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                curMin = Math.min(nums[i], nums[i] * preMin);
                curMax = Math.max(nums[i], nums[i] * preMax);
            } else {
                curMin = Math.min(nums[i], nums[i] * preMax);
                curMax = Math.max(nums[i], nums[i] * preMin);
            }
            res = Math.max(res, curMax);
            // 赋值滚动变量
            preMax = curMax;
            preMin = curMin;
        }

        return res;

    }

}
// @lc code=end

