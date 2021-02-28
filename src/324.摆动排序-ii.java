import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=324 lang=java
 *
 * [324] 摆动排序 II
 *
 * https://leetcode-cn.com/problems/wiggle-sort-ii/description/
 *
 * algorithms
 * Medium (36.90%)
 * Likes:    238
 * Dislikes: 0
 * Total Accepted:    18.7K
 * Total Submissions: 50.6K
 * Testcase Example:  '[1,5,1,1,6,4]'
 *
 * 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * 
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * 
 * 
 * 
 * 
 * 进阶：你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 * 
 */

// @lc code=start
class Solution {
    public void wiggleSort(int[] nums) {
        int[] help = nums.clone();
        Arrays.sort(help);
        int N = nums.length;

        // 比如123456
        for (int i = 1; i < nums.length; i += 2) {
            // 遍历完成后 x 6 x 5 x 4
            N--;
            nums[i] = help[N];
        }
        for (int i = 0; i < nums.length; i += 2) {
            N--;
            // 遍历完成后 3 6 2 5 1 4
            nums[i] = help[N];
        }
    }
}
// @lc code=end

