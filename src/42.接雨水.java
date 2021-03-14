import java.util.ArrayDeque;
import java.util.Deque;

import org.graalvm.compiler.hotspot.stubs.CreateExceptionStub;

/*
 * @lc app=leetcode.cn id=42 lang=java
 *
 * [42] 接雨水
 *
 * https://leetcode-cn.com/problems/trapping-rain-water/description/
 *
 * algorithms
 * Hard (51.28%)
 * Likes:    1515
 * Dislikes: 0
 * Total Accepted:    131.2K
 * Total Submissions: 251.9K
 * Testcase Example:  '[0,1,0,2,1,0,1,3,2,1,2,1]'
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢
 * Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 */

// @lc code=start
class Solution {

    /**
     * 方法3：双指针方法
     *
     * @param height 高度数组
     * @return 接的雨水数量
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int ans = 0;

        while (left <= right) {
            if (leftMax < rightMax) {
                ans += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return ans;
    }

    /**
     * 方法2：动态规化 用两个数组储存左右的最大值
     *
     * @param height 高度数组
     * @return 接的雨水数量
     */
    public int trap1(int[] height) {
        // 初始化
        int ans = 0;
        int len = height.length;
        if (len < 3) {
            return 0;
        }

        int[] left_max_arr = new int[len];
        int[] right_max_arr = new int[len];
        left_max_arr[0] = height[0];
        right_max_arr[len - 1] = height[len - 1];

        // 从左到右，更新左最大数组
        for (int i = 1; i < len; i++) {
            left_max_arr[i] = Math.max(left_max_arr[i - 1], height[i]);
        }
        // 从右向左，更新右最大数组
        for (int j = len - 2; j >= 0; j--) {
            right_max_arr[j] = Math.max(right_max_arr[j + 1], height[j]);
        }

        for (int i = 0; i < len; i++) {
            ans += Math.min(left_max_arr[i], right_max_arr[i]) - height[i];
        }

        return ans;
    }

    /**
     * 方法一：暴力算法
     *
     * @param height 高度数组
     * @return 接的雨水数量
     */
    public int trap2(int[] height) {
        int res = 0;
        int size = height.length;

        // 最左和最右边没法存雨水
        for (int i = 1; i < size - 1; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }
            for (int j = i; j < size; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }

            res += Math.min(maxLeft, maxRight) - height[i];
        }
        return res;
    }

    public int trap3(int[] height) {
        int sum = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int current = 0;
        while (current < height.length) {
            // 如果栈不为空，且当前指向的高度大于栈顶高度就一直循环
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                int mid = height[stack.peek()];
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 两墙之间的距离
                int distance = current - stack.peek() - 1;
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - mid);
            }
            // 当前指针指向的墙入栈
            stack.push(current);
            current++;
        }
        return sum;
    }
}
// @lc code=end

