import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * @lc app=leetcode.cn id=480 lang=java
 *
 * [480] 滑动窗口中位数
 *
 * https://leetcode-cn.com/problems/sliding-window-median/description/
 *
 * algorithms
 * Hard (35.45%)
 * Likes:    99
 * Dislikes: 0
 * Total Accepted:    5.3K
 * Total Submissions: 14.9K
 * Testcase Example:  '[1,3,-1,-3,5,3,6,7]\n3'
 *
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 
 * 例如：
 * 
 * 
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 
 * 
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1
 * 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * 
 * 
 * 
 * 示例：
 * 
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * 
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * ⁠1 [3  -1  -3] 5  3  6  7      -1
 * ⁠1  3 [-1  -3  5] 3  6  7      -1
 * ⁠1  3  -1 [-3  5  3] 6  7       3
 * ⁠1  3  -1  -3 [5  3  6] 7       5
 * ⁠1  3  -1  -3  5 [3  6  7]      6
 * 
 * 
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 想到用空间换时间，用一个内部有序的数据结构维护窗口
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        // 这个比较规则，不用Integer.compare的话，可能会溢出
        // java的treeset不支持重复的元素，所以用int[nums[i], i]数组表示元素，不会有 重复的值
        Set<int[]> set = new TreeSet<>(
                (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        for (int i = 0; i < k; i++){
            set.add(new int[] {nums[i], i});
        }
        for (int i = k, j = 0; i < n; i++, j++) {
            ans[j] = findMid(set);
            set.add(new int[] { nums[i], i });
            set.remove(new int[] { nums[i - k], i - k });
        }
        ans[n - k] = findMid(set);
        return ans;
    }



    double findMid(Set<int[]> set) {
        int mid = (set.size() - 1) / 2;
        Iterator<int[]> it = set.iterator();
        while (mid> 0){
            mid--;
            it.next();
        }
        return set.size() % 2 == 0 ? ((double) it.next()[0] + it.next()[0]) / 2 : it.next()[0];
    }
}
// @lc code=end
