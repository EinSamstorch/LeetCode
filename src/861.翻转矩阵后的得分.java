/*
 * @lc app=leetcode.cn id=861 lang=java
 *
 * [861] 翻转矩阵后的得分
 *
 * https://leetcode-cn.com/problems/score-after-flipping-matrix/description/
 *
 * algorithms
 * Medium (74.29%)
 * Likes:    83
 * Dislikes: 0
 * Total Accepted:    5.9K
 * Total Submissions: 8K
 * Testcase Example:  '[[0,0,1,1],[1,0,1,0],[1,1,0,0]]'
 *
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * 
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * 
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * 
 * 返回尽可能高的分数。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int matrixScore1(int[][] A) {
        int m = A.length, n = A[0].length;
        int ret = m * ( 1 << (n - 1));

        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 1) {
                    nOnes += A[i][j];
                } else {
                    // 如果这一行进行了反转，则该元素的实际取值为1 - A[i][j]
                    nOnes += (1 - A[i][j]);
                }
            }
            int k = Math.max(nOnes, m - nOnes);
            ret += k * (1 << (n - j - 1));
        }
        return ret;
    }

    /**
     * 这个写的比官方题解通俗易懂多了
     */
    public int matrixScore(int[][] A) {
        // 1. 将第一列全部都置为1
        for (int[] a : A) {
            if (a[0] == 0) {
                for (int i = 0; i < a.length; i++) {
                    a[i] = a[i] == 0 ? 1 : 0;
                }
            }
        }
        int res = 0;
        // 2.从第一列开始，计算每一列1的个数count
        // i代表的是列，j代表的是行
        for (int i = 0; i < A[0].length; i++) {
            int count = 0; 
            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 1) {
                    count++;
                }
            }
            // 3. 如果count<=A行数的一般，则说明需要翻转
            if (count <= A.length / 2) {
                count = A.length - count;
            }
            // 4. 根据每一列1的个数计算结果
            res += count * Math.pow(2, A[0].length - i - 1);
        }
        return res;
    }
}
// @lc code=end

