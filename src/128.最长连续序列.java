/*
 * @lc app=leetcode.cn id=128 lang=java
 *
 * [128] 最长连续序列
 *
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/description/
 *
 * algorithms
 * Hard (52.12%)
 * Likes:    560
 * Dislikes: 0
 * Total Accepted:    80.1K
 * Total Submissions: 153.7K
 * Testcase Example:  '[100,4,200,1,3,2]'
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 */

import java.util.HashSet;
import java.util.Set;

// @lc code=start
class Solution {
    /**
     * 暴力解法用到了许多不必要的枚举，
     * 比如x, x + 1, x + 2, ..., x + y已经匹配，
     * 而我们却重新从x + 1， x + 2， ... 开始匹配
     * 所以需要跳过。我们需要枚举的数，一定是不存在x - 1的，要不然就会从x - 1开始匹配
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        // 使用hashset可以将查找的时间复杂度降到O(1)
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    /**
     * 暴力解法
     * 枚举数组中的每个数 x，考虑以其为起点，不断尝试匹配 x+1, x+2, ⋯ 是否存在
     * 假设最长匹配到了 x+y，那么以 xx 为起点的最长连续序列即为x,x+1,x+2,⋯,x+y，其长度为 y+1，
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        Set<Integer> num_set = new HashSet<>();
        for (int num : nums) {
            num_set.add(num);
        }
        int longestStreak = 0;

        for (int num : nums) {
            int currentNum = num;
            int currentStreak = 1;
            while (num_set.contains(currentNum + 1)) {
                currentNum += 1;
                currentStreak += 1;
            }
            longestStreak = Math.max(longestStreak, currentStreak);
        }

        return longestStreak;
    }
}
// @lc code=end

