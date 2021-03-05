/*
 * @lc app=leetcode.cn id=795 lang=java
 *
 * [795] 区间子数组个数
 *
 * https://leetcode-cn.com/problems/number-of-subarrays-with-bounded-maximum/description/
 *
 * algorithms
 * Medium (52.76%)
 * Likes:    121
 * Dislikes: 0
 * Total Accepted:    5.8K
 * Total Submissions: 11.1K
 * Testcase Example:  '[2,1,4,3]\n2\n3'
 *
 * 给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。
 * 
 * 求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。
 * 
 * 例如 :
 * 输入: 
 * A = [2, 1, 4, 3]
 * L = 2
 * R = 3
 * 输出: 3
 * 解释: 满足条件的子数组: [2], [2, 1], [3].
 * 
 * 
 * 注意:
 * 
 * 
 * L, R  和 A[i] 都是整数，范围在 [0, 10^9]。
 * 数组 A 的长度范围在[1, 50000]。
 * 
 * 
 */

// @lc code=start
class Solution {
    // 还是类似992题
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return atMost(A, R) - atMost(A, L - 1);
    }

    /**
     * 前缀和
     * @return 最大为k的子数组的个数
     */
    private int atMost1(int[] nums, int k) {
        int res = 0;
        int count = 0;
        for (int num : nums) {
            if (num > k) {
                count = 0;
            } else {
                count++;
            }
            res += count;
        }
        return res;
    }

    /**
     * 滑动窗口
     */
    public int atMost(int[] nums, int k) {
        int len = nums.length;
        int left = 0, right = 0;
        int ans = 0;

        while (right < len) {
            if (nums[right] <= k) {
                right++;
                ans += right - left;
            } else {
                right++;
                left = right;
            }
        }
        return ans;
    }

}
// @lc code=end

