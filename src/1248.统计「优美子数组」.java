import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1248 lang=java
 *
 * [1248] 统计「优美子数组」
 *
 * https://leetcode-cn.com/problems/count-number-of-nice-subarrays/description/
 *
 * algorithms
 * Medium (55.05%)
 * Likes:    165
 * Dislikes: 0
 * Total Accepted:    29.9K
 * Total Submissions: 54.2K
 * Testcase Example:  '[1,1,2,1,1]\n3'
 *
 * 给你一个整数数组 nums 和一个整数 k。
 * 
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 
 * 请返回这个数组中「优美子数组」的数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 
 * 
 * 示例 3：
 * 
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        // 统计奇数个数，相当于我们的presum
        int oddNum = 0;
        int count = 0;
        map.put(0, 1);

        for (int num : nums) {
            oddNum += num % 2;
            // 发现存在，则count++
            if (map.containsKey(oddNum - k)) {
                count += map.get(oddNum - k);
            }
            map.put(oddNum, map.getOrDefault(oddNum, 0) + 1);
        }
        return count;
    }
}
// @lc code=end

