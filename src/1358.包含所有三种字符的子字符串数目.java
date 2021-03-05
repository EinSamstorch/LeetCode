/*
 * @lc app=leetcode.cn id=1358 lang=java
 *
 * [1358] 包含所有三种字符的子字符串数目
 *
 * https://leetcode-cn.com/problems/number-of-substrings-containing-all-three-characters/description/
 *
 * algorithms
 * Medium (46.40%)
 * Likes:    43
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 8.9K
 * Testcase Example:  '"abcabc"'
 *
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 * 
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "abcabc"
 * 输出：10
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "abc", "abca", "abcab", "abcabc", "bca", "bcab",
 * "bcabc", "cab", "cabc" 和 "abc" (相同字符串算多次)。
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "aaacb"
 * 输出：3
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "aaacb", "aacb" 和 "acb" 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：s = "abc"
 * 输出：1
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= s.length <= 5 x 10^4
 * s 只包含字符 a，b 和 c 。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfSubstrings(String s) {
        int ans = 0;
        // abc的计数
        int[] count = new int[3];
        int left = 0, right = 0;
        int len = s.length();
        char[] chars = s.toCharArray();

        while (right < len) {
            char curR = chars[right];
            count[curR - 'a']++;
            while (count[0] >= 1 && count[1] >= 1 && count[2] >= 1) {
                ans += len - right;
                char curL = chars[left];
                count[curL - 'a']--;
                left++;
            }
            right++;
        }
        return ans;
    }
}
// @lc code=end

