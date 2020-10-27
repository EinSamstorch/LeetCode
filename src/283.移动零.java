/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 *
 * https://leetcode-cn.com/problems/move-zeroes/description/
 *
 * algorithms
 * Easy (62.47%)
 * Likes:    767
 * Dislikes: 0
 * Total Accepted:    220.4K
 * Total Submissions: 352.7K
 * Testcase Example:  '[0,1,0,3,12]'
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 
 * 说明:
 * 
 * 
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**  
     * 双指针，用一个指针来记录非0的个数
     */
    public void moveZeroes1(int[] nums) {
        int j = 0;
        // 第一遍遍历，j指针记录非0的个数，只要是非0的都赋值给nums[j]
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        // 第二遍遍历的时候，将末尾的元素都赋值为0就可以了
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**  
     * 参考快排的思想，快排是选定一个中间点x，将所有小于等于x的元素放左边，大于x的元素放x的右边
     * 本题选择0当中间点，不等于0的放左边，等于0的放右边
     */
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        if (nums == null || len == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }
}
// @lc code=end

