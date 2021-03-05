import java.nio.file.attribute.DosFileAttributeView;

/*
 * @lc app=leetcode.cn id=1034 lang=java
 *
 * [1034] 边框着色
 *
 * https://leetcode-cn.com/problems/coloring-a-border/description/
 *
 * algorithms
 * Medium (42.60%)
 * Likes:    22
 * Dislikes: 0
 * Total Accepted:    3.1K
 * Total Submissions: 7.3K
 * Testcase Example:  '[[1,1],[1,2]]\n0\n0\n3'
 *
 * 给出一个二维整数网格 grid，网格中的每个值表示该位置处的网格块的颜色。
 * 
 * 只有当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一连通分量。
 * 
 * 连通分量的边界是指连通分量中的所有与不在分量中的正方形相邻（四个方向上）的所有正方形，或者在网格的边界上（第一行/列或最后一行/列）的所有正方形。
 * 
 * 给出位于 (r0, c0) 的网格块和颜色 color，使用指定颜色 color 为所给网格块的连通分量的边界进行着色，并返回最终的网格 grid
 * 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：grid = [[1,1],[1,2]], r0 = 0, c0 = 0, color = 3
 * 输出：[[3, 3], [3, 2]]
 * 
 * 
 * 示例 2：
 * 
 * 输入：grid = [[1,2,2],[2,3,2]], r0 = 0, c0 = 1, color = 3
 * 输出：[[1, 3, 3], [2, 3, 3]]
 * 
 * 
 * 示例 3：
 * 
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], r0 = 1, c0 = 1, color = 2
 * 输出：[[2, 2, 2], [2, 1, 2], [2, 2, 2]]
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= grid.length <= 50
 * 1 <= grid[0].length <= 50
 * 1 <= grid[i][j] <= 1000
 * 0 <= r0 < grid.length
 * 0 <= c0 < grid[0].length
 * 1 <= color <= 1000
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int rows, cols;
    boolean[][] visited;
    int oldColor;
    public int[][] colorBorder(int[][] grid, int r0, int c0, int newColor) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];
        oldColor = grid[r0][c0];
        dfs(grid, r0, c0, newColor);
        return grid;
    }

    // private int dfs(int[][] grid, int x, int y, int newColor) {
    //     if (x < 0 || x >= rows || y < 0 || y >= cols) {
    //         return 0;
    //     }
    //     if (visited[x][y]) {
    //         return 1;
    //     }
    //     // 不符合条件
    //     if (grid[x][y] != oldColor) {
    //         return 0;
    //     }
    //     visited[x][y] = true;
    //     int count = 0;
    //     for (int i = 0; i < 4; i++) {
    //         int newX = x + dx[i];
    //         int newY = y + dy[i];
    //         count += dfs(grid, newX, newY, newColor);
    //     }
    //     if (count < 4) {
    //         grid[x][y] = newColor;
    //     }
    //     return 1;
    // }

    private boolean dfs(int[][] grid, int x, int y, int newColor) {
        // 周围点出界
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return true;
        }
        if (visited[x][y]) {
            return false;
        }
        // 或者与周围点的颜色不同，就把当前点标记为边界
        if (grid[x][y] != oldColor) {   
            return true;
        }
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if (dfs(grid, newX, newY, newColor)) {
                grid[x][y] = newColor;
            }
        }
        return false;
    }
}
// @lc code=end

