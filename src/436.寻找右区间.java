import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/*
 * @lc app=leetcode.cn id=436 lang=java
 *
 * [436] 寻找右区间
 *
 * https://leetcode-cn.com/problems/find-right-interval/description/
 *
 * algorithms
 * Medium (48.17%)
 * Likes:    62
 * Dislikes: 0
 * Total Accepted:    6.1K
 * Total Submissions: 12.8K
 * Testcase Example:  '[[1,2]]'
 *
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 * 
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 * 
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1, 0, 1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1, 2, -1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * intervals[i].length == 2
 * -10^6 i i 
 * 每个间隔的起点都 不相同
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 排序预处理 + 二分查找
     */
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) {
            return new int[0];
        }
        // 对原始区间进行预处理，key：起点，value：索引
        // 题目中说，你可以假定这些区间都不具有相同的起点
        Map<Integer, Integer> hashMap = new HashMap<>();
        int[] arr = new int[len];
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            hashMap.put(intervals[i][0], i);
            arr[i] = intervals[i][0];
        }
        Arrays.sort(arr);

        for (int i = 0; i < len; i++) {
            int index = binarySearch(arr, intervals[i][1]);
            if (index == -1) {
                res[i] = -1;
            } else {
                res[i] = hashMap.get(arr[index]);
            }
        }
        return res;
    }

    /**
     * 查找第一个大于等于target的元素
     */
    private int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums[right] < target) {
            return - 1;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 找第一个大于等于target的元素，它的反面，小于target一定不是解
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
// @lc code=end

