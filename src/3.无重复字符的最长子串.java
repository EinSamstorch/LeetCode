import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (35.55%)
 * Likes:    4282
 * Dislikes: 0
 * Total Accepted:    649.4K
 * Total Submissions: 1.8M
 * Testcase Example:  '"abcabcbb"'
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 
 * 示例 1:
 * 
 * 输入: "abcabcbb"
 * 输出: 3 
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 
 * 
 * 示例 2:
 * 
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 
 * 
 * 示例 3:
 * 
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 
 * 
 */

// @lc code=start
class Solution {
    public int lengthOfLongestSubstring1(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int maxLen = 0;

        // 就两次遍历，暴力解决。
        for (int i = 0; i < len; i++) {
            Set<Character> visited = new HashSet<>();
            for (int j = i; j < len; j++) {
                if (visited.contains(chars[j])) {
                    break;
                } else {
                    visited.add(chars[j]);
                }
            }
            maxLen = Math.max(maxLen, visited.size());
            visited.clear();
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring2(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        // 右指针
        int right = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();

        for (int left = 0; left < len; left++) {
            if (left != 0) {
                // 左指针向右移动一格，移除一个字符
                set.remove(chars[left - 1]);
            }
            while (right < len && !set.contains(chars[right])) {
                // 不断向右移动指针
                set.add(chars[right]);
                right++;
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int len = s.length();
        int left = 0, right = 0;

        int[] window = new int[128];
        int res = 0;

        while (right < len) {
            char curChar = chars[right];
            window[curChar]++;
            right++;
            // 只要出现了重复，窗口右边界停止，左边界向右移动，直到滑动窗口内没有重复的元素
            while (window[curChar] == 2) {
                // 左边界向右走
                char leftChar = chars[left];
                window[leftChar]--;
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
// @lc code=end

