/*
 * @lc app=leetcode.cn id=1482 lang=java
 *
 * [1482] 制作 m 束花所需的最少天数
 *
 * https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
 *
 * algorithms
 * Medium (45.75%)
 * Likes:    71
 * Dislikes: 0
 * Total Accepted:    5.3K
 * Total Submissions: 11.6K
 * Testcase Example:  '[1,10,3,10,2]\n3\n1'
 *
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * 
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 * 
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * 
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * 
 * 
 * 示例 2：
 * 
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 * 
 * 
 * 示例 4：
 * 
 * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 * 
 * 
 * 示例 5：
 * 
 * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * bloomDay.length == n
 * 1 <= n <= 10^5
 * 1 <= bloomDay[i] <= 10^9
 * 1 <= m <= 10^6
 * 1 <= k <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    private int[] bloomDay;

    public int minDays(int[] bloomDay, int m, int k) {
        if (bloomDay.length < m * k) {
            return -1;
        }
        this.bloomDay = bloomDay;
        // 思路和珂珂吃苹果还有d天装船类似
        // 搞一个函数用来计算某些天数内是否能够完成制造
        int left = 1, right = 1000000000;

        while (left < right) {
            int mid = (left + right) >>> 1;
            // 如果k天可以完成，那就尝试更小的天数
            if (canMake(mid, m, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    private boolean canMake(int daysNum, int m, int k) {
        // 临时保存k
        int tempK = k;
        for (int i : bloomDay) {
            // 如果元素的值，小于daysNum的值说明开花了，可以摘
            if (i <= daysNum) {
                // 花都开了，那就摘一朵，摘完发现已经够一束了，那就把目标的束的数量减少
                if (--tempK == 0) {
                    // 减少花束的数量
                    m--;
                    // 花朵的数量要恢复
                    tempK = k;
                }
            } else {
                // 一旦遇到不能摘得要立马恢复朵的数量
                tempK = k;
            }
            // 如果束的数量小于等于0说明在daysNum天的时候，可以完成m束花
        }
        return m <= 0;
    }
}
// @lc code=end

