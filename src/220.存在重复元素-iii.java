import java.util.TreeSet;

/*
 * @lc app=leetcode.cn id=220 lang=java
 *
 * [220] 存在重复元素 III
 *
 * https://leetcode-cn.com/problems/contains-duplicate-iii/description/
 *
 * algorithms
 * Medium (26.57%)
 * Likes:    299
 * Dislikes: 0
 * Total Accepted:    30K
 * Total Submissions: 113K
 * Testcase Example:  '[1,2,3,1]\n3\n0'
 *
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j
 * 的差的绝对值也小于等于 ķ 。
 * 
 * 如果存在则返回 true，不存在返回 false。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 
 * 示例 2:
 * 
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 
 * 示例 3:
 * 
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 * 
 */

// @lc code=start
class Solution {
    /**
     * 线性搜索，超时？
     * 维护了一个k大小的滑动窗口
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= k && i + j < len; j++) {
                // 不转换成long的话会溢出
                if (Math.abs((long)nums[i] - (long)nums[i + j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 自平衡二叉树
     * 可以在对数时间内通过插入和删除来对滑动窗口内元素排序
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Long s = set.ceiling((long)nums[i]);
            if (s != null && s <= (long)nums[i] + t) return true;
    
            // Find the predecessor of current element
            Long g = set.floor((long)nums[i]);
            if (g != null && nums[i] <= (long)g + t) return true;
    
            set.add((long)nums[i]);
            if (set.size() > k) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
    }

}
// @lc code=end

