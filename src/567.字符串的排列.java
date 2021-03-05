import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=567 lang=java
 *
 * [567] 字符串的排列
 *
 * https://leetcode-cn.com/problems/permutation-in-string/description/
 *
 * algorithms
 * Medium (37.22%)
 * Likes:    172
 * Dislikes: 0
 * Total Accepted:    41.6K
 * Total Submissions: 111.6K
 * Testcase Example:  '"ab"\n"eidbaooo"'
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 *
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 *
 *
 *
 * 示例2:
 *
 *
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 *
 *
 *
 * 注意：
 *
 *
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 *
 */

// @lc code=start
class Solution {

    /**
     * 只有两个字符串包含相同次数的相同字符时，一个字符串才是另一个字符串的排列。 只有sorted(x) ==
     * sorted(y)时，一个字符串x才是其他字符串y的排列。
     */
    public boolean checkInclusion1(String s1, String s2) {
        s1 = sort(s1);

        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            if (s1.equals(sort(s2.substring(i, i + s1.length())))) {
                return true;
            }
        }
        return false;
    }

    public String sort(String s) {
        char[] t = s.toCharArray();
        Arrays.sort(t);
        return new String(t);
    }

    /**
     * 使用字符数组储存字符出现的频率
     */
    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        // 储存字符出现的频率
        int[] s1map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] s2map = new int[26];
            for (int j = 0; j < s1.length(); j++) {
                s2map[s2.charAt(i + j) - 'a']++;
            }
            if (matches(s1map, s2map)) {
                return true;
            }
        }
        return false;
    }

    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不需要每次更新全部的s2map，只需要更新左右两个就行，滑动窗口
     */
    public boolean checkInclusion3(String s1, String s2) {
        if (s1.length() > s2.length()){
            return false;
         }   
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map)){
                return true;
            }
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);

    }

    public boolean checkInclusion4(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        // 数组长度初始化为128，省去减'a'的功夫
        int[] needs = new int[128];
        int[] inWindow = new int[128];
        // 记录s1有多少种字母
        Set<Character> set =  new HashSet<>();
        for (char ch : s1.toCharArray()) {
            needs[ch]++;
            set.add(ch);
        }
        int needCharNums = set.size();

        // 关键，维护一个大小为s1.length（）的滑动窗口，窗口内的字幕数量对应及合法
        int windowLen = s1.length();
        // 通过变量valid，指示窗口内已满足条件的字母数量，省去每次扫描26个字母数量是否想等
        int valid = 0;

        int left = 0, right = 0;
        while (right < s2.length()) {
            char cur = s2.charAt(right);
            right++;
            inWindow[cur]++;
            // 细节1：两个if条件必须对应，用==比较好理解
            if (inWindow[cur] == needs[cur]) {
                valid++;
            }
            if (valid == needCharNums) {
                return true;
            }
            // 如果窗口还没有完成初始化，[0, windowLen] --- [left, right)（因为right++了）
            if (right < windowLen) {
                continue;
            }

            char leftChar = s2.charAt(left);
            left++;
            if (inWindow[leftChar] == needs[leftChar]) {
                valid--;
            }
            inWindow[leftChar]--;
        }
        return false;
    }

    public boolean checkInclusion5(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int[] needs = new int[26];
        int[] window = new int[26];
        // needs数组用来统计s1中字符出现的频率
        for (char ch : chars1) {
            needs[ch - 'a']++;
        }
        int left = 0, right = 0;
        
        while (right < s2.length()) {
            int curR = chars2[right] - 'a';
            window[curR]++;
            right++;
            
            while (window[curR] > needs[curR]) {
                int curL = chars2[left] - 'a';
                window[curL]--;
                left++;
            }
            if (right - left == s1.length()) {
                return true;
            }
        }
        return false;

    }

    /**
     * 该方法与上一个方法的唯一区别就是将new int[26] 换成了new int[128]
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        // 用128的话，就省去了-'a'的步骤，用空间换时间
        int[] needs = new int[128];
        int[] window = new int[128];
        // needs数组用来统计s1中字符出现的频率
        for (char ch : chars1) {
            needs[ch]++;
        }
        int left = 0, right = 0;
        
        while (right < s2.length()) {
            int curR = chars2[right];
            window[curR]++;
            right++;
            // 只要window中有一个不符合，就是不符合
            while (window[curR] > needs[curR]) {
                int curL = chars2[left];
                window[curL]--;
                left++;
            }
            if (right - left == s1.length()) {
                return true;
            }
        }
        return false;

    }

}
// @lc code=end
