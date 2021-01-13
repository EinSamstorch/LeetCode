/*
 * @lc app=leetcode.cn id=808 lang=java
 *
 * [808] 分汤
 *
 * https://leetcode-cn.com/problems/soup-servings/description/
 *
 * algorithms
 * Medium (46.31%)
 * Likes:    50
 * Dislikes: 0
 * Total Accepted:    3.2K
 * Total Submissions: 7K
 * Testcase Example:  '50'
 *
 * 有 A 和 B 两种类型的汤。一开始每种类型的汤有 N 毫升。有四种分配操作：
 * 
 * 
 * 提供 100ml 的汤A 和 0ml 的汤B。
 * 提供 75ml 的汤A 和 25ml 的汤B。
 * 提供 50ml 的汤A 和 50ml 的汤B。
 * 提供 25ml 的汤A 和 75ml 的汤B。
 * 
 * 
 * 
 * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为0.25的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
 * 
 * 注意不存在先分配100 ml汤B的操作。
 * 
 * 需要返回的值： 汤A先分配完的概率 + 汤A和汤B同时分配完的概率 / 2。
 * 
 * 
 * 示例:
 * 输入: N = 50
 * 输出: 0.625
 * 解释:
 * 如果我们选择前两个操作，A将首先变为空。对于第三个操作，A和B会同时变为空。对于第四个操作，B将首先变为空。
 * 所以A变为空的总概率加上A和B同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
 * 
 * 
 * 注释: 
 * 
 * 
 * 0 <= N <= 10^9。
 * 
 * 返回值在 10^-6 的范围将被认为是正确的。
 * 
 * 
 * 
 */

// @lc code=start
class Solution {

    /**   
     * 动态规划
     */
    public double soupServings(int N) {
        N = N / 25 + (N % 25 > 0 ? 1 : 0);
        // 汤A平均减少2.5份，汤B平均减少1.5份，当N > 500 * 25时， 所求概率已经大于0.999999了
        if (N > 500) {
            return 1.0;
        }
        // dp(i, j) 表示汤 A 和汤 B 分别剩下 i 和 j 份时，所求的概率值。状态转移方程为：
        // dp(i, j) = 1/4 * (dp(i - 4, y) + dp(i - 3, y - 1) + dp(i - 2, y - 2) + dp(i - 1, y - 3))
        double[][] memo = new double[N + 1][N + 1];
        for (int s = 0; s <= 2 * N; s++) {
            for (int i = 0; i <= N; i++) {
                int j = s - i;
                if (j < 0 || j > N) {
                    continue;
                }
                double ans = 0.0;
                if (i == 0) {
                    ans = 1.0;
                }
                if (i == 0 && j == 0) {
                    ans = 0.5;
                }
                if (i > 0 && j > 0) {
                    ans = 0.25 * (memo[M(i - 4)][j] + memo[M(i -3)][M(j - 1)] + memo[M(i - 2)][M(j - 2)] + memo[M(i - 1)][M(j - 3)]);
                }
                memo[i][j] = ans;
            }
        }
        return memo[N][N];

    }

    private int M(int i) {
        return Math.max(0, i);
    }

    /**  
     * 递归的方法， 好像时间超时了
     * Base Cases:
        dp[0][0] = 0.5
        dp[i][0] = 0.0, dp[0][i] = 1.0 (i >= 1 && i <= N)

        dp[i][j] means the possibility that A first severd up
        dp[i][j] = 0.25 *(dp[M(i - 4)][j] + dp[M(i - 3)][M(j - 1)] +
        dp[M(i - 2)][M(j - 2)] + dp[M(i - 1)][M(j - 3)]);
     */
    public double soupServings1(int N) {
        if (N > 10000) {
            return 1.0;
        }

        return helper(N / 25.0, N / 25.0);
    }

    private double helper(double A, double B) {
        if (A <= 0 && B <= 0) {
            return 0.5;
        } else if (A <= 0) {
            return 1.0;
        } else if (B <= 0) {
            return 0.0;
        } else {
            return 0.25 * (helper(A -4, B) + helper(A - 3, B -1) + helper(A - 2, B - 2) + helper(A - 1, B - 3));
        }
    }
}
// @lc code=end

