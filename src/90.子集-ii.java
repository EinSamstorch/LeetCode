import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=90 lang=java
 *
 * [90] 子集 II
 *
 * https://leetcode-cn.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (60.75%)
 * Likes:    307
 * Dislikes: 0
 * Total Accepted:    49.3K
 * Total Submissions: 80.9K
 * Testcase Example:  '[1,2,2]'
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 
 * 说明：解集不能包含重复的子集。
 * 
 * 示例:
 * 
 * 输入: [1,2,2]
 * 输出:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先把数字排序，这是剪枝的前提
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>());

        return res;
    }

    private void backtrack(int[] nums, int index, ArrayList<Integer> track) {
        res.add(new ArrayList<>(track));

        for (int i = index; i < nums.length; i++) {
            // 同一树枝上可以重复选取，同一树层上不能重复选取 
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
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

