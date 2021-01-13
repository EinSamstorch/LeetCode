/*
 * @lc app=leetcode.cn id=643 lang=java
 *
 * [643] 子数组最大平均数 I
 *
 * https://leetcode-cn.com/problems/maximum-average-subarray-i/description/
 *
 * algorithms
 * Easy (39.35%)
 * Likes:    116
 * Dislikes: 0
 * Total Accepted:    21.4K
 * Total Submissions: 54.5K
 * Testcase Example:  '[1,12,-5,-6,50,3]\n4'
 *
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * 
 * 示例 1:
 * 
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 
 * 
 * 
 * 
 * 注意:
 * 
 * 
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 自己只配想暴力方法了
     */
    public double findMaxAverage1(int[] nums, int k) {
        double maxAverage = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length - k + 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < i + k && j < nums.length; j++) {
                 sum += nums[j];
            }
            maxAverage = Math.max(maxAverage, sum / (double) k);
        }
        return maxAverage;
    }

    /**
     * 滑动窗口的算法
     */
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0, maxSum = 0;
        
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        maxSum = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(sum, maxSum);
        }

        return maxSum / k;
    }
}
// @lc code=end

