/*
 * @lc app=leetcode.cn id=26 lang=java
 *
 * [26] 删除排序数组中的重复项
 */

// @lc code=start
class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 1.指针定义，[0, index]是修改后无重复得排序元素
        // 注意，这里已经把0纳入了
        int index = 0;
        // 另一个循环指针，从1开始，中止为nums.length
        // 为什么从1开始，因为要比较重复nums[0]肯定是不重复得
        for (int i = 1; i < nums.length; i++) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
        }

        return index + 1;
    }
}
// @lc code=end

