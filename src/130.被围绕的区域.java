import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=130 lang=java
 *
 * [130] 被围绕的区域
 *
 * https://leetcode-cn.com/problems/surrounded-regions/description/
 *
 * algorithms
 * Medium (42.26%)
 * Likes:    475
 * Dislikes: 0
 * Total Accepted:    88.3K
 * Total Submissions: 209K
 * Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
 *
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * 
 * 示例:
 * 
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * 
 * 运行你的函数后，矩阵变为：
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * 
 * 解释:
 * 
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O'
 * 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * 
 */

// @lc code=start
class Solution {
    int rows, cols;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    char[][] board;

    /**
     * 深度优先搜索
     */
    public void solve1(char[][] board) {
        rows = board.length;
        if (rows == 0) {
            return;
        }
        cols = board[0].length;
        this.board = board;
        
        // 1. 将四周的0以及和其连通的0都设置为'A'
        // 第一列和最后一列
        for (int i = 0; i < rows; i++) {
            dfs(i, 0);
            dfs(i, cols - 1);
        }
        // 第一行和最后一行
        for (int j = 1; j < cols - 1; j++) {
            dfs(0, j);
            dfs(rows - 1, j);
        }

        // 2. 遍历一次棋盘
        // 剩下的0就是被x包围的0
        // A 就是不能被包围的0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            dfs(newX, newY);
        }
    }

    public void solve(char[][] board) {
        rows = board.length;
        if (rows == 0) {
            return;
        }
        cols = board[0].length;
        // this.board = board;

        // 1. 将四周的'O'全部入队列
        Queue<int[]> queue = new LinkedList<>();
        // 第一列和最后一列
        for (int i = 0; i < rows; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[i][cols - 1] == 'O') {
                queue.offer(new int[]{i, cols - 1});
            }
        }
        // 第一行和最后一行
        for (int j = 1; j < cols - 1; j++) {
            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});
            }
            if (board[rows - 1][j] == 'O') {
                queue.offer(new int[]{rows - 1, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (inArea(newX, newY) && board[newX][newY] == 'O') {
                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        // 2. 遍历一次棋盘
        // 剩下的0就是被x包围的0
        // - 就是不能被包围的0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
// @lc code=end

