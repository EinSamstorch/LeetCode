import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=54 lang=java
 *
 * [54] 螺旋矩阵
 *
 * https://leetcode-cn.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (40.89%)
 * Likes:    450
 * Dislikes: 0
 * Total Accepted:    74K
 * Total Submissions: 181K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 *
 *
 * 示例 2:
 *
 * 输入:
 * [
 * ⁠ [1, 2, 3, 4],
 * ⁠ [5, 6, 7, 8],
 * ⁠ [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 */

// @lc code=start
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        // 特例
        if (matrix == null || matrix.length == 0) {
            return order;
        }
        // 初始化
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;
        int numEle = rows * columns;

        while (numEle >= 1) {
            // 左上到右上
            for (int i = left; i <= right && numEle >= 1; i++) {
                order.add(matrix[top][i]);
                numEle--;
            }
            top++;
            // 右上到右下
            for (int i = top; i <= bottom && numEle >= 1; i++) {
                order.add(matrix[i][right]);
                numEle--;
            }
            right--;
            // 右下到左下
            for (int i = right; i >= left && numEle >= 1; i--) {
                order.add(matrix[bottom][i]);
                numEle--;
            }
            bottom--;
            // 左下到左上
            for (int i = bottom; i >= top && numEle >= 1; i--) {
                order.add(matrix[i][left]);
                numEle--;
            }
            left++;

        }
        return order;
    }
}
// @lc code=end

