/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */

// @lc code=start
class Solution {
    public int maxArea(final int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        while(left < right){
            int area = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, area);
            if (height[left] < height[right]) {
                ++left;
            }else {
                --right;
            }
        }
        return ans;
    }
}
// @lc code=end

