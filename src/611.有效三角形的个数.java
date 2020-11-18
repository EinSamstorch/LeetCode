import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=611 lang=java
 *
 * [611] 有效三角形的个数
 *
 * https://leetcode-cn.com/problems/valid-triangle-number/description/
 *
 * algorithms
 * Medium (49.13%)
 * Likes:    136
 * Dislikes: 0
 * Total Accepted:    9.9K
 * Total Submissions: 20.1K
 * Testcase Example:  '[2,2,3,4]'
 *
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * 
 * 示例 1:
 * 
 * 
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是: 
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 
 * 
 * 注意:
 * 
 * 
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 暴力解法
     */
    public int triangleNumber1(int[] nums) {
        int count = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                int k = j + 1;
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k += 1;
                }
                count += k - j - 1;
            }
        }
        return count;
    }

    public int triangleNumber2(int[] nums) {
        int count = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 0) {
                continue;
            }
            // 现 k 没有必要每次都从 j + 1 开始。而是从上次找到的 k 值开始就行。
            // 原因很简单， 当 nums[i] + nums[j] > nums[k] 时，我们想要找到下一个满足 nums[i] + nums[j] > nums[k] 的 新的 k 值
            // 由于进行了排序，因此这个 k 肯定比之前的大（单调递增性），因此上一个 k 值之前的数都是无效的，可以跳过。


            int k = i + 2;
            for (int j = i + 1; j < nums.length - 1; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k += 1;
                }
                count += k - j - 1;
            }
        }
        return count;
    }

    /**
     * 二分查找
     * 1.对数组进行排序
     * 2.固定最短的两条边，二分查找最后一个小于两边之和的位置，
     * 可以求得固定两条边长之和满足条件的结果。枚举结束后，总和就是答案。
     */
    public int triangleNumber3(int[] nums) {
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                // 固定两条短边，二分查找最后一个小于两边之和的位置
                int sum = nums[i] + nums[j];
                int left = j + 1, right = n - 1;
                while (left < right) {
                    int mid = (left + right + 1) / 2;
                    if (nums[mid] < sum) {
                        left = mid;
                    } else if (nums[mid] >= sum) {
                        right = mid - 1;
                    }
                }
                // int left = j, right = n - 1;
                // while (left <= right) {
                //     int mid = left + (right - left) / 2;
                //     if (nums[mid] <= sum) {
                //         left = mid + 1;
                //     } else if (nums[mid] > sum) {
                //         right = mid - 1;
                //     }
                // }


                if (sum > nums[right]) {
                    ans += right - j;
                }
            }
        }
        return ans ;
    }

    /**
     * 首先对数组排序。
        固定最长的一条边，运用双指针扫描
        如果 nums[l] + nums[r] > nums[i]，同时说明 nums[l + 1] + nums[r] > nums[i], ..., nums[r - 1] + nums[r] > nums[i]，满足的条件的有 r - l 种，r 左移进入下一轮。
        如果 nums[l] + nums[r] <= nums[i]，l 右移进入下一轮。
        枚举结束后，总和就是答案。
        时间复杂度为 O(n^2)
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        // 固定最长边
        for (int i = n - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }


}
// @lc code=end
