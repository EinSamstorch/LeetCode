/*
 * @lc app=leetcode.cn id=518 lang=java
 *
 * [518] 零钱兑换 II
 *
 * https://leetcode-cn.com/problems/coin-change-2/description/
 *
 * algorithms
 * Medium (56.36%)
 * Likes:    300
 * Dislikes: 0
 * Total Accepted:    39K
 * Total Submissions: 69.2K
 * Testcase Example:  '5\n[1,2,5]'
 *
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 
 * 
 * 示例 2:
 * 
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 
 * 
 * 示例 3:
 * 
 * 输入: amount = 10, coins = [10] 
 * 输出: 1
 * 
 * 
 * 
 * 
 * 注意:
 * 
 * 你可以假设：
 * 
 * 
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * 
 * 
 */

// @lc code=start
class Solution {
    // 转换成背包问题
    public int change1(int amount, int[] coins) {
        int n = coins.length;
        // 若只使用前i个物品，当背包容量为j时，有dp[i][j]种方法可以装满背包。
        // 若只使用coins中的前i个硬币的面值，若想凑出金额j，有dp[i][j]种凑法。
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++) {
            // 如果凑出目标金额为0，那么无为而治就是唯一的一种凑法
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // 如果把第i个物品装进了背包
                // 比如说，你想用面值为 2 的硬币凑出金额 5，那么如果你知道了凑出金额 3 的方法，再加上一枚面额为 2 的硬币，不就可以凑出 5 了嘛。
                // 由于i是从 1 开始的，所以coins的索引是i-1时表示第i个硬币的面值
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    // 如果你不把这第i个物品装入背包，也就是说你不使用coins[i]这个面值的硬币，
                    // 那么凑出面额j的方法数dp[i][j]应该等于dp[i-1][j]，继承之前的结果。
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1; // base case
        for (int i = 0; i < n; i++)
            for (int j = 1; j <= amount; j++)
                if (j - coins[i] >= 0)
                    dp[j] = dp[j] + dp[j-coins[i]];
    
        return dp[amount];
    }
}
// @lc code=end

