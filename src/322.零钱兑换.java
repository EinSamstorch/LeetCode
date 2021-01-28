import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (42.39%)
 * Likes:    1038
 * Dislikes: 0
 * Total Accepted:    175.6K
 * Total Submissions: 414.3K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 
 * 你可以认为每种硬币的数量是无限的。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3 
 * 解释：11 = 5 + 5 + 1
 * 
 * 示例 2：
 * 
 * 
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 
 * 示例 3：
 * 
 * 
 * 输入：coins = [1], amount = 0
 * 输出：0
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：coins = [1], amount = 1
 * 输出：1
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：coins = [1], amount = 2
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int coinChange1(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        // 数组大小为amount + 1， 值也是，因为amount金额的钱，
        // 最多只需要amount个硬币（全用一元的），所以初始化为amount+1就相当于无穷大
        Arrays.fill(dp, max);
        // base case
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                // 子问题无解，跳过
                if (i - coin < 0) {
                    continue;
                } 
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];

    }

    int[] memo;
    // 带备忘录的递归
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        memo = new int[amount];

        return findWay(coins, amount);
    }

    // memo[n]表示钱币n可以被换取的最少的硬币数，不能换取就为-1
    // findWay函数的目的是为了找到amount数量的零钱可以兑换的最少硬币数量，返回其值int
    public int findWay(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        // 记忆化的处理，memo[n]已经赋予了值，就不用继续下面的循环
        // 直接的返回memo[n]的最优值
        if (memo[amount -1] != 0) {
            return memo[amount - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = findWay(coins, amount - coins[i]);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        memo[amount - 1] = min == Integer.MAX_VALUE ? -1 : min;
        return memo[amount - 1];
    }
}
// @lc code=end

