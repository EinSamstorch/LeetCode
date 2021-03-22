import org.graalvm.compiler.lir.sparc.SPARCByteSwapOp;

/*
 * @lc app=leetcode.cn id=859 lang=java
 *
 * [859] 亲密字符串
 *
 * https://leetcode-cn.com/problems/buddy-strings/description/
 *
 * algorithms
 * Easy (29.53%)
 * Likes:    138
 * Dislikes: 0
 * Total Accepted:    22.9K
 * Total Submissions: 77.5K
 * Testcase Example:  '"ab"\n"ba"'
 *
 * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false
 * 。
 * 
 * 交换字母的定义是取两个下标 i 和 j （下标从 0 开始），只要 i!=j 就交换 A[i] 和 A[j] 处的字符。例如，在 "abcd"
 * 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入： A = "ab", B = "ba"
 * 输出： true
 * 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 相等。
 * 
 * 示例 2：
 * 
 * 
 * 输入： A = "ab", B = "ab"
 * 输出： false
 * 解释： 你只能交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 不相等。
 * 
 * 
 * 示例 3:
 * 
 * 
 * 输入： A = "aa", B = "aa"
 * 输出： true
 * 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'a' 生成 "aa"，此时 A 和 B 相等。
 * 
 * 示例 4：
 * 
 * 
 * 输入： A = "aaaaaaabc", B = "aaaaaaacb"
 * 输出： true
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入： A = "", B = "aa"
 * 输出： false
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * 0 
 * A 和 B 仅由小写字母构成。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 自己写的，没法判断字符串相等时，如何处理A[i] == B[j], A[j] == B[i], (A[i] != A[j])的情况
     */
    public boolean buddyStrings(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        if (a.equals(b)) {
            int[] count = new int[128];
            for (int i = 0; i < a.length(); i++) {
                count[a.charAt(i)]++;
            }
             // 如果A == B，则检查A/B中有无重复字符，如果有，重复字符对调一下就可以，满足条件。
            for (int num : count) {
                if (num > 1) {
                    return true;
                }
            }
            return false;
        } 
        int first = -1, second = -1, count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
                if (count == 1) {
                    first = i;
                } else if (count == 2) {
                    second = i;
                } else {
                    return false;
                }
            }
        }
        return count == 2 && a.charAt(first) == b.charAt(second) && a.charAt(second) == b.charAt(first);
    }
}
// @lc code=end

