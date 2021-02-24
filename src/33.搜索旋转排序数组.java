/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (38.07%)
 * Likes:    864
 * Dislikes: 0
 * Total Accepted:    154K
 * Total Submissions: 400.6K
 * Testcase Example:  '[4,5,6,7,0,1,2]\n0'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 
 * 你可以假设数组中不存在重复的元素。
 * 
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 
 * 示例 1:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * 
 */

// @lc code=start
class Solution {
    /**
     * 寻找变化点的方法
     */
    public int search1(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1, mid;

        // 将数组分为左右两个有序区间，mid一定会落在其中一个有序区间中
        while (lo <= hi) {
            mid = lo + (hi - lo)/2;
            if (nums[mid] == target) {
                return mid;
            }
            // 循环条件是lo <= high， 所以mid会等于low
            // 先根据 nums[mid] 与 nums[lo] 的关系判断 mid 是在左段还是右段
            if (nums[mid] >= nums[lo]) {
                //前面已经判断了mid是否等于target，所以这边target不需要等于mid了。
                // 再判断 target 是在 mid 的左边还是右边，从而调整左右边界 lo 和 hi
                if (target >= nums[lo] && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 左半部分有序
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右半部分有序
                if (nums[mid + 1] <= target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}
// @lc code=end

