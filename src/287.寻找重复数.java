/*
 * @lc app=leetcode.cn id=287 lang=java
 *
 * [287] 寻找重复数
 *
 * https://leetcode-cn.com/problems/find-the-duplicate-number/description/
 *
 * algorithms
 * Medium (65.92%)
 * Likes:    906
 * Dislikes: 0
 * Total Accepted:    101.6K
 * Total Submissions: 154.1K
 * Testcase Example:  '[1,3,4,2,2]'
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和
 * n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 
 * 示例 1:
 * 
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 
 * 
 * 说明：
 * 
 * 
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 二分法，结合抽屉原理
     * 以 [2, 4, 5, 2, 3, 1, 6, 7] 为例，一共 8 个数，n + 1 = 8，n = 7，根据题目意思，每个数都在 1 和 7 之间。
例如：区间 [1, 7]的中位数是 4，遍历整个数组，统计小于等于 4 的整数的个数，如果不存在重复元素，最多为 4个。
等于 4的时候区间 [1, 4]内也可能有重复元素。但是，如果整个数组里小于等于 4 的整数的个数严格大于 4 的时候，就可以说明重复的数存在于区间 [1, 4]。
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1, right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count =  0;
            for (int i = 0; i < len; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
// @lc code=end

