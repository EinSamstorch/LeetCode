/*
 * @lc app=leetcode.cn id=424 lang=java
 *
 * [424] 替换后的最长重复字符
 *
 * https://leetcode-cn.com/problems/longest-repeating-character-replacement/description/
 *
 * algorithms
 * Medium (48.38%)
 * Likes:    161
 * Dislikes: 0
 * Total Accepted:    11.3K
 * Total Submissions: 23.3K
 * Testcase Example:  '"ABAB"\n2'
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k
 * 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 
 * 注意:
 * 字符串长度 和 k 不会超过 10^4。
 * 
 * 示例 1:
 * 
 * 输入:
 * s = "ABAB", k = 2
 * 
 * 输出:
 * 4
 * 
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * s = "AABABBA", k = 1
 * 
 * 输出:
 * 4
 * 
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * 
 * 
 */

// @lc code=start
class Solution {
    public static int characterReplacement(String s, int k) {
        int[] map = new int[26];
        if (s == null) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = 0, ans = 0;
        int historyCharMax = 0;

        while (right < s.length()) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);

            //那么这个题的关键点就是我们如何判断一个字符串改变K个字符，能够变成一个连续串
            //如果当前字符串中的出现次数最多的字母个数+K大于串长度，那么这个串就是满足条件的
            if (right - left + 1 > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
            // 当窗口内可替换的字符数小于等于k时，我们需要根据该窗口长度来确定是否更新result
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}
// @lc code=end

