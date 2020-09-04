/*
 * @lc app=leetcode.cn id=75 lang=java
 *
 * [75] 颜色分类
 *
 * https://leetcode-cn.com/problems/sort-colors/description/
 *
 * algorithms
 * Medium (55.24%)
 * Likes:    557
 * Dislikes: 0
 * Total Accepted:    108.3K
 * Total Submissions: 195.8K
 * Testcase Example:  '[2,0,2,1,1,0]'
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 *
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 *
 */

// @lc code=start
class Solution {
    public void sortColors2(int[] nums) {
        int red = 0, white = 0, blue = 0;

        for (int num : nums) {
            if (num == 0) {
                red++;
            } else if (num == 1) {
                white++;
            } else if (num == 2) {
                blue++;
            }
        }

        int i = 0;
        while (i < nums.length) {
            while (red > 0) {
                nums[i++] = 0;
                red--;
            }
            while (white > 0) {
                nums[i++] = 1;
                white--;
            }
            while (blue > 0) {
                nums[i++] = 2;
                blue--;
            }
        }
    }

    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }

        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        // 循环中止条件是i == two
        // 为了保证初始化的时候[0, zero)为空，设置zero = 0,
        // 所以下面遍历到0的时候，先交换，再加
        int zero = 0;

        // 为了保证初始化的时候[two, len - 1]为空，设置two = len
        // 所以下面遍历到2的时候，先减，再交换
        int two = len;
        int i = 0;

        // 当i == two时，上面的三个子区间正好覆盖了全部数组
        // 因此循环可以继续的条件是i < two
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                zero++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
// @lc code=end

