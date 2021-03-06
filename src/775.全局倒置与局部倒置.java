/*
 * @lc app=leetcode.cn id=775 lang=java
 *
 * [775] 全局倒置与局部倒置
 *
 * https://leetcode-cn.com/problems/global-and-local-inversions/description/
 *
 * algorithms
 * Medium (45.73%)
 * Likes:    58
 * Dislikes: 0
 * Total Accepted:    4.2K
 * Total Submissions: 9.2K
 * Testcase Example:  '[0]'
 *
 * 数组 A 是 [0, 1, ..., N - 1] 的一种排列，N 是数组 A 的长度。全局倒置指的是 i,j 满足 0 <= i < j < N 并且
 * A[i] > A[j] ，局部倒置指的是 i 满足 0 <= i < N 并且 A[i] > A[i+1] 。
 * 
 * 当数组 A 中全局倒置的数量等于局部倒置的数量时，返回 true 。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入: A = [1,0,2]
 * 输出: true
 * 解释: 有 1 个全局倒置，和 1 个局部倒置。
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: A = [1,2,0]
 * 输出: false
 * 解释: 有 2 个全局倒置，和 1 个局部倒置。
 * 
 * 
 * 注意:
 * 
 * 
 * A 是 [0, 1, ..., A.length - 1] 的一种排列
 * A 的长度在 [1, 5000]之间
 * 这个问题的时间限制已经减少了。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 方法1:暴力法
     * 一个局部倒置也是一个全局倒置,因此只要检查非局部倒置就可以了
     * 非局部导致指的是A[i] > a[j], i < j, and j - i > 1
     */
    public boolean isIdealPermutation1(int[] A) {
        int len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 2; j < len; j++) {
                if (A[i] > A[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 记住最小的值
     * 暴力法中需要检查是否存在满足 j >= i+2 的 A[i] > A[j]，这和检查 A[i] > min(A[i+2:]) 是等价的。
     * 如果提前计算出 min(A[0:]), min(A[1:]), min(A[2:]), ... 这些区间的最小值，就可以立即完成检查操作。
     * 从右往左遍历数组 A，保存见到的最小的数。定义 floor = min(A[i:]) 来保存最小的数，如果 A[i-2] > floor，直接返回 False
     */
    public boolean isIdealPermutation(int[] nums) {
        int len = nums.length;
        int floor = len;
        for (int i = len - 1; i >= 2; i--) {
            floor = Math.min(floor, nums[i]);
            if (nums[i -2] > floor) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

