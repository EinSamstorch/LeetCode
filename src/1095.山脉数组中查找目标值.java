/*
 * @lc app=leetcode.cn id=1095 lang=java
 *
 * [1095] 山脉数组中查找目标值
 *
 * https://leetcode-cn.com/problems/find-in-mountain-array/description/
 *
 * algorithms
 * Hard (36.99%)
 * Likes:    111
 * Dislikes: 0
 * Total Accepted:    16.1K
 * Total Submissions: 43.5K
 * Testcase Example:  '[1,2,3,4,5,3,1]\n3'
 *
 * （这是一个 交互式问题 ）
 * 
 * 给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index
 * 值。
 * 
 * 如果不存在这样的下标 index，就请返回 -1。
 * 
 * 
 * 
 * 何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
 * 
 * 首先，A.length >= 3
 * 
 * 其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
 * 
 * 
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * 
 * 
 * 
 * 
 * 你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
 * 
 * 
 * MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始）
 * MountainArray.length() - 会返回该数组的长度
 * 
 * 
 * 
 * 
 * 注意：
 * 
 * 对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
 * 
 * 为了帮助大家更好地理解交互式问题，我们准备了一个样例
 * “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：array = [1,2,3,4,5,3,1], target = 3
 * 输出：2
 * 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。
 * 
 * 示例 2：
 * 
 * 输入：array = [0,1,2,4,2,1], target = 3
 * 输出：-1
 * 解释：3 在数组中没有出现，返回 -1。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 3 <= mountain_arr.length() <= 10000
 * 0 <= target <= 10^9
 * 0 <= mountain_arr.get(index) <= 10^9
 * 
 * 
 */

// @lc code=start
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int left = 0, right = mountainArr.length();
        int size = mountainArr.length();
        // 步骤1：先找到山顶元素所在的索引
        int mountainTop = findMountainTop(mountainArr, 0, size - 1);
        // 步骤2：在前有序且升序数组中找target所在的索引
        int res = findFromSortedArr(mountainArr, 0, mountainTop, target);
        if (res != - 1) {
            return res;
        }
        // 步骤3：在后有序且降序数组中找targetr所在的索引
        return findFromInversedArr(mountainArr, mountainTop + 1, size - 1, target);
    }

    public int findMountainTop(MountainArray mountainArr, int left, int right) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 取左中位数，因为进入循环，数组一定至少有2个元素
            // 因此，左中位数一定有右边元素，数组下标不会越界
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                // 如果左边数比右边数小，那他一定不是山顶
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 根据题意，山顶元素一定存在，所以退出while循环的时候，不用再单独作判断
        return left;
    }

    public int findFromSortedArr(MountainArray mountainArr, int left, int right, int target) {
        // 在前有序且升序的数组中找target所在的索引
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 因为不确定区间收缩成一个数之后，这个数是不是要找的数，因此单独做一次判断
        if (mountainArr.get(left) == target) {
            return left;
        }
        return -1;
    }

    public int findFromInversedArr(MountainArray mountainArr, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) > target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return mountainArr.get(left) == target ? left : -1;
    }
}
// @lc code=end

