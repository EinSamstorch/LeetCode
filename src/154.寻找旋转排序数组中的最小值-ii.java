import org.graalvm.compiler.core.phases.MidTier;

/*
 * @lc app=leetcode.cn id=154 lang=java
 *
 * [154] 寻找旋转排序数组中的最小值 II
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Hard (50.20%)
 * Likes:    186
 * Dislikes: 0
 * Total Accepted:    38.6K
 * Total Submissions: 76.6K
 * Testcase Example:  '[1,3,5]'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * 
 * ( 例如，数组 [0,1,2,4,5,6,7]  可能变为 [4,5,6,7,0,1,2] )。
 * 
 * 请找出其中最小的元素。
 * 
 * 注意数组中可能存在重复的元素。
 * 
 * 示例 1：
 * 
 * 输入: [1,3,5]
 * 输出: 1
 * 
 * 示例 2：
 * 
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 
 * 说明：
 * 
 * 
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 类似153题
     */
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }


    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            // 如果[left, right]连续递增，直接返回nums[left]
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            if (nums[left] == nums[mid]) {
                left++;
                continue;
            }
            // 如果[left, mid]连续递增，则在[mid + 1, right]中找
            if (nums[mid] > nums[left]) {
                left = mid + 1;
            } else {
                // 因为mid无法被排除，所有右侧区间设为mid而不是mid - 1
                right = mid;
            }
        }
        return nums[left];
    }
}
// @lc code=end

