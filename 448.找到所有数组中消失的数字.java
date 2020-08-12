import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=448 lang=java
 *
 * [448] 找到所有数组中消失的数字
 *
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/description/
 *
 * algorithms
 * Easy (58.47%)
 * Likes:    424
 * Dislikes: 0
 * Total Accepted:    48.4K
 * Total Submissions: 81.3K
 * Testcase Example:  '[4,3,2,7,8,2,3,1]'
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 *
 */

// @lc code=start
class Solution {

    /**
     * 使用hashset来存储所有的元素，用到了额外空间
     *
     * @param nums 待查找的数组
     * @return 没有出现在数组中的元素
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 处理值变为新指针
            int newIndex = Math.abs(nums[i]) - 1;
            // 检查新指针出的nums值
            // 如果值为正数，将这个数设为负数
            // 暗示着nums[i]已经出现过或者被访问过
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }

        // 遍历1-N
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i - 1] > 0) {
                res.add(i);
            }
        }
        return res;
    }
    
}

// @lc code=end

