/*
 * @lc app=leetcode.cn id=34 lang=java
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 *
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * algorithms
 * Medium (39.67%)
 * Likes:    533
 * Dislikes: 0
 * Total Accepted:    117.2K
 * Total Submissions: 292.5K
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 
 * 示例 1:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 
 * 示例 2:
 * 
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * 
 */

// @lc code=start
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        int lowerBound = searchLowerBound(nums, target);
        if (lowerBound == nums.length || nums[lowerBound] != target) {
            return ans;
        }
        int upperBound = searchUpperBound(nums, target);
        ans[0] = lowerBound;
        ans[1] = upperBound;
        return ans;
    }

    /**
     * 找到目标数在最左边出现的位置
     * @param nums 待寻找的数组
     * @param target 目标数
     * @return 目标数第一次出现的位置
     */
    private int searchLowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right)/2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 返回left或者right都没关系，因为中止条件是left == right
        return left;
    }
    

    /**
     * 找到目标数在最右边出现的位置
     * @param nums 待寻找的数组
     * @param target 目标数
     * @return 目标数最后第一次出现的位置
     */
    private int searchUpperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right)/2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right - 1;
    }
}

// @lc code=end

