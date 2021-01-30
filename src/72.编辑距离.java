/*
 * @lc app=leetcode.cn id=72 lang=java
 *
 * [72] 编辑距离
 *
 * https://leetcode-cn.com/problems/edit-distance/description/
 *
 * algorithms
 * Hard (56.45%)
 * Likes:    554
 * Dislikes: 0
 * Total Accepted:    30.8K
 * Total Submissions: 54.6K
 * Testcase Example:  '"horse"\n"ros"'
 *
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 
 * 你可以对一个单词进行如下三种操作：
 * 
 * 
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 
 * 
 * 示例 1:
 * 
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释: 
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 
 * 
 * 示例 2:
 * 
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释: 
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * 
 * 
 */

// @lc code=start
class Solution {
    // 只讨论word1 -> word2
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // 多开一行一列是为了保存边界条件，即字符长度为0的情况
        // 这一点在字符串的动态规划里很常见
        int[][] dp = new int[len1 + 1][len2 + 1];
        // 初始化，当word2长度为0的时候，将word1的单词全部删除即可
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 初始化，当word1长度为0的时候，将word2的单词全部删除即可
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        // 由于 word1.charAt(i) 操作会去检查下标是否越界，因此在 Java 里，将字符串转换成字符数组是常见操作
        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();

        // 递推开始，注意：填写dp数组的时候，由于初始化的时候，多设置了一行一列，横纵坐标有个偏移
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1Array[i -1] == word2Array[j - 1]) {
                    // 这是最好的情况，不需要操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 否则在以下三种情况里选择步骤最少的，这是动态规划的[最优子结构]
                    // 1. 在下标i处插入一个字符
                    int insert = dp[i][j - 1] + 1;
                    // 2. 替换一个字符
                    int replace = dp[i - 1][j - 1] + 1;
                    // 3. 下标i处删掉一个字符
                    int delete = dp[i - 1][j] + 1;
                    dp[i][j] = Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        return dp[len1][len2];
    }
}
// @lc code=end

