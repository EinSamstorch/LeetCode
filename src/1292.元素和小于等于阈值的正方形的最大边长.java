/*
 * @lc app=leetcode.cn id=1292 lang=java
 *
 * [1292] 元素和小于等于阈值的正方形的最大边长
 *
 * https://leetcode-cn.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/description/
 *
 * algorithms
 * Medium (45.71%)
 * Likes:    56
 * Dislikes: 0
 * Total Accepted:    4.6K
 * Total Submissions: 10K
 * Testcase Example:  '[[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]]\r\n4\r'
 *
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于或等于 4 的正方形的最大边长为 2，如图所示。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]],
 * threshold = 1
 * 输出：0
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * 输出：3
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold
 * = 40184
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * m == mat.length
 * n == mat[i].length
 * 0 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    // 二分查找
    public int maxSideLength1(int[][] mat, int threshold) {
        int rows = mat.length, cols = mat[0].length;
        int[][] prefix = new int[rows + 1][cols + 1];
        // 构造二维前缀和数组
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int left = 0, right = Math.min(rows, cols);

        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            boolean flag = false;
            for (int i = 1; i + mid <= rows + 1; i++) {
                for (int j = 1; j + mid <= cols + 1; j++) {
                    int temp = prefix[i + mid - 1][j + mid -1] -prefix[i + mid - 1][j - 1]
                                 - prefix[i - 1][j + mid - 1] + prefix[i - 1][j - 1];
                    if (temp <= threshold) {
                        flag = true;
                    }   
                }
            }
            if (flag) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int rows = mat.length, cols = mat[0].length;
        int[][] prefix = new int[rows + 1][cols + 1];
        // 构造二维前缀和数组
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                // mat[i - 1][j - 1]是因为 为了一次计算所有的前缀和，在前缀和的上边和左边补了一圈0，
                // 计算前缀和从（1,1）开始，而对应的mat应该为(0,0)，所以坐标减了1
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int ans = 0;
        for (int k = 1; k <= Math.min(rows, cols); k++) {
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    if (i - k < 0 || j - k < 0) {
                        continue;
                    }
                    int temp = prefix[i][j] - prefix[i - k][j] - prefix[i][j - k] + prefix[i - k][j - k];
                    if (temp <= threshold) {
                        ans = Math.max(ans, k);
                    }
                }
            }
        }
        return ans;
    }
}
// @lc code=end

