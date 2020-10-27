/*
 * @lc app=leetcode.cn id=524 lang=java
 *
 * [524] 通过删除字母匹配到字典里最长单词
 *
 * https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/description/
 *
 * algorithms
 * Medium (46.44%)
 * Likes:    102
 * Dislikes: 0
 * Total Accepted:    19.7K
 * Total Submissions: 42.4K
 * Testcase Example:  '"abpcplea"\n["ale","apple","monkey","plea"]'
 *
 * 
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * 
 * 示例 1:
 * 
 * 
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 
 * 输出: 
 * "apple"
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 * 
 * 输出: 
 * "a"
 * 
 * 
 * 说明:
 * 
 * 
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 * 
 * 
 */

// @lc code=start
class Solution {
    public String findLongestWord(String s, List<String> d) {
        String str = "";
        for (String sstr : d) {
            // 判断sstr是否是s的子串
            if (isSequence(s, sstr)) {
                // 比较当前的结果字符与找到的sstr字符，按照题目的需求来决定是否改变结果字符
                if (sstr.length() > str.length() || (sstr.length() == str.length() && str.compareTo(sstr) > 0)) {
                    str = sstr;
                }
            }
        }
        return str;
    }

    /**
     * 判断y是否是x的子串
     */
    public boolean isSequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < x.length() && j < y.length(); i++) {
            if (x.charAt(i) == y.charAt(j)) {
                j++;
            }
        }
        return j == y.length();
    }
}
// @lc code=end
