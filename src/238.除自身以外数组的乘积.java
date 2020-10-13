import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=238 lang=java
 *
 * [238] 除自身以外数组的乘积
 *
 * https://leetcode-cn.com/problems/product-of-array-except-self/description/
 *
 * algorithms
 * Medium (70.96%)
 * Likes:    609
 * Dislikes: 0
 * Total Accepted:    82.1K
 * Total Submissions: 115.7K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i]
 * 之外其余各元素的乘积。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 
 * 
 * 
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * 
 */

// @lc code=start
class Solution {
    /**  
     * 利用索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。
     */
    public int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        // 前缀数组与后缀数组
        int[] left = new int[len];
        int[] right = new int[len];

        // left[i]为索引i左侧所有元素的乘积
        // 索引为0的元素，其左侧没有元素,left[0] = 1;
        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        // right[i]为索引i右侧所有元素的乘积
        // 索引为len - 1的元素，其由右侧没有元素,right[len - 1] = 1;
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        // 利用索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。
        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }

        return ans;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        Arrays.fill(ans, 1);
        int left = 1;
        int right = 1;

        for (int i = 0, j = len - 1; i < len; i++, j--) {
            ans[i] *= left;
            ans[j] *= right;
            left *= nums[i];
            right *= nums[j];
        }
        return ans;
    }
}
// @lc code=end

