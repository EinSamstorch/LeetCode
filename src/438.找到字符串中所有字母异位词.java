import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=438 lang=java
 *
 * [438] 找到字符串中所有字母异位词
 *
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/description/
 *
 * algorithms
 * Medium (49.38%)
 * Likes:    479
 * Dislikes: 0
 * Total Accepted:    56.1K
 * Total Submissions: 113.6K
 * Testcase Example:  '"cbaebabacd"\n"abc"'
 *
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * 
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 
 * 说明：
 * 
 * 
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 
 * 输出:
 * [0, 6]
 * 
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入:
 * s: "abab" p: "ab"
 * 
 * 输出:
 * [0, 1, 2]
 * 
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();

        int[] map = new int[26];
        for (char c : pChars) {
            int index = c - 'a';
            map[index]++;
        }
        int pLen = p.length();
        int sLen = s.length();

        for (int i = 0; i + pLen <= sLen; i++) {
            int count = pLen;
            int[] temp = map.clone();
            for (int j = i; j - i + 1 <= pLen; j++) {
                int index = sChars[j] - 'a';
                if (temp[index] > 0) {
                    temp[index]--;
                    count--;
                }
                if (count == 0) {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams(String s, String p) {
        char[] arrS = s.toCharArray();
        char[] arrP = p.toCharArray();
        List<Integer> ans = new ArrayList<>();

        // 定义一个needs数组来看arrp中包含的元素的个数
        int[] needs = new int[26];
        // 定义一个window数组来看滑动窗口中是否有arrp中的元素，并记录出现的个数
        int[] window = new int[26];

        for (char ch : arrP) {
            needs[ch - 'a']++;
        }
        int left = 0, right = 0;

        while (right < arrS.length) {
            int curR = arrS[right] - 'a';
            // 将右窗口当前访问到的元素curR个数加一
            window[curR]++;
            // 右边界右移一格
            right++;
            // 自己之前是比较了所有在p中出现的字符有没有出现在s中
            // 实际上只要有一个不符合，那就不符合
            while (window[curR] > needs[curR]) {
                int curL = arrS[left] - 'a';
                window[curL]--;
                left++;
            }
            if (right - left == arrP.length) {
                ans.add(left);
            }
        }
        return ans;
    }
}
// @lc code=end

