import java.util.Arrays;


/*
 * @lc app=leetcode.cn id=189 lang=java
 *
 * [189] 旋转数组
 *
 * https://leetcode-cn.com/problems/rotate-array/description/
 *
 * algorithms
 * Easy (42.88%)
 * Likes:    712
 * Dislikes: 0
 * Total Accepted:    170.4K
 * Total Submissions: 394.1K
 * Testcase Example:  '[1,2,3,4,5,6,7]\n3'
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 *
 * 示例 2:
 *
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 *
 * 说明:
 *
 *
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 *
 */

// @lc code=start
class Solution {
    /**
     * 暴力解法
     */
    public void rotate1(int[] nums, int k) {
        // 旋转k次，每次将数组旋转一个元素
        for (int i = 0; i < k; i++) {
            int previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                int temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    /**
     * 使用额外的数组
     */
    public void rotate2(int[] nums, int k) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 这一步妙的啊
            ans[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(ans, 0, nums, 0, nums.length);
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        // 记录交换位置的次数，n个同学一共需要换n次
        int count = 0; 
        for (int start = 0; count < len; start++) {
            // 从0开始交换位置
            int cur = start;
            int pre = nums[cur];
            do {
                // 下一个位置
                int next = (cur + k) % len;
                // 来到角落
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
                count++;
            } while(start != cur);
        }
    }

}
// @lc code=end

