import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 *
 * https://leetcode-cn.com/problems/merge-sorted-array/description/
 *
 * algorithms
 * Easy (48.57%)
 * Likes:    626
 * Dislikes: 0
 * Total Accepted:    207.6K
 * Total Submissions: 426.8K
 * Testcase Example:  '[1,2,3,0,0,0]\n3\n[2,5,6]\n3'
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *
 *
 * 说明:
 *
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 *
 *
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */

// @lc code=start
class Solution {
    /**
     * 合并数组，排序
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /**
     * 三指针，从前往后
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1。从头改变nums1时会破坏原来的数据，所以需要复制
        int[] nums1_copy = Arrays.copyOf(nums1, m);
        // 给nums1_copy和nums2的两个指针
        int p1 = 0;
        int p2= 0;
        // 给nums1的指针
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1
        while (p1 < m && p2 < n) {
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
        }

        // if there are still elements to add
        if (p1 < m) {
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 -p2);
        } 
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }

    }

    /**
     * 三指针，从后往前
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        // two get pointers for nums1 and nums2
        int p1 = m - 1;
        int p2 = n - 1;
        // set pointer for nums1
        int p = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    /**
     * 双指针，这就和归并排序那差不多
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];

        for (int i = 0; i < sorted.length; i++) {
            if (p1 == m) {
                sorted[i] = nums2[p2++];
            } else if (p2 == n) {
                sorted[i] = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                sorted[i] = nums1[p1++];
            } else {
                sorted[i] = nums2[p2++];
            }
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = sorted[i];
        }
    }
}
// @lc code=end

