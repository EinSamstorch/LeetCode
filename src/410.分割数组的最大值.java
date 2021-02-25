/*
 * @lc app=leetcode.cn id=410 lang=java
 *
 * [410] 分割数组的最大值
 *
 * https://leetcode-cn.com/problems/split-array-largest-sum/description/
 *
 * algorithms
 * Hard (54.14%)
 * Likes:    435
 * Dislikes: 0
 * Total Accepted:    28.6K
 * Total Submissions: 52.7K
 * Testcase Example:  '[7,2,5,10,8]\n2'
 *
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * 
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,2,3,4,5], m = 2
 * 输出：9
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1,4,4], m = 3
 * 输出：4
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int splitArray(int[] nums, int m) {
        int max = 0;
        int sum = 0;
        // 计算子数组各自和的最大值的上下界
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        
        // 使用二分查找，确定一个恰当的子数组各自和的最大值
        // 使得它对应的子数组的分割术，恰好等于m
        int left = max;
        int right = sum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int splits = splits(nums, mid);
            if (splits > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;

    }

    /**
     * 子数组的最大分割数
     */
    private int splits(int[] nums, int maxIntervalSum) {
        // 至少一个分割
        int spilts = 1;
        // 当前区间的和
        int curIntervalSum = 0;
        for (int num : nums) {
            // 尝试加上当前遍历的这个数，如果加上去超过了子数组各自和的最大值，就不加这个数，另起炉灶
            if (curIntervalSum + num > maxIntervalSum) {
                curIntervalSum = 0;
                spilts++;
            }
            curIntervalSum += num;
        }
        return spilts;
    }
}
// @lc code=end

