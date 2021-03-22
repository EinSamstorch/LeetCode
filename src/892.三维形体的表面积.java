/*
 * @lc app=leetcode.cn id=892 lang=java
 *
 * [892] 三维形体的表面积
 *
 * https://leetcode-cn.com/problems/surface-area-of-3d-shapes/description/
 *
 * algorithms
 * Easy (63.92%)
 * Likes:    142
 * Dislikes: 0
 * Total Accepted:    31.5K
 * Total Submissions: 49.3K
 * Testcase Example:  '[[2]]'
 *
 * 给你一个 n * n 的网格 grid ，上面放置着一些 1 x 1 x 1 的正方体。
 * 
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * 
 * 放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。
 * 
 * 请你返回最终这些形体的总表面积。
 * 
 * 注意：每个形体的底面也需要计入表面积中。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：grid = [[2]]
 * 输出：10
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：grid = [[1,2],[3,4]]
 * 输出：34
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：grid = [[1,0],[0,2]]
 * 输出：16
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：grid = [[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == grid.length
 * n == grid[i].length
 * 1 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 这根本就不是简单题
     */
    public int surfaceArea(int[][] grid) {
        // 习惯上应该做参数检查，但题目中给出了 N >= 1 ，故可以略去
        int rows = grid.length;
        // 题目保证了输入一定是 N * N，但为了使得程序适用性更强，还是单独把 cols 做赋值
        int cols = grid[0].length;

        int sum = 0;
        // 垂直重叠
        int verticalOverlap = 0;
        // 行重叠
        int rowOverlap = 0;
        // 列重叠
        int colOverlap = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += grid[i][j];
                if (grid[i][j] > 1) {
                    verticalOverlap += (grid[i][j] - 1);
                }
                if (j > 0) {
                    rowOverlap += Math.min(grid[i][j - 1], grid[i][j]);
                }
                if (i > 0) {
                    colOverlap += Math.min(grid[i - 1][j], grid[i][j]);
                }
            }
        }
        return sum * 6 - (verticalOverlap + rowOverlap + colOverlap) * 2;
    }
}
// @lc code=end

