/*
 * @lc app=leetcode.cn id=467 lang=java
 *
 * [467] 环绕字符串中唯一的子字符串
 *
 * https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string/description/
 *
 * algorithms
 * Medium (42.85%)
 * Likes:    141
 * Dislikes: 0
 * Total Accepted:    6.3K
 * Total Submissions: 14.6K
 * Testcase Example:  '"a"'
 *
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s
 * 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 
 * 
 * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p
 * 的不同的非空子串的数目。 
 * 
 * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: "a"
 * 输出: 1
 * 解释: 字符串 S 中只有一个"a"子字符。
 * 
 * 
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: "cac"
 * 输出: 2
 * 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 * 
 * 
 * 
 * 
 * 示例 3:
 * 
 * 
 * 输入: "zab"
 * 输出: 6
 * 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findSubstringInWraproundString(String p) {
        // 记录p中以每个字符结尾的最长连续子串的长度
        int[] dp = new int[26];
        char[] chars = p.toCharArray();
        // 记录当前连续子串的长度
        int count = 0;
        // 遍历p中的所有字符
        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && (chars[i] - chars[i - 1] - 1) % 26 == 0) {
                // 连续则自加
                count++;
            } else {
                // 不连续则刷新
                count = 1;
            }
            dp[chars[i] - 'a'] = Math.max(dp[chars[i] - 'a'], count);
        }
        int res = 0;
        // 统计所有以每个字符结尾的最长连续子串的长度，就是唯一相等子串的个数
        for (int i : dp) {
            res += i;
        }
        return res;
    }
}
// @lc code=end

