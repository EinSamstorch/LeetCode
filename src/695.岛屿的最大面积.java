import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=695 lang=java
 *
 * [695] 岛屿的最大面积
 *
 * https://leetcode-cn.com/problems/max-area-of-island/description/
 *
 * algorithms
 * Medium (64.17%)
 * Likes:    396
 * Dislikes: 0
 * Total Accepted:    66.9K
 * Total Submissions: 104.3K
 * Testcase Example:  '[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]'
 *
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * 
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被
 * 0（代表水）包围着。
 * 
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * 
 * 
 * 
 * 示例 1:
 * 
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,1,1,0,1,0,0,0,0,0,0,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,0,1,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,1,1,0,0],
 * ⁠[0,0,0,0,0,0,0,0,0,0,1,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 
 * 
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * 
 * 示例 2:
 * 
 * [[0,0,0,0,0,0,0,0]]
 * 
 * 对于上面这个给定的矩阵, 返回 0。
 * 
 * 
 * 
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * 
 */

// @lc code=start
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public int maxAreaOfIsland1(int[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(i, j, grid));
                }
            }
        }
        return res;
    }

    /**
     * 每次调用得时候默认num为1，进入后判断是不是岛屿，如果不是，则直接返回0，就可以避免预防错误的情况
     * 每次找到岛屿，则直接把找到的岛屿改为0，沉岛思想，遇到岛屿把他和周围的全都沉默。
     * ps：如果用陈导思想，那么自然可以用朋友圈思想。
     * @param i
     * @param j
     * @param grid
     * @return 岛屿数量
     */
    private int dfs(int i, int j, int[][] grid) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] ==0) {
            return 0;
        }
        // 避免重复访问
        grid[i][j] = 0;
        int area = 1;
        area += dfs(i + 1, j, grid);
        area += dfs(i - 1, j, grid);
        area += dfs(i, j - 1, grid);
        area += dfs(i, j + 1, grid);

        return area;
    }

    /**
     * 方法二：深度优先搜索+栈
     * 方法一通过函数的调用来表示接下来想要遍历哪些土地，让下一层函数来访问这些土地。而方法二把接下来想要遍历的土地放在栈里，然后在取出这些土地的时候访问它们。
     * 访问每一片土地时，我们将对围绕它四个方向进行探索，找到还未访问的土地，加入到栈 stack 中；
     * 另外，只要栈 stack 不为空，就说明我们还有土地待访问，那么就从栈中取出一个元素并访问。
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int cur = 0;
                Deque<Integer> stacki = new LinkedList<>(); 
                Deque<Integer> stackj = new LinkedList<>();
                stacki.push(i);
                stackj.push(j);

                while(!stacki.isEmpty()) {
                    int cur_i = stacki.pop();
                    int cur_j = stackj.pop();
                    if (cur_i < 0 || cur_j < 0 || cur_i >= grid.length || cur_j >= grid[0].length || grid[cur_i][cur_j] == 0) {
                        continue;
                    }
                    cur++;
                    grid[cur_i][cur_j] = 0;
                    int[] di = {0, 0, 1, -1};
                    int[] dj = {1, -1, 0, 0};
                    for (int index = 0; index < 4; index++) {
                        int next_i = cur_i + di[index];
                        int next_j = cur_j + dj[index];
                        stacki.push(next_i);
                        stackj.push(next_j);
                    }
                }
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }
}
// @lc code=end

