import java.util.*;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 *
 * https://leetcode-cn.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (68.97%)
 * Likes:    794
 * Dislikes: 0
 * Total Accepted:    119.7K
 * Total Submissions: 172.3K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 *
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * ⁠ [7],
 * ⁠ [2,2,3]
 * ]
 *
 *
 * 示例 2：
 *
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 *
 */

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, path);


        return res;
    }

    private void backtrack(int[] candidates, int start, int target, Deque<Integer> path) {
        // 先判断结束条件 test
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
            // 做选择
            path.addLast(candidates[i]);
            // 进入到下一层决策树, 由于candidates中数字可以重复使用，所以下一轮的搜索起点仍然是i
            backtrack(candidates, i, target - candidates[i], path);
            // 撤销选择
            path.removeLast();
        }
    }
}
// @lc code=end

