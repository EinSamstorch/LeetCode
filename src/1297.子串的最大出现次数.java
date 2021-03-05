import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=1297 lang=java
 *
 * [1297] 子串的最大出现次数
 *
 * https://leetcode-cn.com/problems/maximum-number-of-occurrences-of-a-substring/description/
 *
 * algorithms
 * Medium (44.70%)
 * Likes:    44
 * Dislikes: 0
 * Total Accepted:    5K
 * Total Submissions: 11.2K
 * Testcase Example:  '"aababcaab"\n2\n3\n4'
 *
 * 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：
 * 
 * 
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * 输出：2
 * 解释：子串 "aab" 在原字符串中出现了 2 次。
 * 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * 输出：2
 * 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 * 
 * 
 * 示例 3：
 * 
 * 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * 输出：3
 * 
 * 
 * 示例 4：
 * 
 * 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s 只包含小写英文字母。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxFreq1(String s, int maxLetters, int minSize, int maxSize) {
        char[] chars = s.toCharArray();
        int left = 0, right = 0;
        int ans = 0;
        int count = 0;
        int[] freq = new int[26];

        while (right < s.length()) {
            int indexR = chars[right] - 'a';
            if (freq[indexR] == 0) {
                count++;
            }
            freq[indexR]++;
            right++;
            int len = right - left;
            if (len < minSize) {
                continue;
            }

            while (count > maxLetters && len > maxLetters) {
                int indexL = chars[left] - 'a';
                freq[indexL]--;
                if (freq[indexL] == 0) {
                    count--;
                }
                left++;
            }
            ans += 1;
        }
        return ans;
    }

    /**
     * 官方题解1  穷举法
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int len = s.length();
        Map<String, Integer> map = new HashMap<>();
        int ans = 0;
        char[] chars = s.toCharArray();
        
        for (int i = 0; i < len; i++) {
            Set<Character> exist = new HashSet<>();
            String cur = "";
            for (int j = i; j < Math.min(len, i + maxSize); j++) {
                exist.add(chars[j]);
                if (exist.size() > maxLetters) {
                    break;
                }
                cur += chars[j];
                if (j - i + 1 >= minSize) {
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                    ans = Math.max(ans, map.get(cur));
                }
            }
        }
        return ans;
    }




}
// @lc code=end

