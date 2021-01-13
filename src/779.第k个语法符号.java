/*
 * @lc app=leetcode.cn id=779 lang=java
 *
 * [779] 第K个语法符号
 *
 * https://leetcode-cn.com/problems/k-th-symbol-in-grammar/description/
 *
 * algorithms
 * Medium (42.98%)
 * Likes:    109
 * Dislikes: 0
 * Total Accepted:    15K
 * Total Submissions: 34.9K
 * Testcase Example:  '1\n1'
 *
 * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * 
 * 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 * 
 * 
 * 例子:
 * 
 * 输入: N = 1, K = 1
 * 输出: 0
 * 
 * 输入: N = 2, K = 1
 * 输出: 0
 * 
 * 输入: N = 2, K = 2
 * 输出: 1
 * 
 * 输入: N = 4, K = 5
 * 输出: 1
 * 
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 第三行: 0110
 * 第四行: 01101001
 * 
 * 
 * 
 * 注意：
 * 
 * 
 * N 的范围 [1, 30].
 * K 的范围 [1, 2^(N-1)].
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 暴力法超时 生成每一行字符串，每次只需要保存最后一行就可以了。
     */
    public int kthGrammar1(int N, int K) {
        int[] lastRow = new int[1 << N];
        for (int i = 1; i < N; i++) {
            // 只能倒着遍历，要不然会破坏前面的结构
            for (int j = (1 << (i - 1)) - 1; j >= 0; j--) {
                lastRow[2 * j] = lastRow[j];
                lastRow[2 * j + 1] = 1 - lastRow[j];
            }
        }
        return lastRow[K - 1];
    }

    public int kthGrammar2(int N, int k) {
        if (N == 1) {
            return 0;
        }
        if (k <= 1 << N - 2) {
            return kthGrammar(N - 1, k);
        }
        return kthGrammar(N - 1, k - (1 << N - 2)) ^ 1;
    }

    /**   
     * 找规律
     * 从N = 2开始，该行前一半的值等于前N-1行的值，后一半的值等于该行前一半的值按位取反
     */
    public int kthGrammar(int N, int K) {
        if (N == 1 || N == 0) {
            return 0;
        }
        // 因为第N行长度为2^(N - 1)，第N - 1行就为2^(N - 2)
        int N1 = (int)Math.pow(2, N - 2);
        if (K <= N1) {
            return kthGrammar(N - 1, K);
        }
        // 对结果取反
        return kthGrammar(N - 1, K - N1) == 1 ? 0 : 1;
    }
}
// @lc code=end
