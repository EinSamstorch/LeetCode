import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=561 lang=java
 *
 * [561] 数组拆分 I
 *
 * https://leetcode-cn.com/problems/array-partition-i/description/
 *
 * algorithms
 * Easy (72.24%)
 * Likes:    194
 * Dislikes: 0
 * Total Accepted:    49.4K
 * Total Submissions: 68.4K
 * Testcase Example:  '[1,4,3,2]'
 *
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到
 * n 的 min(ai, bi) 总和最大。
 * 
 * 示例 1:
 * 
 * 
 * 输入: [1,4,3,2]
 * 
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 * 
 * 
 * 提示:
 * 
 * 
 * n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 * 
 * 
 */

// @lc code=start
class Solution {
    // 我们需要形成数组元​​素的配对，使得这种配对中最小的总和最大。
    // 因此，我们可以查看选择配对中最小值的操作，比如 (a,b)可能会产生的最大损失 a-b (如果 a > b)。
    // 为了使总损失最小，只有当为配对选择的数字比数组的其他元素更接近彼此时，才有可能将每个配对中的损失最小化。
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

}
// @lc code=end

