/*
 * @lc app=leetcode.cn id=992 lang=java
 *
 * [992] K 个不同整数的子数组
 *
 * https://leetcode-cn.com/problems/subarrays-with-k-different-integers/description/
 *
 * algorithms
 * Hard (44.71%)
 * Likes:    275
 * Dislikes: 0
 * Total Accepted:    20.7K
 * Total Submissions: 46.1K
 * Testcase Example:  '[1,2,1,2,3]\n2'
 *
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定不同的子数组为好子数组。
 * 
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * 
 * 返回 A 中好子数组的数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2],
 * [1,2,1,2].
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * @return 最多包含K个不同整数得子区间的个数
     */
    private int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];
        int left = 0, right = 0;
        // [left, right)里不同的整数
        int count = 0;
        int res = 0;
        // [left, right)包含不同整数的个数小于等于K
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left, right)区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }
}
// @lc code=end

