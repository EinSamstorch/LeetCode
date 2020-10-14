/*
 * @lc app=leetcode.cn id=485 lang=java
 *
 * [485] 最大连续1的个数
 *
 * https://leetcode-cn.com/problems/max-consecutive-ones/description/
 *
 * algorithms
 * Easy (56.79%)
 * Likes:    130
 * Dislikes: 0
 * Total Accepted:    56.7K
 * Total Submissions: 99.8K
 * Testcase Example:  '[1,0,1,1,0,1]'
 *
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * 
 * 示例 1:
 * 
 * 
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 
 * 
 * 注意：
 * 
 * 
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 * 
 * 
 */

// @lc code=start
class Solution {
    // 开始自己写的失败产品
    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxLen = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int curLen = 1;
            if (nums[i] == 1 && nums[i + 1] == 1) {
                curLen += 1;
            }
            maxLen = Math.max(curLen, maxLen);
        }
        return maxLen;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // 记录连续1的个数
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                count = 0;
            }
        }
        // 最后一次连续序列在循环中无法比较，所以要在循环外比较
        return Math.max(count, maxCount);
    } 
}
// @lc code=end

