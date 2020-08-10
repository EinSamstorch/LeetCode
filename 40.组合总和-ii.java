/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (61.76%)
 * Likes:    326
 * Dislikes: 0
 * Total Accepted:    73.3K
 * Total Submissions: 117.5K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 *
 *
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 */

import java.util.*;

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, path);


        return res;
    }

    private void backtrack(int[] candidates, int start, int target, Deque<Integer> path) {
        // 先判断结束条件
        if (target < 0) {
            return;
        }
        // 当target=0时，将结果加入res中
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历选择列表,考虑去重
        // 重复的原因是因为在较深的节点值考虑了之前考虑过的元素，因此我们需要设置“下一轮搜索的起点”即可
        for (int i = start; i < candidates.length; i++) {
            // 这就是排序的好处，可以直接这样剪枝
            if (target < candidates[i]) {
                break;
            }
            // 小剪枝，去重。类似于question47，全排2
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // 做选择
            path.addLast(candidates[i]);
            // 进入到下一层决策树
            backtrack(candidates, i + 1, target - candidates[i], path);
            // 撤销选择
            path.removeLast();
        }
    }
}
// @lc code=end

