import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=1081 lang=java
 *
 * [1081] 不同字符的最小子序列
 *
 * https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/description/
 *
 * algorithms
 * Medium (57.15%)
 * Likes:    87
 * Dislikes: 0
 * Total Accepted:    10.3K
 * Total Submissions: 18K
 * Testcase Example:  '"bcabc"'
 *
 * 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 * 
 * 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "bcabc"
 * 输出："abc"
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * s 由小写英文字母组成
 * 
 * 
 */

// @lc code=start
class Solution {
    public String smallestSubsequence(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int[] count = new int[128];
        char[] chars = s.toCharArray();
        // 统计字符出现次数
        for (char c : chars) {
            count[c]++;
        }
        boolean[] inStack = new boolean[128];

        for (char c : chars) {
            // 每遍历一个字符，都将字符c出现次数减一
            count[c]--;
            // 如果已经在栈中了，就跳过
            if (inStack[c]) {
                continue;
            }
            while (!stack.isEmpty() && c < stack.peek()) {
                // 如果这个字符后面没有出现过了，就不用pop了
                if (count[stack.peek()] == 0) {
                    break;
                }
                char temp = stack.pop();
                inStack[temp] = false;
            }
            stack.push(c);
            inStack[c] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
// @lc code=end

