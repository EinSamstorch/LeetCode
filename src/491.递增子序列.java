import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=491 lang=java
 *
 * [491] 递增子序列
 *
 * https://leetcode-cn.com/problems/increasing-subsequences/description/
 *
 * algorithms
 * Medium (55.49%)
 * Likes:    272
 * Dislikes: 0
 * Total Accepted:    35.7K
 * Total Submissions: 64.3K
 * Testcase Example:  '[4,6,7,7]'
 *
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是 2 。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：[4, 6, 7, 7]
 * 输出：[[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7],
 * [4,7,7]]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 * 
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    /**
     * 在[回溯算法：求子集问题（二）]中我们是通过排序，再加一个标记数组来达到去重的目的。而本题求自增子序列，是不能对原数组经行排序的，排完序的数组都是自增子序列了。所以不能使用之前的去重逻辑！
     * 使用set去重  同一层不能重复使用一个元素
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, -1, path);
        return res;
    }
    private void dfs(int[] nums, int index, Deque<Integer> path) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
        }
        // 在[index + 1, nums.length - 1]范围内 遍历搜索递增序列的下一个值
        // 借助set对[index + 1, nums.length - 1]范围内的数去重
        Set<Integer> set = new HashSet<>();
        for (int i = index + 1; i < nums.length; i++) {
            // 1. 如果set中已经有与nums[i]相同的值了,说明加上 nums[i] 后的所有可能的递增序列之前已经被搜过一遍了，因此停止继续搜索
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            // 2. 如果nums[i] >= nums[index],说明出现了新的递增序列,因此继续dfs搜索
            if (index == -1 || nums[i] >= nums[index]) {
                path.addLast(nums[i]);
                dfs(nums, i, path);
                path.removeLast();
            }
        }
    }
}
// @lc code=end

