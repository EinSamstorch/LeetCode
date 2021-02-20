import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=542 lang=java
 *
 * [542] 01 矩阵
 *
 * https://leetcode-cn.com/problems/01-matrix/description/
 *
 * algorithms
 * Medium (45.29%)
 * Likes:    379
 * Dislikes: 0
 * Total Accepted:    46.3K
 * Total Submissions: 102.3K
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 
 * 两个相邻元素间的距离为 1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[0,0,0]]
 * 
 * 输出：
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[1,1,1]]
 * 
 * 输出：
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[1,2,1]]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 对于Tree的BFS遍历（单源BFS），只要将root入队列，然后一层一层无脑遍历就好
     * 对于图（多源BFS）：
     *      1.需要将多个源点都入队  
     *      2. Tree是有向的，不需要标记是否访问过，而图是无向的，需要标记
     *      并且为了防止某个节点多次入队，需要在入队之前就将其标记为已访问
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        
        // 首先将所有的0都入队列，并且将1的位置设置为-1，表示该位置是 未被访问过的1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            } 
        }

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 如果四领域的点是-1，表示这个点是未被访问过的1
                // 所以这个点到0的距离就可以更新成matrix[x][y] + 1.
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && matrix[newX][newY] == -1) {
                    matrix[newX][newY] = matrix[x][y] + 1;
                    queue.offer(new int[] {newX, newY});
                }
            }
        }
        return matrix;
    }
}
// @lc code=end

