import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.PresentationDirection;

/*
 * @lc app=leetcode.cn id=930 lang=java
 *
 * [930] 和相同的二元子数组
 *
 * https://leetcode-cn.com/problems/binary-subarrays-with-sum/description/
 *
 * algorithms
 * Medium (39.61%)
 * Likes:    92
 * Dislikes: 0
 * Total Accepted:    5.9K
 * Total Submissions: 14.8K
 * Testcase Example:  '[1,0,1,0,1]\n2'
 *
 * 在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：A = [1,0,1,0,1], S = 2
 * 输出：4
 * 解释：
 * 如下面黑体所示，有 4 个满足题目要求的子数组：
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * A.length <= 30000
 * 0 <= S <= A.length
 * A[i] 为 0 或 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numSubarraysWithSum1(int[] nums, int k) {
        int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            // 这里要注意，我们的前缀和是从preSum[1]开始填充的
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                // 区间和[left, right],注意下标偏移
                // 因为我们的nums[2]到nums[4]等于presum[5]-presum[2]
                // 所以这样就可以得到nums[i,j]区间内的和
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 和第560题一模一样
     */
    public int numSubarraysWithSum(int[] nums, int k) {
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
// @lc code=end

