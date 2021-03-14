import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=402 lang=java
 *
 * [402] 移掉K位数字
 *
 * https://leetcode-cn.com/problems/remove-k-digits/description/
 *
 * algorithms
 * Medium (32.90%)
 * Likes:    525
 * Dislikes: 0
 * Total Accepted:    60.9K
 * Total Submissions: 185.1K
 * Testcase Example:  '"1432219"\n3'
 *
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 
 * 注意:
 * 
 * 
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 
 * 
 * 示例 1 :
 * 
 * 
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 
 * 
 * 示例 2 :
 * 
 * 
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 
 * 
 * 示例 3 :
 * 
 * 
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 这题竟然也可以用单调栈来实现
     */
    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char digit : num.toCharArray()) {
            // 对每个数字，如果该数字小于栈顶部，即该数字的左邻居，则弹出堆栈，否则入栈
            while (!stack.isEmpty() && k > 0 && digit < stack.peek()) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }

        // 如果给的string就是123456这种递增的顺序，不会弹出堆栈
        // 从栈顶移除剩下的数字
        for (int i = 0; i < k; i++) {
            stack.pop();
        }
        // 去除前导的0
        StringBuilder res = new StringBuilder();
        boolean leadingZero = true;
        while (!stack.isEmpty()) {
            // 因为栈是先进后出的，为了避免翻转栈，就使用了双向队列的方法
            // 注意，polllast才是移除第一个进栈的元素
            char digit = stack.pollLast();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            res.append(digit);
        }
        // 对应示例3的情况
        return res.length() == 0 ? "0" : res.toString();
    }
}
// @lc code=end

