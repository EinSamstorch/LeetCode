/*
 * @lc app=leetcode.cn id=1004 lang=java
 *
 * [1004] 最大连续1的个数 III
 *
 * https://leetcode-cn.com/problems/max-consecutive-ones-iii/description/
 *
 * algorithms
 * Medium (61.01%)
 * Likes:    248
 * Dislikes: 0
 * Total Accepted:    48.5K
 * Total Submissions: 79.4K
 * Testcase Example:  '[1,1,1,0,0,0,1,1,1,1,0]\n2'
 *
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释： 
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 
 * 示例 2：
 * 
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestOnes1(int[] nums, int K) {
        int left = 0, right = 0;
        // sum维护数组中有几个1
        int sum = 0;

        while (right < nums.length) {
            sum += nums[right];
            right++;
            if (right - left > sum + K) {
                sum -= nums[left];
                left++;
            }
        }
        return right - left;
    }

    public int longestOnes(int[] nums, int K) {
        int left = 0, right = 0;
        int maxWindow = 0;
        // 维护0的个数
        int zeroCount = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            right++;
            while (zeroCount > K) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxWindow = Math.max(maxWindow, right - left);
        }
        return maxWindow;
    }
}
// @lc code=end

