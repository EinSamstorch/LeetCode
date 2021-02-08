/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 * algorithms
 * Easy (52.56%)
 * Likes:    772
 * Dislikes: 0
 * Total Accepted:    135.7K
 * Total Submissions: 257.9K
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 
 * 注意你不能在买入股票前卖出股票。
 * 
 * 示例 1:
 * 
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * ⁠    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 
 * 
 * 示例 2:
 * 
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 穷举，固定买入时间，穷举后面每一天卖出的时间
     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans = Math.max(ans, prices[j] - prices[i]);
            }
        }
        return ans;
    }

    // 固定卖出时间，穷举买入时间，可以少一个for循环
    public int maxProfit2(int[] prices) {
        int res = 0;
        int curMin = prices[0];

        for (int sell = 0; sell < prices.length; sell++) {
            curMin = Math.min(curMin, prices[sell]);
            res = Math.max(res, prices[sell] - curMin);
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数
        int[][] dp = new int[len][2];
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 只允许交易一次，因此手上的现金数就是当天股价的相反数
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];

    }
}
// @lc code=end

