import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=994 lang=java
 *
 * [994] 腐烂的橘子
 *
 * https://leetcode-cn.com/problems/rotting-oranges/description/
 *
 * algorithms
 * Easy (46.78%)
 * Likes:    124
 * Dislikes: 0
 * Total Accepted:    13.6K
 * Total Submissions: 27.6K
 * Testcase Example:  '[[2,1,1],[1,1,0],[0,1,1]]'
 *
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * 
 * 
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 
 * 
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * 
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 
 * 
 * 示例 2：
 * 
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 
 * 
 * 示例 3：
 * 
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 实际上是求所有腐烂橘子到新鲜橘子的最短距离
     * 所以用bfs
     */
    public int orangesRotting(int[][] grid) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // 表示新鲜橘子的数量
        int count = 0;
        // 遍历二维数组，找出所有的新鲜橘子和腐烂的橘子
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    count++;
                } else if (grid[i][j] == 2) {
                    // 腐烂的入队
                    queue.add(new int[]{i, j});
                }
            }
        }
        // round表示腐烂的分钟数或者轮数
        int round = 0;
        // 如果有新鲜橘子，并且队列不为空
        // 直到上下左右都触及边界 或者被感染的橘子已经被遍历完
        while (count > 0 && !queue.isEmpty()) {
            round++;
            // 每次处理一层
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int[] point = queue.poll();
                int x  = point[0], y = point[1];
                for (int k = 0; k < 4; k++) {
                    int newX = x + dx[k];
                    int newY = y + dy[k];
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        count--;
                        queue.add(new int[]{newX, newY});
                    }
                }
            }
        }

        if (count > 0) {
            return -1;
        } else {
            return round;
        }
    }
}
// @lc code=end

