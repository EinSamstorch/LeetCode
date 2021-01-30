/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (28.72%)
 * Likes:    1805
 * Dislikes: 0
 * Total Accepted:    193.7K
 * Total Submissions: 674.3K
 * Testcase Example:  '"babad"'
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 
 * 示例 1：
 * 
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 
 * 
 * 示例 2：
 * 
 * 输入: "cbbd"
 * 输出: "bb"
 * 
 * 
 */

// @lc code=start
class Solution {
    // 动态规划法
    public String longestPalindrome1(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j]表示s[i][j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    // 当子串s[i...j]的长度等于2或3的时候，只需要判断一下首尾就可以了
                    if (j - i + 1 < 4) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要dp[i][j] == true成立， 就表示子串s[i...j]是回文。
                // 记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    // 中心扩展法
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        String res = s.substring(0, 1);
        // 中心位置枚举到len - 2就可
        for (int i = 0; i < len - 1; i++) {
            // 以s[i]为中心的最长回文子串
            String oddStr = centerSpread(s, i, i);
            // 以s[i + 1]为中心的最长回文子串
            String evenStr = centerSpread(s, i, i + 1);
            // res = longest(res, oddStr, evenStr)
            res = res.length() > oddStr.length() ? res : oddStr;
            res = res.length() > evenStr.length() ? res : evenStr;
        }

        return res;
    }

    private String centerSpread(String s, int left, int right) {
        // left == right时，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1时，回文串的中心是一个空隙，回文串的长度是偶数
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        // 这里要小心，跳出while循环是，恰好满足s.charAt(left) != s.charAt(right)
        // 因此，不能取i，不能取j
        return s.substring(left + 1, right);
    }
}
// @lc code=end

