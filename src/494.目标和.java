import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=494 lang=java
 *
 * [494] 目标和
 *
 * https://leetcode-cn.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (44.63%)
 * Likes:    536
 * Dislikes: 0
 * Total Accepted:    61.2K
 * Total Submissions: 137.1K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或
 * -中选择一个符号添加在前面。
 * 
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * 一共有5种方法让最终目标和为3。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 * 
 * 
 */

// @lc code=start
class Solution {
    int count = 0;
    // 使用递归，枚举出所有的可能
    public int findTargetSumWays1(int[] nums, int target) {
        calculate(nums, 0, target);
        return count;
    }

    private void calculate(int[] nums, int i, int rest) {
        if (i == nums.length) {
            if (rest == 0) {
                count++;
            }
        } else {
            calculate(nums, i + 1, rest + nums[i]);
            calculate(nums, i + 1, rest - nums[i]);
        }
    }

    // 使用备忘录进行优化
    public int findTargetSumWays2(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        return dp(nums, 0, target);
    }
    // 备忘录
    HashMap<String, Integer> memo = new HashMap<>();
    private int dp(int[] nums, int i, int rest) {
        // base case
        if (i == nums.length) {
            if (rest == 0) {
                return 1;
            }
            return 0;
        }
        // 把它两转成字符串才能作为哈希表的值
        // 之所以这个形式，是因为同一个i，有可能有不同的值
        String key = i + "," + rest;
        // 避免重复计算
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // 还是穷举
        int result = dp(nums, i + 1, rest - nums[i]) + dp(nums, i + 1, rest + nums[i]);
        // 计入备忘录
        memo.put(key, result);

        return result;
    }

}
// @lc code=end

