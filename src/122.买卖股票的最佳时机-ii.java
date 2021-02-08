/*
 * @lc app=leetcode.cn id=122 lang=java
 *
 * [122] 买卖股票的最佳时机 II
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 *
 * algorithms
 * Easy (57.75%)
 * Likes:    625
 * Dislikes: 0
 * Total Accepted:    127.4K
 * Total Submissions: 219.7K
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 
 * 示例 1:
 * 
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 
 * 
 * 示例 2:
 * 
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4
 * 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 
 * 
 * 示例 3:
 * 
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 
 */

// @lc code=start
class Solution {
    private int res = 0;
    public int maxProfit1(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        dfs (prices, 0, 0, res);
        return res;
    }

    /**
     *  深度优先搜索
     * @param prices 股价数组
     * @param index 当前是第几天，从0开始
     * @param status 0表示不持有股票，1表示持有股票
     * @param profit 当前收益
     */
    private void dfs(int[] prices, int index, int status, int profit) {
        if (index == prices.length) {
            res = Math.max(res, profit);
            return;
        }
        dfs(prices, index + 1, status, profit);
        if (status == 0) {
            // 可以尝试转向1
            dfs(prices, index + 1, 1, profit - prices[index]);
        } else {
            // 可以长是转向0
            dfs(prices, index + 1, 0, profit + prices[index]);
        }
    }

    public int maxProfit2(int[] prices) {
        int len = prices.length;
        // 特判
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[len][2];
        // dp[i][0]下标为i这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1]下标为i这天结束的时候，持股，手上拥有的现金数
        // 初始化：不持股显然为0，持股就需要减去第1天（下标为0）的股价
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第二天开始遍历
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len -1][0];
    }

    /**
     * 贪心算法
     * 因为交易次数不受限，所以只要将所有的上坡收集起来，就是最大利润了
     */
    public int maxProfit3(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        // cash 持有现金
        // hold 持有股票
        // 状态转移：cash -> hold -> cash -> hold ...
        int[] cash = new int[len];
        int[] hold = new int[len];
        cash[0] = 0;
        hold[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            cash[i] = Math.max(cash[i -1], hold[i -1] + prices[i]);
            hold[i] = Math.max(hold[i -1], cash[i - 1] - prices[i]);
        }
        return cash[len - 1];
    }

}
// @lc code=end

