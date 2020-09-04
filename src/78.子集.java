/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 *
 * https://leetcode-cn.com/problems/subsets/description/
 *
 * algorithms
 * Medium (77.71%)
 * Likes:    720
 * Dislikes: 0
 * Total Accepted:    123.7K
 * Total Submissions: 158.9K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 *
 */

import java.util.ArrayList;
import java.util.List;

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, int index, List<Integer> track) {
        res.add(new ArrayList<>(track));

        for (int i = index; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, i + 1, track);
            // 撤销选择，返回上一层
            track.remove(track.size() - 1);
        }
    }
}
// @lc code=end

