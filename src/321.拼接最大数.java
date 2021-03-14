import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=321 lang=java
 *
 * [321] 拼接最大数
 *
 * https://leetcode-cn.com/problems/create-maximum-number/description/
 *
 * algorithms
 * Hard (42.96%)
 * Likes:    356
 * Dislikes: 0
 * Total Accepted:    22.8K
 * Total Submissions: 53.1K
 * Testcase Example:  '[3,4,6,5]\n[9,1,2,5,8,3]\n5'
 *
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n)
 * 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * 
 * 示例 1:
 * 
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 
 * 示例 2:
 * 
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 
 * 示例 3:
 * 
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * 
 */

// @lc code=start
class Solution {
    /**
     * 将解题分为两步
     * 1. 分别从两个数组中得到指定长度的最大子序列
     * 2. 将两个最大子序列合并
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n);
        int end = Math.min(k, m);

        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    /**
     * 单调栈
     * 问题转化为 移除 nums 中 n-k 个元素 LeetCode 402
     * kao！太巧妙了吧
     */
    private int[] maxSubsequence(int[] nums, int k) {
        int len = nums.length;
        // 移出n - k个
        k = len - k;
        if (k == 0) {
            return nums.clone();
        }
        if (k == len) {
            return new int[0];
        }

        int[] stack = new int[len];
        int top = 0;

        for (int i = 0; i < len; i++) {
            while (k > 0 && top > 0 && nums[i] > stack[top - 1]) {
                top--;
                k--;
            }
            // 当前元素入栈
            stack[top++] = nums[i];
        }
        // 如果k > 0, 再移除k个栈顶元素
        top -= k;
        return Arrays.copyOfRange(stack, 0, top);
    }

    /**
     * 合并两个数组成最大值
     */
    private int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] res = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            res[k++] = compare(nums1, i, nums2, j) >= 0 ? nums1[i++] : nums2[j++];
        }
        while (i < m) {
            res[k++] = nums1[i++];
        }
        while (j < n) {
            res[k++] = nums2[j++];
        }

        return res;
    }

    /**
     * 比较nums1[i, m)和nums2[j, n)
     * 这地方是一个难点，甚至可以参考String.compareTo(String anotherString)
     */
    private int compare(int[] nums1, int i, int[] nums2, int j) {
        int m = nums1.length;
        int n = nums2.length;

        while (i < m && j < n) {
            int diff = nums1[i] - nums2[j];
            if (diff != 0) {
                return diff;
            }
            i++;
            j++;
        }
        return Integer.compare(m - i, n - j);
    }
}
// @lc code=end

