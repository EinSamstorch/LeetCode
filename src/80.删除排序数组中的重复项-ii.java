/*
 * @lc app=leetcode.cn id=80 lang=java
 *
 * [80] 删除排序数组中的重复项 II
 */

// @lc code=start
class Solution {
    public int removeDuplicates3(int[] nums) {
        int i = 1, count = 1, length = nums.length;
        
        while (i < length) {
            if (nums[i] == nums[i - 1]) {
                count++;
                if (count > 2) {
                    this.removeElement(nums, i);
                    i--;
                    length--;
                }
            } else {
                count = 1;
            }
            i++;
        }
        return length;
    }

    public int[] removeElement(int[] nums, int index) {
        for (int i = index + 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }
        return nums;
    }

    public int removeDuplicates(int[] nums) {
        int left = 0;

        for(int right = 0; right < nums.length; right++) {
            // nums[left - 2] < nums[right]
            // 如果重复个数大于2，那么nums[L-2] < nums[R]就是不成立的
            // 即 多余重复项都被忽视了，直到遇到新的数字或者数字结束
            if (left < 2 || nums[left - 2] < nums[right]) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public int removeDuplicates3(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= k) {
            return nums.length;
        }

        // 1. 定义[0, index]是修改之后得满足要求得数组区间，这里已经把0 1 2 ... k-1,共k个数放进去了
        int index = k - 1;
        // 2. 判断中止条件
        for (int i = k; i < nums.length; i++) {
            // 3.指针移动条件
            if (nums[i] != nums[index - k + 1]) {
                index++;
                nums[index] = nums[i];
            }
        }
        // 4.判断返回值
        return index + 1;
    }
}
// @lc code=end

