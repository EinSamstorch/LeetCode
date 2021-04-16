import java.util.ArrayList;
import java.util.List;

import jdk.internal.jline.internal.Curses;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (77.07%)
 * Likes:    1725
 * Dislikes: 0
 * Total Accepted:    260.1K
 * Total Submissions: 337.5K
 * Testcase Example:  '3'
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：["()"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    // 做减法
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        dfs("", n, n, res);
        return res;
    }

    /**
     * 里面算法流程和树的前序遍历也好像啊
     * @param curStr 当前递归得到的结果
     * @param left 左括号还有几个可以用
     * @param right 右括号还有几个可以用
     * @param res 结果集
     */
    private void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试,都是用新的字符串变量,所以无需回溯
        // 在递归终止的时候,直接添加到结果集即可
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }
        // 剪枝(左括号可以使用的个数严格大于右括号可以似使用的个数,才剪枝) 
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }
        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }
}
// @lc code=end

