import java.util.ArrayDeque;
import java.util.Deque;

import javax.print.attribute.standard.NumberUpSupported;

/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (41.61%)
 * Likes:    887
 * Dislikes: 0
 * Total Accepted:    85.8K
 * Total Submissions: 206.2K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 
 * 
 * 
 * 
 * 
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * 
 * 
 * 
 * 
 * 
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * 
 */

// @lc code=start
class Solution {
    /**
     * 枚举宽
     * 我们可以使用两重循环枚举矩形的左右边界以固定宽度 ww，
     * 此时矩形的高度 hh，就是所有包含在内的柱子的「最小高度」
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int area = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int height = Integer.MAX_VALUE;
            for (int j = i; j < heights.length; j++) {
                int width = j - i + 1;
                height = Math.min(height, heights[j]);
                int tempArea = height* width;
                area = Math.max(area, tempArea);
            }
        }
        return area;
    }

    /**
     * 枚举高。
     * 我们可以使用一重循环枚举某一根柱子，将其固定为矩形的高度 hh。随后我们从这跟柱子开始向两侧延伸，
     * 直到遇到高度小于 hh 的柱子，就确定了矩形的左右边界。
     * @param heights
     * @return
     */
    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        int area = 0;
        for (int i = 0; i < len; i++) {
            int curHeight = heights[i];
            // 找到左边最后一个大于等于heights[i]的下标
            int left = i;
            while (left > 0 && heights[left-1] >= curHeight) {
                left--;
            }
            int right = i;
            // 找到右边最后一个大于等于heights[i]的下标
            while(right < len - 1 && heights[right + 1] >= curHeight) {
                right++;
            }
            int width = right - left + 1;
            area = Math.max(area, width * curHeight);
        }
        return area;
    }

    /**
     * 维护一个从栈底到栈顶单调递增的栈
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        int[] left = new int[len];
        int[] right = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();

        // 找左侧最近的小于其高度的柱子
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();

        // 找到右侧最近的小于其高度的柱子
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            // right[i] - left[i] - 1是因为两边都不算
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;

    }
}
// @lc code=end

