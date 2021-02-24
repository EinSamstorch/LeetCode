import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @lc app=leetcode.cn id=81 lang=java
 *
 * [81] 搜索旋转排序数组 II
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Medium (35.80%)
 * Likes:    206
 * Dislikes: 0
 * Total Accepted:    38K
 * Total Submissions: 106K
 * Testcase Example:  '[2,5,6,0,0,1,2]\n0'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * 
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * 
 * 示例 1:
 * 
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 
 * 进阶:
 * 
 * 
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int lo = 0;
        int hi = nums.length - 1;
        int mid;
        // 将数组分为左右两个有序区间，mid一定会落在其中一个有序区间中
        while (lo <= hi) {
            mid = lo + (hi - lo)/2;
            if (nums[mid] == target) {
                return true;
            }
            // 10111这种，无法判断是前面有序还是后面有序，就lo++    
            if (nums[lo] == nums[mid]) {
                lo++;
                continue;
            }
            // 循环条件是lo <= hi, 所以mid会等于low
            // 先根据nums[mid]和nums[lo]的关系判断mid是在左段还是右段
            if (nums[mid] > nums[lo]) {
                // 再判断target是在mid的左边还是右边，从而调整左右边界
                if (target >= nums[lo] && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    // 否则，去后半部分找
                    lo = mid + 1;
                }
            } else if (nums[mid] < nums[lo]) {
                if (target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            // if (nums[mid] == target) {
            //     return true;
            // }
            if (nums[left] < nums[mid]) {
                // 落在前有序数组里
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[left]) {
                // 落在后有序数组中
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else if (nums[left] == nums[mid]) {
                left++;
            }
        }
        // 后处理，夹逼以后，还要判断一下，是不是target
        return nums[left] == target;
    }
}
// @lc code=end

