/*
 * @lc app=leetcode.cn id=162 lang=java
 *
 * [162] 寻找峰值
 *
 * https://leetcode-cn.com/problems/find-peak-element/description/
 *
 * algorithms
 * Medium (47.39%)
 * Likes:    308
 * Dislikes: 0
 * Total Accepted:    61.4K
 * Total Submissions: 128.9K
 * Testcase Example:  '[1,2,3,1]'
 *
 * 峰值元素是指其值大于左右相邻值的元素。
 * 
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 
 * 示例 1:
 * 
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 
 * 示例 2:
 * 
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5 
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * 
 * 
 * 说明:
 * 
 * 你的解法应该是 O(logN) 时间复杂度的。
 * 
 */

// @lc code=start
class Solution {
    
    /**
     * 线性扫描法
     * 因为 nums[-1] 看做负无穷，所以从第 0 个元素开始，它一定是上升的趋势，由于我们要找峰顶，所以当它第一次出现下降，下降前的值就是我们要找的了。
     */
    public int findPeakElement1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // 第一次下降
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        // 一直上升的话，返回最后一个
        return nums.length - 1;
    }

    /**
     * 递归法 二分
     */
    public int findPeakElement2(int[] nums) {
        return serach(nums, 0, nums.length - 1);
    }

    private int serach(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] > nums[mid + 1]) {
          return serach(nums, left, mid);
        }
        return serach(nums, mid + 1, right);
    }

    /**
     * 迭代法 二分
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        // 此处right为nums.length - 1
        // 考虑极端情况下，nums序列单调递增，不断更新left = mid + 1， 最后left == len - 1， mid + 1越界
        int right = nums.length - 1;
        // 当left == right时退出循环
        while (left < right) {
            int mid = (left + right) / 2;
            // 如果中间元素恰好处于一个降序或者局部下降坡度中，则说明峰值元素会在本元素的左边
            // 包括其本身
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                // 如果中间元素处于一个上升坡度或者局部上升坡度中，说明峰值元素会在本元素右边
                left = mid + 1;
            }
        }
        return left;
    }
}
// @lc code=end

