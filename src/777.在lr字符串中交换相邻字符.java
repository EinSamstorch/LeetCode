/*
 * @lc app=leetcode.cn id=777 lang=java
 *
 * [777] 在LR字符串中交换相邻字符
 *
 * https://leetcode-cn.com/problems/swap-adjacent-in-lr-string/description/
 *
 * algorithms
 * Medium (32.14%)
 * Likes:    81
 * Dislikes: 0
 * Total Accepted:    4.3K
 * Total Submissions: 13.5K
 * Testcase Example:  '"RXXLRXRXL"\n"XRLXXRRLX"'
 *
 * 在一个由 'L' , 'R' 和 'X'
 * 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时，
 * 返回True。
 * 
 * 
 * 
 * 示例 :
 * 
 * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * 输出: True
 * 解释:
 * 我们可以通过以下几步将start转换成end:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= len(start) = len(end) <= 10000。
 * start和end中的字符串仅限于'L', 'R'和'X'。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 性质分析
     */
    public boolean canTransform(String start, String end) {
        //1. 验证转换不变性
        // L和R是不能相互穿插的，这意味着开始和结束的字符串如果只看 'L' 和 'R' 的话是一模一样的
        if (!start.replace("X", "").equals(end.replace("X", ""))) {
            return false;
        }

        //2. 验证不可到达性,L不能移到初始位置的右边，R不能移到初始位置的左边
        int t = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'L') {
                while (end.charAt(t) != 'L') {
                    t++;
                }
                // 这边判断完之后要t++，L在目标位置中位置加一
                if (i < t++) {
                    return false;
                }
            }
        }
        t = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'R') {
                while (end.charAt(t) != 'R') {
                    t++;
                }
                if (i > t++) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean canTransform1(String start, String end) {
        int N = start.length();
        char[] S = start.toCharArray(), T = end.toCharArray();
        int i = -1, j = -1;

        while (++i < N && ++j < N) {
            while (i < N && S[i] == 'X') {
                i++;
            }
            while (j < N && T[j] == 'X') {
                j++;
            }
            /* At this point, i == N or S[i] != 'X',
               and j == N or T[j] != 'X'.  i and j
               are the indices representing the next
               occurrences of non-X characters in S and T.
            */

            if ((i < N) ^ (j < N)) {
                return false;
            }
            if (i < N && j < N) {
                if (S[i] != T[j] || (S[i] == 'L' && i < j) || (S[i] == 'R' && i > j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
// @lc code=end

