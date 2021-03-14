import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=316 lang=java
 *
 * [316] 去除重复字母
 *
 * https://leetcode-cn.com/problems/remove-duplicate-letters/description/
 *
 * algorithms
 * Medium (47.68%)
 * Likes:    475
 * Dislikes: 0
 * Total Accepted:    50.2K
 * Total Submissions: 105.3K
 * Testcase Example:  '"bcabc"'
 *
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 
 * 注意：该题与 1081
 * https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters
 * 相同
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
    public String removeDuplicateLetters1(String s) {
        Stack<Character> stack = new Stack<>();

        // this lets us keep track of what's in our solution in O(1) time
        HashSet<Character> seen = new HashSet<>();

        // this will let us know if there are any more instances of s[i] left in s
        HashMap<Character, Integer> last_occurrence = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            last_occurrence.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // we can only try to add c if it;s not already in our solution
            // this is to maintain only one of each character
            if (!seen.contains(c)) {
                // if the last letter in our solution"
                // 1. exists
                // 2. is greater than c so removing it will make the string smaller
                // 3. it's not the last occurence
                // we remove it from the solution to keep the soltuion optimal
                while (!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }

        }
        StringBuilder sBuilder = new StringBuilder(stack.size());
        for (Character c : stack) {
            sBuilder.append(c.charValue());
        }
        return sBuilder.toString();
    }

    /**
     * 这个是labuladong的题解，感觉还是这个比较清晰
     */
    public String removeDuplicateLetters(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        // 将大小设置为128，这样就不需要每次-'a'了
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        boolean[] inStack = new boolean[128];

        for (char c : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的字符--
            count[c]--;
            // 如果字符在栈中，直接跳过
            if (inStack[c]) {
                continue;
            }
            // 插入之前，和栈顶的元素比较一下，如果比栈顶的元素小，pop前面的元素
            // 维护的是单调递增栈
            while (!stack.isEmpty() && c < stack.peek()) {
                // 如果之后不存在栈顶元素了，就停止pop
                if (count[stack.peek()] == 0) {
                    break;
                }
                // 如果之后还有，可以pop
                char temp = stack.pop();
                inStack[temp] = false;
            }
            stack.push(c);
            inStack[c] = true;
        }

        StringBuilder sb = new StringBuilder(stack.size());
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
// @lc code=end
