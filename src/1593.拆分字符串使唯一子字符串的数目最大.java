import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Flow.Subscriber;
import java.util.zip.DataFormatException;

import jdk.nashorn.internal.codegen.SpillObjectCreator;

/*
 * @lc app=leetcode.cn id=1593 lang=java
 *
 * [1593] 拆分字符串使唯一子字符串的数目最大
 *
 * https://leetcode-cn.com/problems/split-a-string-into-the-max-number-of-unique-substrings/description/
 *
 * algorithms
 * Medium (50.31%)
 * Likes:    23
 * Dislikes: 0
 * Total Accepted:    4.3K
 * Total Submissions: 8.5K
 * Testcase Example:  '"ababccc"'
 *
 * 给你一个字符串 s ，请你拆分该字符串，并返回拆分后唯一子字符串的最大数目。
 * 
 * 字符串 s 拆分后可以得到若干 非空子字符串 ，这些子字符串连接后应当能够还原为原字符串。但是拆分出来的每个子字符串都必须是 唯一的 。
 * 
 * 注意：子字符串 是字符串中的一个连续字符序列。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：s = "ababccc"
 * 输出：5
 * 解释：一种最大拆分方法为 ['a', 'b', 'ab', 'c', 'cc'] 。像 ['a', 'b', 'a', 'b', 'c', 'cc']
 * 这样拆分不满足题目要求，因为其中的 'a' 和 'b' 都出现了不止一次。
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "aba"
 * 输出：2
 * 解释：一种最大拆分方法为 ['a', 'ba'] 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：s = "aa"
 * 输出：1
 * 解释：无法进一步拆分字符串。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 
 * 1 <= s.length <= 16
 * 
 * 
 * s 仅包含小写英文字母
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    int maxSplit = 1;

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        dfs(0, 0, s, set);
        return maxSplit;
    }

    private void dfs(int index, int split, String s, Set<String> set) {
        int len = s.length();
        if (index >= len) {
            maxSplit = Math.max(maxSplit, split);
        }
         else {
             for (int i = index; i < len; i++) {
                 String subStr = s.substring(index, i + 1);
                 if (set.add(subStr)) {
                     dfs(i + 1, split + 1, s, set);
                     set.remove(subStr);
                 }
             }
         }
    }
}
// @lc code=end

