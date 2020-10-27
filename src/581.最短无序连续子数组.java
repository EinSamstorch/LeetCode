import java.util.Arrays;
import java.util.Stack;

import jdk.tools.jlink.internal.plugins.LegalNoticeFilePlugin;

/*
 * @lc app=leetcode.cn id=581 lang=java
 *
 * [581] 最短无序连续子数组
 *
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/description/
 *
 * algorithms
 * Medium (35.04%)
 * Likes:    418
 * Dislikes: 0
 * Total Accepted:    41.3K
 * Total Submissions: 117.8K
 * Testcase Example:  '[2,6,4,8,10,9,15]'
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 
 * 你找到的子数组应是最短的，请输出它的长度。
 * 
 * 示例 1:
 * 
 * 
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 
 * 
 * 说明 :
 * 
 * 
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 * 
 * 
 */

// @lc code=start
class Solution {
    // 自己的暴力解法，超时了
    public int findUnsortedSubarray1(int[] nums) {
        if (check(nums)) {
            return 0;
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int[] temp = Arrays.copyOf(nums, nums.length);
                // Arrays.sort(int[] a, int fromIndex, int toIndex), 包括fromIndex, 不包括toIndex
                Arrays.sort(temp, i, j + 1);
                if (check(temp)) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }
        return minLen;
    }

    public boolean check(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // 找到最左边不在正确位置得边界和最右边不在正确位置的边界
    // 找逆序对
    public int findUnsortedSubarray3(int[] nums) {
        int left = nums.length;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    right = Math.max(right, j);
                    left = Math.min(left, i);
                }
            }
        }
        return right - left < 0 ? 0 : right - left + 1;
    }

    // 将数组nums进行排序，记为nums_sorted,然后比较nums和nums_sorted的元素
    // 来决定最左边和最右边不匹配的元素
    public int findUnsortedSubarray4(int[] nums) {
        // 原来复制数组这样就可以了，这个最简单
        int[] copyNums = nums.clone();
        Arrays.sort(copyNums);
        int start = nums.length;
        int end = 0;

        for (int i = 0; i < nums.length; i++) {
            if (copyNums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end - start < 0 ? 0 : end - start + 1;
    }

    /**
     * 单调栈总结：
     * 找下一个更大的元素，就单调递减栈
     * 找下一个更小的元素，就单调递增栈
     * 找左边，从头遍历；找右边，从尾遍历
     *
     */
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int left = nums.length;
        int right = 0;

        // 构建单调递增栈，从前遍历，找数组左边第一个比他小的元素的下标
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }
        stack.clear();

        // 构建单调递减栈，从后遍历，找数组右边第一个比他大的元素的下标
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }

        return right - left > 0 ? right - left + 1 : 0;
    }
}
// @lc code=end

