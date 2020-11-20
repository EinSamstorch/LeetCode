import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=697 lang=java
 *
 * [697] 数组的度
 *
 * https://leetcode-cn.com/problems/degree-of-an-array/description/
 *
 * algorithms
 * Easy (54.75%)
 * Likes:    186
 * Dislikes: 0
 * Total Accepted:    26.6K
 * Total Submissions: 48.5K
 * Testcase Example:  '[1,2,2,3,1]'
 *
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1:
 *
 *
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释:
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 *
 *
 * 示例 2:
 *
 *
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 *
 *
 * 注意:
 *
 *
 * nums.length 在1到50,000区间范围内。
 * nums[i] 是一个在0到49,999范围内的整数。
 *
 *
 */

// @lc code=start
class Solution {
    public int findShortestSubArray1(int[] nums) {
        // 1. 统计出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 2. 找到出现次数最多的那个数
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        int maxNum = majorityEntry.getKey();
        // int maxNum = Collections.max(map.values());

        // 3. 找到最大数出现的左右边界
        int left = 0, right = nums.length - 1;
        // 3.1 找左边界
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == maxNum) {
                left = i;
                break;
            }
        }
        // 3.2 找右边界
        for (int j = nums.length - 1; j >= 0; j--) {
            if (nums[j] == maxNum) {
                right = j;
                break;
            }
        }
        return right - left + 1;
    }

    /**
     * 用了三个哈希表分别存储了所有元素的首尾索引及频次
     */
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            left.putIfAbsent(num, i);
            right.put(num, i);
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int ans = nums.length;
        int degree = Collections.max(count.values());
        for (int num : count.keySet()) {
            if (count.get(num) == degree) {
                ans = Math.min(ans, right.get(num) - left.get(num) + 1);
            }
        }
        return ans;

    }
}
// @lc code=end

