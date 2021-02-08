/*
 * @lc app=leetcode.cn id=188 lang=java
 *
 * [188] 买卖股票的最佳时机 IV
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 *
 * algorithms
 * Hard (29.26%)
 * Likes:    163
 * Dislikes: 0
 * Total Accepted:    12.5K
 * Total Submissions: 42.6K
 * Testcase Example:  '2\n[2,4,1]'
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 
 * 示例 1:
 * 
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 
 * 
 * 示例 2:
 * 
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4
 * 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxProfit1(int k, int[] prices) {
        int len = prices.length;
        if (k == 0 || len < 2) {
            return 0;
        }
        // 特殊判断，因为交易一次需要2天，如果k > len / 2, 相当于没有限制
        // 转换为122题，使用贪心算法
        if (k > len / 2) {
            return greedy(prices);
        }

        // 状态转移方程里下标有-1的时候，为了防止数组下标越界，多开一行，因此第一维长度len + 1
        // 第二维表示交易次数，从0开始，因为第二维长度k + 1
        // 第3维表示是否持股，0：不持股 1：持股
        int[][][] dp = new int[len + 1][k + 1][2];

        // 初始化：把持股的部分都设置为一个较小的负值
        // 注意：如果使用默认值0，状态转移的过程中会做出错误的决策
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }

        // 注意：i和j都有1个位置的偏移
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i - 1]);
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i - 1]);
            }
        }
        return dp[len][k][0];
    }

    private int greedy(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }


    public int maxProfit(int K, int[] prices) {// 这里悄咪咪把小k换成了大K，便于后续索引赋值
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        // 特殊判断，因为交易一次需要2天，如果k > len / 2, 相当于没有限制
        // 转换为122题，使用贪心算法
        if (K > n / 2) {
            return greedy(prices);
        }

        /* dp定义：dp[i][j][k]代表 第i天交易了j次时的最大利润，其中k代表当天是否持有股票，0不持有，1持有 */
        int[][][] dp = new int[n][K + 1][2];
        for (int j = 0; j <= K; j++) {
            dp[0][j][1] = -prices[0];
        }

        /*
         * 状态方程： dp[i][k][0]，当天不持有股票时，看前一天的股票持有情况 
         * dp[i][k][1]，当天持有股票时，看前一天的股票持有情况
         */
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= K; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][K][0];
    }

    
}
// @lc code=end
