import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=719 lang=java
 *
 * [719] 找出第 k 小的距离对
 *
 * https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance/description/
 *
 * algorithms
 * Hard (34.84%)
 * Likes:    138
 * Dislikes: 0
 * Total Accepted:    6K
 * Total Submissions: 17.3K
 * Testcase Example:  '[1,3,1]\n1'
 *
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * 
 * 示例 1:
 * 
 * 
 * 输入：
 * nums = [1,3,1]
 * k = 1
 * 输出：0 
 * 解释：
 * 所有数对如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 
 * 
 * 提示:
 * 
 * 
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        //对距离进行二分
        int left = 0, right = nums[nums.length - 1] - nums[0];
        while (left < right) {
          int mid = left + (right - left) / 2;
          int i = 0, cnt = 0;
          //查找距离小于等于mid的元素对数
          for (int j = 0; j < nums.length; j++) {
            //两个指针i,j，如果nums[i]与nums[j]的距离小于等于mid
            //那么[i,j-1]的所有元素与nums[j]的距离都小于等于mid
            //这个对数为j-i
            while (nums[j] - nums[i] > mid) i++;
            cnt += j - i;
          }
          //个数太少，说明距离限制太小了，需要扩大限制
          if (cnt < k) left = mid + 1;
          else right = mid;
        }
        return left;
    }
}

// @lc code=end

