import java.nio.file.attribute.DosFileAttributeView;

/*
 * @lc app=leetcode.cn id=1020 lang=java
 *
 * [1020] 飞地的数量
 *
 * https://leetcode-cn.com/problems/number-of-enclaves/description/
 *
 * algorithms
 * Medium (52.86%)
 * Likes:    42
 * Dislikes: 0
 * Total Accepted:    6.7K
 * Total Submissions: 12.8K
 * Testcase Example:  '[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]'
 *
 * 给出一个二维数组 A，每个单元格为 0（代表海）或 1（代表陆地）。
 * 
 * 移动是指在陆地上从一个地方走到另一个地方（朝四个方向之一）或离开网格的边界。
 * 
 * 返回网格中无法在任意次数的移动中离开网格边界的陆地单元格的数量。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释： 
 * 有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 
 * 示例 2：
 * 
 * 输入：[[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：
 * 所有 1 都在边界上或可以到达边界。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * 所有行的大小都相同
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] grid;
    int rows, cols;
    public int numEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        // 淹没所有和边界相连的陆地, 把和边界相连的1都变成0
        for (int i = 0; i < rows; i++) {
            dfs(i, 0);
            dfs(i, cols - 1);
        }
        for (int j = 1; j < cols; j++) {
            dfs(0, j);
            dfs(rows - 1, j);
        }
        // 统计剩下的飞陆
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        dfs(x + 1, y);
        dfs(x - 1, y);
        dfs(x, y + 1);
        dfs(x, y - 1);
    }
}
// @lc code=end

