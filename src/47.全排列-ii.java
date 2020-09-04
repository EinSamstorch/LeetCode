/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (59.08%)
 * Likes:    369
 * Dislikes: 0
 * Total Accepted:    77.7K
 * Total Submissions: 130.5K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 *
 */

import java.util.*;

// @lc code=start
class Solution {
    private final List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 特例
        if (nums.length == 0) {
            return res;
        }
        // 排序，排序是剪枝的前提
        Arrays.sort(nums);
        // 标记数组
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(nums, path, used);

        return res;
    }

    private void backtrack(int[] nums, Deque<Integer> path, boolean[] used) {
        // 中止条件
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 已经访问过该元素，跳过
            if (used[i]) {
                continue;
            }

            // 剪枝
            if (i > 0 && nums[i] == nums[i - 1] && !used[i- 1]) {
                continue;
            }
            
            // 决策树做选择
            path.addLast(nums[i]);
            used[i] = true;

            // 进入下一层决策树
            backtrack(nums, path, used);

            // 重置状态，回到上一层决策树
            used[i] = false;
            path.removeLast();


        }

    }
}
// @lc code=end

