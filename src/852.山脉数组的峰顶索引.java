import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=852 lang=java
 *
 * [852] 山脉数组的峰顶索引
 *
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/description/
 *
 * algorithms
 * Easy (70.50%)
 * Likes:    127
 * Dislikes: 0
 * Total Accepted:    34.9K
 * Total Submissions: 49.4K
 * Testcase Example:  '[0,1,0]'
 *
 * 我们把符合下列属性的数组 A 称作山脉：
 * 
 * 
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... >
 * A[A.length - 1]
 * 
 * 
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... >
 * A[A.length - 1] 的 i 的值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[0,1,0]
 * 输出：1
 * 
 * 
 * 示例 2：
 * 
 * 输入：[0,2,1,0]
 * 输出：1
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int peakIndexInMountainArray1(int[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[maxIndex] <= arr[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * 线性扫描法
     * 从左到右扫描直到山的高度不再增长为止，停止增长点就是峰顶
     * 这题题目的意思就是只有一个峰顶
     */
    public int peakIndexInMountainArray2(int[] nums) {
        int i = 0;
        while (nums[i] < nums[i + 1]) {
            i++;
        }
        return i;
    }

    /**
     * 二分法
     */
    public int peakIndexInMountainArray(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] < nums[mid + 1]) {
                lo = mid + 1;
            } else if (nums[mid] > nums[mid + 1]){
                hi = mid - 1;
            } else if (nums[mid] == nums[mid + 1]) {
                // 收缩左侧边界
                hi = mid - 1;
            }
        }
        return lo;
    }

}
// @lc code=end

