import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode.cn id=216 lang=java
 *
 * [216] 组合总和 III
 *
 * https://leetcode-cn.com/problems/combination-sum-iii/description/
 *
 * algorithms
 * Medium (71.66%)
 * Likes:    216
 * Dislikes: 0
 * Total Accepted:    55.9K
 * Total Submissions: 76K
 * Testcase Example:  '3\n7'
 *
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 
 * 说明：
 * 
 * 
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 
 * 
 * 示例 1:
 * 
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 
 * 
 * 示例 2:
 * 
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        // 记录路径
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(k, n, 1, path);
        return res;
    }


    public void backtrack(int k, int residue, int start, Deque<Integer> path) {
        // 先判断结束条件
        if (residue < 0) {
            return;
        }
        if (path.size() == k && residue == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 因为题目要求不能出现重复的组合
        for (int i = start; i <= 9; i++) {
            // 做选择
            path.addLast(i);
            // 进入下一层决策树
            backtrack(k, residue - i, i + 1, path);
            // 回溯,撤销选择
            path.removeLast();
        }
    }
}
// @lc code=end

