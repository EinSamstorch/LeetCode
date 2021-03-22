import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=1296 lang=java
 *
 * [1296] 划分数组为连续数字的集合
 *
 * https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/description/
 *
 * algorithms
 * Medium (44.49%)
 * Likes:    45
 * Dislikes: 0
 * Total Accepted:    6.4K
 * Total Submissions: 14.4K
 * Testcase Example:  '[1,2,3,3,4,4,5,6]\n4'
 *
 * 给你一个整数数组 nums 和一个正整数 k，请你判断是否可以把这个数组划分成一些由 k 个连续数字组成的集合。
 * 如果可以，请返回 True；否则，返回 False。
 * 
 * 
 * 
 * 注意：此题目与 846 重复：https://leetcode-cn.com/problems/hand-of-straights/
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [1,2,3,3,4,4,5,6], k = 4
 * 输出：true
 * 解释：数组可以分成 [1,2,3,4] 和 [3,4,5,6]。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * 输出：true
 * 解释：数组可以分成 [1,2,3] , [2,3,4] , [3,4,5] 和 [9,10,11]。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [3,3,2,2,1,1], k = 3
 * 输出：true
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：nums = [1,2,3,4], k = 3
 * 输出：false
 * 解释：数组不能分成几个大小为 3 的子数组。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPossibleDivide1(int[] nums, int k) {
        int len = nums.length;
        if (len % k != 0) {
            return false;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(len);
        for (int num : nums) {
            minHeap.add(num);
        }

        while (!minHeap.isEmpty()) {
            int top = minHeap.poll();
            for (int i = 1; i < k; i++) {
                // 从1开始，正好需要移除k - 1个元素
                // i正好就是相对于top的偏移
                // 注意：这个remove会线性去扫top + i的索引，时间复杂度是O(N)
                if (!minHeap.remove(top + i)) {
                    // 如果移除失败，说明划分不存在，直接返回false
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * java sort + hashmap
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        int len = nums.length;
        if (len % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            int curNumCount = map.get(num);
            if (curNumCount == 0) {
                // 等于0说明被归到前面集合中了
                continue;
            }
            map.put(num, curNumCount - 1);
            for (int i = 1; i < k; i++) {
                int count = map.getOrDefault(num + i, 0);
                if (count == 0) {
                    // 等于0就说明当前num为起点，找不到k个大小的连续集合
                    return false;
                }
                map.put(num + i, count - 1);
            }

        }
        return true;
    }
}
// @lc code=end

