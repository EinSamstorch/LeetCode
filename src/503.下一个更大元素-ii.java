import java.util.Arrays;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=503 lang=java
 *
 * [503] 下一个更大元素 II
 */

// @lc code=start
class Solution {

    /**
     * 这个是正序遍历
     * 
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements1(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);

        for (int i = 0; i < 2 * nums.length; i++) {
            // 取模，实现循环数组
            int index = i % nums.length;
            // 找到下一个更大元素
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                // 栈中保存的是索引
                res[stack.pop()] = nums[index];
            }
            // 如果栈为空 且栈顶大于下一个要加入的元素，将该元素入栈
            stack.push(index);
        }

        return res;
    }

    /**
     * 这个是倒序遍历
     */
    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            int index = i % nums.length;
            // 维护一个单调栈，栈顶到栈底单调不降
            while (!stack.isEmpty() && stack.peek() <= nums[index]) {
                stack.pop();
            }
            res[index] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[index]);
        }
        return res;
    }
}
// @lc code=end
