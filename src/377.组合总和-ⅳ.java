import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode.cn id=377 lang=java
 *
 * [377] 组合总和 Ⅳ
 *
 * https://leetcode-cn.com/problems/combination-sum-iv/description/
 *
 * algorithms
 * Medium (44.34%)
 * Likes:    308
 * Dislikes: 0
 * Total Accepted:    26.4K
 * Total Submissions: 59.5K
 * Testcase Example:  '[1,2,3]\n4'
 *
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 
 * 题目数据保证答案符合 32 位整数范围。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [9], target = 3
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * nums 中的所有元素 互不相同
 * 1 
 * 
 * 
 * 
 * 
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 * 
 */

// @lc code=start
class Solution {

    /**
     * 动态规划
     * 这里状态定义就是题目要求的，并不难，状态转移方程要动点脑子，也不难：
     * 状态转移方程：dp[i]= dp[i - nums[0]] + dp[i - nums[1]] + dp[i - nums[2]] + ... （当 [] 里面的数 >= 0）
     * 特别注意：dp[0] = 1，表示，如果那个硬币的面值刚刚好等于需要凑出的价值，这个就成为 1 种组合方案
     * 再举一个具体的例子：nums=[1, 3, 4], target=7;
     * dp[7] = dp[6] + dp[4] + dp[3]
     * 即：7 的组合数可以由三部分组成，1 和 dp[6]，3 和 dp[4], 4 和dp[3];
     *
     */
    public int combinationSum4_1(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    /**
     * 暴力递归
     * 超时
     */
    public int combinationSum4_2(int[] nums, int target) {
        return dfs1(nums, target);
    }

    private int dfs1(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int num : nums) {
            res += dfs1(nums, target - num);
        }
        return res;
    }

    /**
     * 带备忘录的递归
     */
    public int combinationSum4(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return dfs(nums, target, memo);
    }

    private int dfs(int[] nums, int target, int[] memo) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }
        if (memo[target] != -1) {
            return memo[target];
        }
        int res = 0;
        for (int num : nums) {
            res += dfs(nums, target - num, memo);
        }
        memo[target] = res;
        return res;

    }
}
// @lc code=end

