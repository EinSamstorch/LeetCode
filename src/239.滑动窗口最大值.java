/*
 * @lc app=leetcode.cn id=239 lang=java
 *
 * [239] 滑动窗口最大值
 *
 * https://leetcode-cn.com/problems/sliding-window-maximum/description/
 *
 * algorithms
 * Hard (48.99%)
 * Likes:    544
 * Dislikes: 0
 * Total Accepted:    74.4K
 * Total Submissions: 151.8K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k
 * 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 * ⁠ 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * ⁠1 [3  -1  -3] 5  3  6  7       3
 * ⁠1  3 [-1  -3  5] 3  6  7       5
 * ⁠1  3  -1 [-3  5  3] 6  7       5
 * ⁠1  3  -1  -3 [5  3  6] 7       6
 * ⁠1  3  -1  -3  5 [3  6  7]      7
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 *
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// @lc code=start
class Solution {

    /**
     * 单调递减队列
     */
    private class MonotonicQueue {
        ArrayDeque<Integer> deque;

        public MonotonicQueue() {
            deque = new ArrayDeque<>();
        }

        public int max() {
            return deque.peekFirst();
        }

        public void pop(int num) {
            if (!deque.isEmpty() && num == deque.peekFirst()) {
                deque.pollFirst();
            }
        }

        public void push(int num) {
            // 构建单调递减队列，队列中比插入元素小的先弹出
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }
            deque.addLast(num);
        }
    }

    public int[] maxSlidingWindow1(int[] nums, int k) {
        MonotonicQueue windows = new MonotonicQueue();
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 先把窗口的前k - 1填满
                windows.push(nums[i]);
            } else {
                windows.push(nums[i]);
                res.add(windows.max());
                windows.pop(nums[i - k + 1]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            output[i] = max;
        }
        return output;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[len - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < len; i++) {
            pq.offer(new int[]{nums[i], i});
            // 那个最大值可能并不在滑动窗口中，
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}
// @lc code=end

