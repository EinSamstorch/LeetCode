/*
 * @lc app=leetcode.cn id=713 lang=java
 *
 * [713] 乘积小于K的子数组
 *
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/description/
 *
 * algorithms
 * Medium (37.14%)
 * Likes:    197
 * Dislikes: 0
 * Total Accepted:    10.4K
 * Total Submissions: 28.1K
 * Testcase Example:  '[10,5,2,6]\n100'
 *
 * 给定一个正整数数组 nums。
 * 
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 * 
 * 示例 1:
 * 
 * 
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 
 * 
 * 说明:
 * 
 * 
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 二分查找
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        // 因为数量级太大，相乘会溢出
        double logk = Math.log(k);
        // prefix是前缀和数组
        double[] prefix = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + Math.log(nums[i]);
        }

        int ans = 0;
        for (int i = 0; i < prefix.length; i++) {
            int lo = i + 1;
            int hi = prefix.length;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                // 我们可以用 prefix[j+1]−prefix[i] 得到nums[i] 到 nums[j] 的乘积的对数
                if (prefix[mid] < prefix[i] + logk) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            ans += lo - i - 1;
        }
        return ans;
    }

    /**
     * 滑动窗口
     * 对每个right，都需要找到最小的left，满足乘积和小于k。
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int product = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left++];
            }
            ans += right - left + 1;
        }
        return ans;
    }

    /**
     * 和992题好像
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int len = nums.length;
        if (k < 2) {
            return 0;
        }
        int left = 0, right = 0;
        int product = 1;
        int ans = 0;

        while (right < len) {
            product *= nums[right];
            right++;
            
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            ans += right - left;
        }
        return ans;
    }
}
// @lc code=end

