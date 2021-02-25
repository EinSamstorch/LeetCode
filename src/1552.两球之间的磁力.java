import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=1552 lang=java
 *
 * [1552] 两球之间的磁力
 *
 * https://leetcode-cn.com/problems/magnetic-force-between-two-balls/description/
 *
 * algorithms
 * Medium (49.59%)
 * Likes:    58
 * Dislikes: 0
 * Total Accepted:    6.6K
 * Total Submissions: 13.2K
 * Testcase Example:  '[1,2,3,4,7]\n3'
 *
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i
 * 个篮子的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。
 * 
 * 已知两个球如果分别位于 x 和 y ，那么它们之间的磁力为 |x - y| 。
 * 
 * 给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 
 * 输入：position = [1,2,3,4,7], m = 3
 * 输出：3
 * 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3
 * 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：position = [5,4,3,2,1,1000000000], m = 2
 * 输出：999999999
 * 解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * 所有 position 中的整数 互不相同 。
 * 2 <= m <= position.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int len = position.length;
        int left = 1;
        // right也可以设置成Integer.MAX_VALUE
        int right = position[len - 1];
        // 如果按照下面这样设置，右边界时不正确。
        // int left = 1, right = position[len - 1] - position[0];

        while (left < right) {
            int mid = (left + right) >>> 1;
            int spilts = countSpilts(position, mid);
            if (spilts >= m) {
                // 本题要找的是又边界，所以就算spilts = m 时，也要收缩左边界
                left = mid + 1;
            } else {
                right = mid;
            } 
        }
        return left - 1;
    }

    private int countSpilts(int[] position, int distance) {
        int len = position.length;
        int pre = position[0];
        int count = 1;

        for (int i = 1; i < len; i++) {
            if (position[i] - pre >= distance) {
                count++;
                pre = position[i];
            }
        }
        return count;
    }
}
// @lc code=end

