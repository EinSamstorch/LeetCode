import java.nio.file.attribute.DosFileAttributeView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N 皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (73.87%)
 * Likes:    837
 * Dislikes: 0
 * Total Accepted:    117.8K
 * Total Submissions: 159.5K
 * Testcase Example:  '4'
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 
 * 
 * 
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：n = 1
 * 输出：[["Q"]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] rows : board) {
            Arrays.fill(rows, '.');
        }
        // 从第0行对棋盘进行dfs
        backtrack(board, 0);
        return res;
    }

    private void backtrack(char[][] board, int row) {
        // 终止条件,搜索到最后一行了
        if (board.length == row) {
            res.add(charToString(board));
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            // 排除掉不合法的选项
            if (!isVaild(board, row, col)) {
                continue;
            }
            // 决策树上做选择
            board[row][col] = 'Q';
            // 进入下一层决策数
            backtrack(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }

    /**
     * 判断皇后摆放位置是否有效
     */
    private boolean isVaild(char[][] board, int row, int col) {
        int n = board.length;
        // 检查同一列是否有皇后冲突
        for (char[] rows : board) {
            if (rows[col] == 'Q') {
                return false;
            }
        }
        // 检查右上角是否有皇后冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查左上角是否有皇后冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * 将字符数组转换成String放入list中
     */
    private List<String> charToString(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] rows : board) {
            res.add(String.valueOf(rows));
        }
        return res;
    }
}
// @lc code=end

