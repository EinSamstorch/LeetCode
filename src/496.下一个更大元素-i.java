

/*
 * @lc app=leetcode.cn id=496 lang=java
 *
 * [496] 下一个更大元素 I
 *
 * https://leetcode-cn.com/problems/next-greater-element-i/description/
 *
 * algorithms
 * Easy (64.72%)
 * Likes:    216
 * Dislikes: 0
 * Total Accepted:    34.4K
 * Total Submissions: 53.1K
 * Testcase Example:  '[4,1,2]\n[1,3,4,2]'
 *
 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2
 * 中的下一个比其大的值。
 * 
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * ⁠   对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 * ⁠   对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 * ⁠   对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 
 * 示例 2:
 * 
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 * 对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * ⁠   对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 * 
 * 
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// @lc code=start
class Solution {
    public int[] nextGreaterElement2(int[] findNums, int[] nums) {
        int[] result = new int[findNums.length];

        for (int i = 0; i < findNums.length; i++) {
            int m = 0;
            boolean flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (findNums[i] == nums[j]) {
                    m = j;
                } 
            }
            for (int k = m; k < nums.length; k++) {
                if (findNums[i] < nums[k] ) {
                    flag = true;
                    result[i] = nums[k];
                    break;
                }
            }
            if (!flag) {
                result[i] = -1;
            }
        }
        return result;
    }

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        int[] result = new int[findNums.length];

        Map<Integer, Integer> map = nextGreaterHelper(nums);
        for (int i = 0; i < findNums.length; i++) {
            result[i] = map.get(findNums[i]);
        }

        return result;
    }

    private Map<Integer, Integer> nextGreaterHelper(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        // 存放高个子元素的栈
        Stack<Integer> stack = new Stack<>();

        // 倒着往栈里面放
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                // 矮个子起开
                stack.pop();
            }

            // 当前元素身后的第一个高个
            map.put(nums[i], stack.isEmpty() ? -1 : stack.peek());
            // 进队，接受之后的身高判定
            stack.push(nums[i]);
        }
        return map;
    }

}

// @lc code=end

