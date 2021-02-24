import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=1300 lang=java
 *
 * [1300] 转变数组后最接近目标值的数组和
 *
 * https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/description/
 *
 * algorithms
 * Medium (47.14%)
 * Likes:    126
 * Dislikes: 0
 * Total Accepted:    21.5K
 * Total Submissions: 45.5K
 * Testcase Example:  '[4,9,3]\n10'
 *
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value
 * 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。
 * 
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * 
 * 请注意，答案不一定是 arr 中的数字。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * 
 * 
 * 示例 2：
 * 
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * 
 * 
 * 示例 3：
 * 
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findBestValue(int[] arr, int target) {
        int left = 0;
        int right = 0;
        for (int num : arr) {
            right = Math.max(right,  num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = calculateSum(arr, mid);
            // 计算第一个使得转变后的数组的和大于等于target的阈值的threshold
            if (sum < target) {
                // 严格小于的一定不是解
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 比较阈值线分别定在left - 1和left的时候，与target的接近程度
        int sum1 = calculateSum(arr, left - 1);
        int sum2 = calculateSum(arr, left);
        if (target - sum1 <= sum2 - target) {
            return left - 1;
        }
        return left;
    }

	private int calculateSum(int[] arr, int threshold) {
		int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, threshold);
        }
        return sum;
	}
}
// @lc code=end

