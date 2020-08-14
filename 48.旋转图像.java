/*
 * @lc app=leetcode.cn id=48 lang=java
 *
 * [48] 旋转图像
 *
 * https://leetcode-cn.com/problems/rotate-image/description/
 *
 * algorithms
 * Medium (68.47%)
 * Likes:    524
 * Dislikes: 0
 * Total Accepted:    89.8K
 * Total Submissions: 130K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 * ⁠ [1,2,3],
 * ⁠ [4,5,6],
 * ⁠ [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 * ⁠ [7,4,1],
 * ⁠ [8,5,2],
 * ⁠ [9,6,3]
 * ]
 *
 *
 * 示例 2:
 *
 * 给定 matrix =
 * [
 * ⁠ [ 5, 1, 9,11],
 * ⁠ [ 2, 4, 8,10],
 * ⁠ [13, 3, 6, 7],
 * ⁠ [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 * ⁠ [15,13, 2, 5],
 * ⁠ [14, 3, 4, 1],
 * ⁠ [12, 6, 8, 9],
 * ⁠ [16, 7,10,11]
 * ]
 *
 *
 */

// @lc code=start
class Solution {
    /**
     * 矩阵转置+翻转
     *
     * @param matrix 待旋转的矩阵
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 矩阵转置
        for (int row = 0; row < n; row++) {
            for (int col = row; col < n; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }
        // 矩阵翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }

    /**
     * 分层旋转，从最外层开始
     *
     * @param matrix 待旋转的矩阵
     */
    public void rotate(int[][] matrix) {
        int pos1 = 0;
        int pos2 = matrix.length - 1;
        while (pos1 < pos2) {
            int add = 0;
            while (add < pos2 - pos1) {
                // 左上角为0块，右上角为1块，右下角为2块，左下角为3块
                int temp = matrix[pos1][pos1 + add];
                // 1 = 2
                matrix[pos1][pos1 + add] = matrix[pos2 - add][pos1];
                // 3 = 2
                matrix[pos2 - add][pos1] = matrix[pos2][pos2 - add];
                // 2 = 1
                matrix[pos2][pos2 - add] = matrix[pos1 + add][pos2];
                // 1 = temp
                matrix[pos1 + add][pos2] = temp;
                add++;
            }
            pos1++;
            pos2--;

        }
    }

}
// @lc code=end

