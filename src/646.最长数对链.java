import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=646 lang=java
 *
 * [646] 最长数对链
 *
 * https://leetcode-cn.com/problems/maximum-length-of-pair-chain/description/
 *
 * algorithms
 * Medium (57.12%)
 * Likes:    156
 * Dislikes: 0
 * Total Accepted:    17K
 * Total Submissions: 29.7K
 * Testcase Example:  '[[1,2], [2,3], [3,4]]'
 *
 * 给出 n 个数对。 在每一个数对中，第一个数字总是比第二个数字小。
 * 
 * 现在，我们定义一种跟随关系，当且仅当 b < c 时，数对(c, d) 才可以跟在 (a, b) 后面。我们用这种形式来构造一个数对链。
 * 
 * 给定一个数对集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：[[1,2], [2,3], [3,4]]
 * 输出：2
 * 解释：最长的数对链是 [1,2] -> [3,4]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 给出数对的个数在 [1, 1000] 范围内。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findLongestChain1(int[][] pairs) {
        // 按照数对的第一个元素升序排序
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int rows = pairs.length;
        int ans = 1;
        
        for (int i = 0; i < rows; i++) {
            int length = 0;
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[j + 1][0]) {
                    length++;
                }
            }
            ans = Math.max(length, ans);
        }
        return ans;
    }

    /**
     * 贪心算法

     */
    public int findLongestChain2(int[][] pairs) {
        // 按照数对第二个数的升序排序
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int cur = Integer.MIN_VALUE;
        int ans = 0;

        for (int[] pair : pairs) {
            if (cur < pair[0]) {
                cur = pair[1];
                ans++;
            }
        }
        return ans;
    }

    /**
     * 动态规划
     */
    public int findLongestChain(int[][] pairs) {
        // 按照数对的第一个元素升序排序
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int len = pairs.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        int ans = 0;
        for (int value : dp) {
            ans = Math.max(ans, value);
        }
        return ans;
    }
}
// @lc code=end

