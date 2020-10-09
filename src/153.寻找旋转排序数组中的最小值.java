import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=153 lang=java
 *
 * [153] 寻找旋转排序数组中的最小值
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (51.54%)
 * Likes:    267
 * Dislikes: 0
 * Total Accepted:    81.9K
 * Total Submissions: 158.3K
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
        int left = 0, right = nums.length - 1;

        while(left < right) {
            int mid = left + (right - left) / 2;
            // 为什么比较右值和中值呢，因为比较左值和中值不一定能确定最小值范围。
            // 左值< 中值 < 右值; 左值 < 中值 ，中值 > 右值
            // 因为是向下取整的地板除，mid更接近left。 left <= mid, mid < right
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]){
                // 因为mid无法被排除，所有右侧区间设为mid而不是mid - 1
                right = mid;
            }
        }
        return nums[left];
    }

    public int findMin2(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            // 如果[left, right]连续递增，直接返回nums[left]
            if (nums[left] < nums[right]) {
                return nums[left];
            }
            int mid = left + (right - left) / 2;
            // 如果[left, mid]连续递增，则在[mid + 1, right]中找
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                // 因为mid无法被排除，所有右侧区间设为mid而不是mid - 1
                right = mid;
            }
        }
        return -1;
    }
}
// @lc code=end

