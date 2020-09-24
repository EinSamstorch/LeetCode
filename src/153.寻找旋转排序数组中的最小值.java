/*
 * @lc app=leetcode.cn id=153 lang=java
 *
 * [153] 寻找旋转排序数组中的最小值
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (51.64%)
 * Likes:    260
 * Dislikes: 0
 * Total Accepted:    79.4K
 * Total Submissions: 153.8K
 * Testcase Example:  '[3,4,5,1,2]'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7]  可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 *
 * 输入: [3,4,5,1,2]
 * 输出: 1
 *
 * 示例 2:
 *
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[left] <= nums[mid]) { //左边有序
                min = Math.min(min, nums[left]);
                left = mid + 1; //看右边
            } else { //右边有序
                min = Math.min(min, nums[mid]);
                right = mid - 1; //看左边
            }
            5 1234
        }
        return min;
    }
}
// @lc code=end

