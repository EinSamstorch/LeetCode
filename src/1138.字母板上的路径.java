/*
 * @lc app=leetcode.cn id=1138 lang=java
 *
 * [1138] 字母板上的路径
 *
 * https://leetcode-cn.com/problems/alphabet-board-path/description/
 *
 * algorithms
 * Medium (41.05%)
 * Likes:    20
 * Dislikes: 0
 * Total Accepted:    4K
 * Total Submissions: 9.7K
 * Testcase Example:  '"leet"'
 *
 * 我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
 *
 * 在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]，如下所示。
 *
 *
 *
 * 我们可以按下面的指令规则行动：
 *
 *
 * 如果方格存在，'U' 意味着将我们的位置上移一行；
 * 如果方格存在，'D' 意味着将我们的位置下移一行；
 * 如果方格存在，'L' 意味着将我们的位置左移一列；
 * 如果方格存在，'R' 意味着将我们的位置右移一列；
 * '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
 *
 *
 * （注意，字母板上只存在有字母的位置。）
 *
 * 返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = "leet"
 * 输出："DDR!UURRR!!DDD!"
 *
 *
 * 示例 2：
 *
 * 输入：target = "code"
 * 输出："RR!DDRR!UUL!R!"
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= target.length <= 100
 * target 仅含有小写英文字母。
 *
 *
 */

// @lc code=start
class Solution {
    public String alphabetBoardPath(String target) {
        int x = 0, y = 0, nx = 0, ny = 0;
        StringBuffer ans = new StringBuffer("");

        // 字母板上只有有字母的位置，所以考虑z的情况，
        // 当前字母为z，不能从z的右侧到达，只能从上面下来
        // 
        // 考虑到字母z，所以只能是左，上，下， 右
        for (int i = 0; i < target.length(); i++) {
            int temp = target.charAt(i) - 'a';
            if (i > 0 && target.charAt(i) == target.charAt(i - 1)) {
                ans.append('!');
            } else {
                nx = temp / 5;
                ny = temp % 5;
                // 向上走
                if (nx < x) {
                    for (int z = 0; z < x - nx; z++) {
                        ans.append("U");
                    }
                }

                // 向左走
                if (ny < y) {
                    for (int z = 0; z < y - ny; z++) {
                        ans.append("L");
                    }
                }

                // 向下走
                if (nx > x) {
                    for (int z = 0; z < nx - x; z++) {
                        ans.append("D");
                    }
                }
                // 向右走
                if (ny > y) {
                    for (int z = 0; z < ny - y; z++) {
                        ans.append("R");
                    }
                }


                ans.append("!");
                x = nx;
                y = ny;

            }
        }
        return ans.toString();
    }
}
// @lc code=end

