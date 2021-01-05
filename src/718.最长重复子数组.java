/*
 * @lc app=leetcode.cn id=718 lang=java
 *
 * [718] 最长重复子数组
 *
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/description/
 *
 * algorithms
 * Medium (54.68%)
 * Likes:    368
 * Dislikes: 0
 * Total Accepted:    49.3K
 * Total Submissions: 90.1K
 * Testcase Example:  '[1,2,3,2,1]\n[3,2,1,4,7]'
 *
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 动态规划
     */
    public int findLength1(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int ans = 0;
        int dp[][] = new int[m + 1][n + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n -1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1: 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
}
// @lc code=end

