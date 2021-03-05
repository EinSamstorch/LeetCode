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
    public static int characterReplacement1(String s, int k) {
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
            right++;
            //那么这个题的关键点就是我们如何判断一个字符串改变K个字符，能够变成一个连续串
            //如果当前字符串中的出现次数最多的字母个数+K大于串长度，那么这个串就是满足条件的
            if (right - left > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
            // 当窗口内可替换的字符数小于等于k时，我们需要根据该窗口长度来确定是否更新result
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    public static int characterReplacement(String s, int k) {
        int len = s.length();
        if (len < 2) {
            return len;
        }
        int[] map = new int[26];
        char[] chars = s.toCharArray();
        int left = 0, right = 0;
        int ans = 0;
        int historyCharMax = 0;

        while (right < len) {
            int index = chars[right] - 'A';
            // 对状态作修改
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);
            right++;
            // 这边的if可以换成while，反正里面的数据只会执行一次
            if (right - left > historyCharMax + k) {
                // 说明此时k不够用，将其他不是最多出现次数的字符替换以后，都不能填满这个滑动的窗口
                // 这时候必须要考虑左边界收缩的问题
                map[chars[left] - 'A']--;
                left++;
            }
            // ans = Math.max(ans, right - left);
        }
        return right - left;
    }

}
// @lc code=end

