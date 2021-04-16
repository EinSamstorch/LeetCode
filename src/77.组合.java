import java.util.ArrayList;
import java.util.List;

import org.graalvm.compiler.core.common.alloc.Trace;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (76.68%)
 * Likes:    558
 * Dislikes: 0
 * Total Accepted:    153.9K
 * Total Submissions: 200.8K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 
 * 示例:
 * 
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 * 
 */

// @lc code=start
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n <= 0) {
            return res;
        }
        backtrack(n, k, 1, new ArrayList<Integer>());
        return res;
    }

    private void backtrack(int width, int height, int start, ArrayList<Integer> track) {
        if (track.size() == height) {
            res.add(new ArrayList<>(track));
            return;
        }
        // 加上了剪枝
        for (int i = start; i <= width - (height - track.size()) + 1; i++) {
            // 做选择
            track.add(i);
            backtrack(width, height, i + 1, track);
            // 撤销选择,返回上一层
            track.remove(track.size() - 1);
        }
    }
}
// @lc code=end

