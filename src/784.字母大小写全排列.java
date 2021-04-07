import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=784 lang=java
 *
 * [784] 字母大小写全排列
 *
 * https://leetcode-cn.com/problems/letter-case-permutation/description/
 *
 * algorithms
 * Medium (66.76%)
 * Likes:    257
 * Dislikes: 0
 * Total Accepted:    30.9K
 * Total Submissions: 46.2K
 * Testcase Example:  '"a1b2"'
 *
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * 
 * 
 * 
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * 
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 * 
 * 输入：S = "12345"
 * 输出：["12345"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 这一题用的回溯法,但是我还没完全搞懂
     * 等刷完回溯法的题目后,再来看这一道题
     */
    List<String> res = new ArrayList<>();
    public List<String> letterCasePermutation(String str) {
        char[] chars = str.toCharArray();
        dfs(chars, 0);
        return res;
    }

    private void dfs(char[] chars, int index) {
        if (index == chars.length) {
            res.add(new String(chars));
            return;
        }
        dfs(chars, index + 1);

        if (Character.isLetter(chars[index])) {
            // 转换大小写的一种便捷方式
            chars[index] ^= 32;
            dfs(chars, index + 1);
        } 
    }

}
// @lc code=end

