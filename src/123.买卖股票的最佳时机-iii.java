/*
 * @lc app=leetcode.cn id=123 lang=java
 *
 * [123] 买卖股票的最佳时机 III
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 *
 * algorithms
 * Hard (41.40%)
 * Likes:    305
 * Dislikes: 0
 * Total Accepted:    23.7K
 * Total Submissions: 57.1K
 * Testcase Example:  '[3,3,5,0,0,3,1,4]'
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 
 * 示例 1:
 * 
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
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
 * 示例 3:
 * 
 * 输入: [7,6,4,3,1] 
 * 输出: 0 
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 
 */

// @lc code=start
class Solution {
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        
        // dp[i][j][k] 表示在 [0, i] 区间里（状态具有前缀性质），交易进行了 j 次，并且状态为 k 时我们拥有的现金数。
        // j = 0 表示没有交易发生；
        // j = 1 表示此时已经发生了 1 次买入股票的行为；
        // j = 2 表示此时已经发生了 2 次买入股票的行为
        // k = 0 表示当前不持股，不代表当天要买入或卖出股票！！！！
        // k = 1 表示当前持股
        int[][][] dp = new int [len][3][2];
        // 第三维规定了必须持股，因此是-prices[0]
        dp[0][1][1] = -prices[0];
        // 交易了两次之后还持股，这是不可能的，所以初始化为负无穷
        dp[0][2][1] = Integer.MIN_VALUE;

        for (int i = 1; i < len; i++) {
            // 转移顺序，先持股，再卖出
            dp[i][1][1] = Math.max(dp[i -1][1][1], -prices[i]);
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
        }

        return Math.max(dp[len - 1][1][0], dp[len - 1][2][0]);
    }

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        
       // dp[i][j]中表示第i天，j为[0-4]五个状态，dp[i][j]表示第i天，状态j所剩的最大现金
       // 注意dp[i][1]表示第i天，买入股票的状态，并不是说一定要第i天买入股票
       // j五个状态，0 无操作；1 第一次买入； 2 第一次卖出； 3 第二次买入； 4 第二次卖出

       int[][] dp = new int[len][5];
       dp[0][1] = -prices[0];
       dp[0][3] = -prices[0];
       for (int i = 1; i < len ; i++) {
           dp[i][0] = dp[i - 1][0];
           dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
           dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
           dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
           dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
       }
       return dp[len - 1][4];
    }

    
}
// @lc code=end

