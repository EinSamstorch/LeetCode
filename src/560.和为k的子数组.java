import java.util.HashMap;
import java.util.Map;

import javax.xml.catalog.GroupEntry.PreferType;

/*
 * @lc app=leetcode.cn id=560 lang=java
 *
 * [560] 和为K的子数组
 *
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/description/
 *
 * algorithms
 * Medium (45.13%)
 * Likes:    640
 * Dislikes: 0
 * Total Accepted:    74K
 * Total Submissions: 163.9K
 * Testcase Example:  '[1,1,1]\n2'
 *
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 
 * 示例 1 :
 * 
 * 
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 
 * 
 * 说明 :
 * 
 * 
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                } 
            }
        }
        return count;
    }

    /**
     * 前缀和
     * 构建前缀和数组，以快速计算区间和
     */
    public int subarraySum2(int[] nums, int k) {
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
                // 区间和[left, right],注意下表偏移
                // 因为我们的nums[2]到nums[4]等于presum[5]-presum[2]
                // 所以这样就可以得到nums[i,j]区间内的和
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        // key：前缀和，value：key对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
        // 例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
        // 输入：[3,1,1,0] k = 2时则不会漏掉
        // 因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 鲜活的前缀和为preSum - k的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            // 然后维护preSumFreq的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}
// @lc code=end
