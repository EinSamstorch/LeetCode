import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=442 lang=java
 *
 * [442] 数组中重复的数据
 *
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/description/
 *
 * algorithms
 * Medium (65.74%)
 * Likes:    247
 * Dislikes: 0
 * Total Accepted:    21.1K
 * Total Submissions: 31.7K
 * Testcase Example:  '[4,3,2,7,8,2,3,1]'
 *
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 *
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 *
 */

// @lc code=start
class Solution {

    /**
     * 类似448.找到数组中消失的数字 原地hash
     *
     * @param nums 待遍历的数组
     * @return 数组中重复的数据
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }

        for (int i = 0; i < len; i++) {
            // 处理值变为新指针。数值为i的数字映射到下标 i - 1的位置上
            int newIndex = Math.abs(nums[i]) - 1;
            // 如果位置 i - 1上的数字已经为负数，则找到
            if (nums[newIndex] < 0) {
                res.add(Math.abs(newIndex + 1));
            }
            nums[newIndex] = -nums[newIndex];
        }
        return res;
    }


    /**
     * 类似41.缺失第一个正数的解法，需要交换数组
     *
     * @param nums 待遍历的数组
     * @return 数组中重复的数据
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }

        // 对数组自己做哈希：数值为i的数字映射到下标 i - 1的位置
        for (int i = 0; i < len; i++) {
            // 在指定范围内，如果数字并且没有放在正确的位置上，才交换
            // 例如：数值3应该放在索引2的位置上
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 遍历交换后的数组，如果当前下标和数字不对应
        // 说明出现了重复的数字，加入到res中
        for (int i = 0; i < len; i++) {
            if (nums[i] - 1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
// @lc code=end

