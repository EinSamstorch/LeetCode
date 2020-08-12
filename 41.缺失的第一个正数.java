/*
 * @lc app=leetcode.cn id=41 lang=java
 *
 * [41] 缺失的第一个正数
 *
 * https://leetcode-cn.com/problems/first-missing-positive/description/
 *
 * algorithms
 * Hard (38.41%)
 * Likes:    709
 * Dislikes: 0
 * Total Accepted:    81.5K
 * Total Submissions: 202.8K
 * Testcase Example:  '[1,2,0]'
 *
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 *
 *
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 *
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 *
 *
 *
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


// @lc code=start
class Solution {

    /**
     * 方法3：
     * 要找的数一定在[1, N + 1]这个左闭右闭的区间里
     *
     * @param nums 待寻找的数组
     * @return 第一个缺失的正整数
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // 满足在指定范围内、并且没有放在正确的位置上，才交换
                // 例如：数值 3 应该放在索引 2 的位置上
                swap(nums, nums[i] - 1, i);
            }
        }

        // [1, -1, 3, 4]
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        // 都正确则返回数组长度 + 1
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }



    /**
     * 方法一：哈希表（空间复杂度不符合要求）
     * 时间复杂度：O(N), 空间复杂度：O(N)
     * 其实我们只需从最小的正整数 1开始，依次判断 2、 3 、44直到数组的长度 N 是否在数组中；
     * 如果当前考虑的数不在这个数组中，我们就找到了这个缺失的最小正整数；
     *
     * @param nums 待寻找的数组
     * @return 第一个缺失的正整数
     */
    public int firstMissingPositive0(int[] nums) {
        int len = nums.length;
        Set<Integer> hashSet = new HashSet<>();

        for (int num : nums) {
            hashSet.add(num);
        }

        for (int i = 0; i <= len; i++) {
            if (!hashSet.contains(i)) {
                return i;
            }
        }
        return len + 1;

    }

    /**
     * 方法2：二分查找（时间复杂度不符合要求）
     *
     * @param nums 待寻找的数组
     * @return 第一个缺失的正整数
     */
    public int firstMissingPositive2(int[] nums) {
        int len = nums.length;
        // 对数组排序
        Arrays.sort(nums);

        for (int i = 0; i <= len; i++) {
            int res = binarySearch(nums, i);
            if (res == -1) {
                return i;
            }
        }
        return len + 1;

    }

    /**
     * 在nums数组中寻找target
     *
     * @param nums   数组
     * @param target 目标值
     * @return target在数组中位置，没找到的话返回-1
     */
    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = right - 1;
            }

        }
        // 没有找到的话返回-1.
        return -1;
    }
}
// @lc code=end

