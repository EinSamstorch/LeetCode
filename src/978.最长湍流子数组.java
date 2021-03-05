import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=978 lang=java
 *
 * [978] 最长湍流子数组
 *
 * https://leetcode-cn.com/problems/longest-turbulent-subarray/description/
 *
 * algorithms
 * Medium (47.20%)
 * Likes:    157
 * Dislikes: 0
 * Total Accepted:    37.5K
 * Total Submissions: 79.4K
 * Testcase Example:  '[9,4,2,10,7,8,8,1,9]'
 *
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * 
 * 
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 
 * 
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 
 * 返回 A 的最大湍流子数组的长度。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 
 * 
 * 示例 2：
 * 
 * 输入：[4,8,12,16]
 * 输出：2
 * 
 * 
 * 示例 3：
 * 
 * 输入：[100]
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxTurbulenceSize1(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return len;
        }
        int left = 0, right = 1;
        // 为true表示nums[i - 1] < nums[i]
        boolean pre = false;
        int res = 1;
        while (right < len) {
            boolean current = nums[right - 1] < nums[right];
            if (current == pre) {
                left = right - 1;
            }
            if (nums[right - 1] == nums[right]) {
                left = right;
            }
            right++;
            res = Math.max(res, right - left); 
            pre = current;
        }
        return res;
    }

    /**
     * 使用动态规划
     */
    public int maxTurbulenceSize(int[] nums) {
        int len = nums.length;
        // 定义 up[i] 表示以位置 i 结尾的，并且 arr[i - 1] < arr[i] 的最长湍流子数组长度。
        int[] up = new int[len];
        // 定义 down[i] 表示以位置 i 结尾的，并且 arr[i - 1] > arr[i] 的最长湍流子数组长度。
        int[] down = new int[len];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        int ans = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                // 上升
                up[i] = down[i - 1] + 1;
                ans = Math.max(up[i], ans);
            } else if (nums[i] < nums[i - 1]) {
                // 下降
                down[i] = up[i - 1] + 1;
                ans = Math.max(down[i], ans);
            }
        }
        return ans;
    }
}
// @lc code=end

