/*
 * @lc app=leetcode.cn id=409 lang=java
 *
 * [409] 最长回文串
 *
 * https://leetcode-cn.com/problems/longest-palindrome/description/
 *
 * algorithms
 * Easy (55.44%)
 * Likes:    287
 * Dislikes: 0
 * Total Accepted:    75.9K
 * Total Submissions: 136.9K
 * Testcase Example:  '"abccccdd"'
 *
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * 
 * 示例 1: 
 * 
 * 
 * 输入:
 * "abccccdd"
 * 
 * 输出:
 * 7
 * 
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 自己写的，写错了，比如说出现a出现3次，可以从中拿出两个a
     */
    public  int longestPalindrome1(String s) {
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        // 将所有字符放入hashmap中
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int largestOdd = 0;

        for (int value : map.values()) {
            if (value % 2 == 0) {
                count += value;
            } else {
                largestOdd = Math.max(largestOdd, value);
            }
        }
        return count + largestOdd;
    }

    public int longestPalindrome2(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int ans = 0;
        for (int value : count) {
            ans += value / 2 * 2;
            if (value % 2 == 1 && ans % 2== 0) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 在对char数组遍历的时候 只能出现一个个数为奇数的字符 
     * 所以我们直接记录有多少个字符出现次数为奇数就可以了
     */
    public int longestPalindrome(String s) {
        // 找出可以构成最长回文串的长度
        int[] arr = new int[128];
        for(char c : s.toCharArray()) {
            arr[c]++;
        }
        int count = 0;
        for (int i : arr) {
            count += (i % 2);
        }
        return count == 0 ? s.length() : (s.length() - count + 1);
    }
}
// @lc code=end

