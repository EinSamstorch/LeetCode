/*
 * @lc app=leetcode.cn id=125 lang=java
 *
 * [125] 验证回文串
 *
 * https://leetcode-cn.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (47.11%)
 * Likes:    370
 * Dislikes: 0
 * Total Accepted:    223.4K
 * Total Submissions: 474.3K
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 
 * 示例 1:
 * 
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: "race a car"
 * 输出: false
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        char[] chars = s.toCharArray();

        while (left < right) {
            char l = chars[left];
            char r = chars[right];
            // 从头和尾开始向中间遍历，字符不是字母和数字的情况就跳过
            if (!Character.isLetterOrDigit(l)) {
                left++;
            } else if (!Character.isLetterOrDigit(r)) {
                right--;
            } else {
                // 判断两者是否相等
                if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                    // 不相等的话直接返回false
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
// @lc code=end

