import java.nio.file.attribute.DosFileAttributeView;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=343 lang=java
 *
 * [343] 整数拆分
 *
 * https://leetcode-cn.com/problems/integer-break/description/
 *
 * algorithms
 * Medium (59.55%)
 * Likes:    495
 * Dislikes: 0
 * Total Accepted:    83.5K
 * Total Submissions: 140.2K
 * Testcase Example:  '2'
 *
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 
 * 示例 1:
 * 
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 
 * 示例 2:
 * 
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * 
 */

// @lc code=start
class Solution {
    private int[] memo;
    // 记忆化递归
    public int integerBreak1(int n) {
        memo = new int[n + 1];
        // -1代表还没有计算出来
        Arrays.fill(memo, -1);
        return dfs(n);
    }

    private int dfs(int n) {
        // base case
        if (n == 1) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, Math.max(i * (n - i), i * dfs(n - i)));
        }
        memo[n] = res;
        return res;
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 其中一个加法因子至少是1，j最多是i - 1
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
// @lc code=end

