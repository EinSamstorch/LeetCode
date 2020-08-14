/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 *
 * https://leetcode-cn.com/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (42.78%)
 * Likes:    550
 * Dislikes: 0
 * Total Accepted:    128.8K
 * Total Submissions: 300.9K
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 *
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[][] merge2(int[][] intervals) {
        // 初始化
        List<int[]> merged = new ArrayList<>();
        // 特例
        if (intervals == null || intervals.length == 0) {
            return merged.toArray(new int[0][]);
        }
        // 我们将列表中的区间按照左端点升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            // 如果当前区间的左端点在数组merged最后一个区间的右端点之后，不会重合，直接加入数组merged末尾
            // 否则，他们重合，需要用当前区间右端点更新merged中最后一个区间的右端点
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放入结果中
            merged.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }

        return merged.toArray(new int[0][]);
    }

    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> merged = new LinkedList<>();
        if (intervals.length < 2) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        merged.add(intervals[0]);

        int[] curInterval;
        for (int i = 1; i < intervals.length; i++) {
            curInterval = intervals[i];
            int left = curInterval[0];
            int right = curInterval[1];
            if (merged.getLast()[1] < left) {
                merged.add(curInterval);
            } else {
                curInterval = merged.removeLast();
                curInterval[1] = Math.max(curInterval[1], right);
                merged.add(curInterval);
            }
        }

        return merged.toArray(new int[0][]);
    }

}
// @lc code=end

