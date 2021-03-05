/*
 * @lc app=leetcode.cn id=925 lang=java
 *
 * [925] 长按键入
 *
 * https://leetcode-cn.com/problems/long-pressed-name/description/
 *
 * algorithms
 * Easy (39.23%)
 * Likes:    189
 * Dislikes: 0
 * Total Accepted:    46.2K
 * Total Submissions: 117.9K
 * Testcase Example:  '"alex"\n"aaleex"'
 *
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * 
 * 
 * 示例 2：
 * 
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 * 
 * 
 * 示例 3：
 * 
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 * 
 * 
 * 示例 4：
 * 
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * name.length <= 1000
 * typed.length <= 1000
 * name 和 typed 的字符都是小写字母。
 * 
 * 
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 这种方法无法保证顺序
     */
    public boolean isLongPressedName1(String name, String typed) {
        char[] nameChars = name.toCharArray();
        char[] typedChars = typed.toCharArray();
        int[] need = new int[128];
        int[] have = new int[128];
        for (char ch : nameChars) {
            need[ch]++;
        }
        int  index = 0, count = 0;
        while (index < typed.length()) {
            char c = typedChars[index];
            have[c]++;
            index++;
            if (have[c] <= need[c]) {
                count++;
            }
            if (count == name.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 双指针
     */
    public boolean isLongPressedName(String name, String typed) {
        char[] nameChars = name.toCharArray();
        char[] typedChars = typed.toCharArray();
        int i = 0,  j = 0;
        while (j < typed.length()) {
            if (i < name.length() && nameChars[i] == typedChars[j]) {
                i++;
                j++;
            } else if (j > 0 && typedChars[j] == typedChars[j - 1]) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }

    
}
// @lc code=end

