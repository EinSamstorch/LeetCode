/*
 * @lc app=leetcode.cn id=57 lang=java
 *
 * [57] 插入区间
 *
 * https://leetcode-cn.com/problems/insert-interval/description/
 *
 * algorithms
 * Hard (37.40%)
 * Likes:    175
 * Dislikes: 0
 * Total Accepted:    27.5K
 * Total Submissions: 73.6K
 * Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
 *
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 *
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 *
 *
 * 示例 2：
 *
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 *
 *
 *
 *
 * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 *
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        List<int[]> temp = new ArrayList<>(Arrays.asList(intervals));
        if (newInterval == null || newInterval.length == 0) {
            return temp.toArray(new int[0][]);
        }
        temp.add(newInterval);
        int[][] newIntervals = temp.toArray(new int[0][]);
        // 重新排序
        Arrays.sort(newIntervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < newIntervals.length; i++) {
            int left = newIntervals[i][0];
            int right = newIntervals[i][1];
            while (i < newIntervals.length - 1 && newIntervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, newIntervals[i][1]);
            }
            ans.add(new int[]{left, right});

        }

        return ans.toArray(new int[0][]);
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int index = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<>();

        // 1.将newInterval之前的区间添加到output中
        while (index < n && newStart > intervals[index][0]) {
            output.add(intervals[index++]);
        }

        int[] interval;
        // 如果output中是空的，或者output中最后最后一个区间的右侧小于newStart，将newInterval加入
        if (output.isEmpty() || output.getLast()[1] < newStart) {
            output.add(newInterval);
        } else {
            // 如果与output中最后一个区间重合则合并他们
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            // 更新之后加入interval中
            output.add(interval);
        }

        while (index < n) {
            interval = intervals[index++];
            int start = interval[0], end = interval[1];
            // 如果没有重叠，则直接加入
            if (output.getLast()[1] < start) {
                output.add(interval);
            } else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }

        return output.toArray(new int[0][]);
    }
}
// @lc code=end

