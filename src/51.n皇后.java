/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (69.94%)
 * Likes:    494
 * Dislikes: 0
 * Total Accepted:    54.2K
 * Total Submissions: 76.8K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 *
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 *
 *
 *
 * 提示：
 *
 *
 *
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步，可进可退。（引用自
 * 百度百科 - 皇后 ）
 *
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @lc code=start

/**
 * 思考路径：
 * 1.定位这是backtrack问题
 * 2.思考决策树的构建过程
 * 3.思考回溯的模板
 */
class Solution {
    
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {

        // 构建棋盘并初始化
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }

        backtrack(board, 0);
        return res;

    }

    /**
     * 路径：board中小于row的行都已经成功的放置了皇后
     * 选择列表：第row行中所有的列都是放置皇后的选择
     * 结束条件：row超过board的最后一行
     *
     * @param board 棋盘
     * @param row   棋盘上的行
     */
    private void backtrack(char[][] board, int row) {
        // 触发结束条件
        if (row == board.length) {
            res.add(charToString(board));
            return;
        }
        // 获取列的长度
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            // 排除不合法的选择
            if (!isValid(board, row, col)) {
                continue;
            }
            // 做选择
            board[row][col] = 'Q';
            // 进入下一层决策树
            backtrack(board, row + 1);
            // 撤销选择，返回上一层决策树
            board[row][col] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        // 获取行数
        int rows = board.length;
        // 检查同一列是否有皇后冲突
        for (char[] chars : board) {
            if (chars[col] == 'Q') {
                return false;
            }
        }
        // 检查右上方是否有皇后冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查左上方是否有皇后冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private List<String> charToString(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] chars : board) {
            res.add(String.valueOf(chars));
        }
        return res;
    }
}
// @lc code=end

