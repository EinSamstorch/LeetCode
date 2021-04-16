import java.beans.BeanInfo;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 * @lc app=leetcode.cn id=93 lang=java
 *
 * [93] 复原 IP 地址
 *
 * https://leetcode-cn.com/problems/restore-ip-addresses/description/
 *
 * algorithms
 * Medium (52.30%)
 * Likes:    553
 * Dislikes: 0
 * Total Accepted:    111.7K
 * Total Submissions: 213.5K
 * Testcase Example:  '"25525511135"'
 *
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
 * 和 "192.168@1.1" 是 无效 IP 地址。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * s 仅由数字组成
 * 
 * 
 */

// @lc code=start
class Solution {
    List<String> res = new ArrayList<>();
    Deque<String> path = new ArrayDeque<>(4);

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if (len < 4 || len > 12) {
            return res;
        }
        dfs(s, len, 0, 4);
        return res;
    }


    // 需要一个变量记录剩余多少段还没被分割
    private void dfs(String s, int len, int begin, int residue) {
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
        }

        for (int i = begin; i < begin + 3; i++) {
            if (i >= len) {
                break;
            }
            if (residue * 3 < len - i) {
                continue;
            }
            if (judgeIpSegement(s, begin, i)) {
                String curIpSegement = s.substring(begin, i + 1);
                path.addLast(curIpSegement);
                dfs(s, len, i + 1, residue - 1);
                path.removeLast();
            }
        }
    }

    /**
     * 判断ip段是否合法
     */
    public boolean judgeIpSegement(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }
        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return res >= 0 && res <= 255;
    }
}
// @lc code=end

