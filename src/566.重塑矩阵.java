/*
 * @lc app=leetcode.cn id=566 lang=java
 *
 * [566] 重塑矩阵
 *
 * https://leetcode-cn.com/problems/reshape-the-matrix/description/
 *
 * algorithms
 * Easy (65.46%)
 * Likes:    139
 * Dislikes: 0
 * Total Accepted:    23.3K
 * Total Submissions: 35.6K
 * Testcase Example:  '[[1,2],[3,4]]\n1\n4'
 *
 * 在MATLAB中，有一个非常有用的函数 reshape，它可以将一个矩阵重塑为另一个大小不同的新矩阵，但保留其原始数据。
 * 
 * 给出一个由二维数组表示的矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 * 
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 * 
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 * 
 * 示例 1:
 * 
 * 
 * 输入: 
 * nums = 
 * [[1,2],
 * ⁠[3,4]]
 * r = 1, c = 4
 * 输出: 
 * [[1,2,3,4]]
 * 解释:
 * 行遍历nums的结果是 [1,2,3,4]。新的矩阵是 1 * 4 矩阵, 用之前的元素值一行一行填充新矩阵。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: 
 * nums = 
 * [[1,2],
 * ⁠[3,4]]
 * r = 2, c = 4
 * 输出: 
 * [[1,2],
 * ⁠[3,4]]
 * 解释:
 * 没有办法将 2 * 2 矩阵转化为 2 * 4 矩阵。 所以输出原矩阵。
 * 
 * 
 * 注意：
 * 
 * 
 * 给定矩阵的宽和高范围在 [1, 100]。
 * 给定的 r 和 c 都是正数。
 * 
 * 
 */

// @lc code=start
class Solution {
    // 自己想的暴力算法
    public int[][] matrixReshape1(int[][] nums, int r, int c) {
        int rows = nums.length;
        int cols = nums[0].length;
        // 不符合条件的直接返回
        if (r * c != rows * cols) {
            return nums;
        }
        int[][] ans = new int[r][c];
        int[] temp = new int[rows * cols];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp[index++] = nums[i][j];
            }
        }

        index = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ans[i][j] = temp[index++];
            }
        }
        return ans;
    }

    // 太秀了，通过对列值取余，取模来确定行，列。
    // 两个二维数组一并线性映射
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length;
        int cols = nums[0].length;
        // 不符合条件的直接返回
        if (r * c != rows * cols) {
            return nums;
        }
        int count = 0;
        int[][] ans = new int[r][c];

        while (count < r * c) {
            ans[count / c][count % c] = nums[count / cols][count % cols];
            count++;
        }
        return ans;
    }

}
// @lc code=end
