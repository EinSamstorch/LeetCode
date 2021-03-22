import java.security.DrbgParameters.Reseed;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=969 lang=java
 *
 * [969] 煎饼排序
 *
 * https://leetcode-cn.com/problems/pancake-sorting/description/
 *
 * algorithms
 * Medium (65.39%)
 * Likes:    94
 * Dislikes: 0
 * Total Accepted:    10.7K
 * Total Submissions: 16.3K
 * Testcase Example:  '[3,2,4,1]'
 *
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * 
 * 一次煎饼翻转的执行过程如下：
 * 
 * 
 * 选择一个整数 k ，1 
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 
 * 
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * 
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length
 * 范围内的有效答案都将被判断为正确。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：[3,2,4,1]
 * 输出：[4,2,4,3]
 * 解释：
 * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
 * 初始状态 arr = [3, 2, 4, 1]
 * 第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
 * 第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
 * 第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
 * 第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。 
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：[1,2,3]
 * 输出：[]
 * 解释：
 * 输入已经排序，因此不需要翻转任何内容。
 * 请注意，其他可能的答案，如 [3，3] ，也将被判断为正确。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 1 
 * arr 中的所有整数互不相同（即，arr 是从 1 到 arr.length 整数的一个排列）
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 每次找到当前最大值，通过最多两次翻转，将其移动到末尾
     */
    public List<Integer> pancakeSort1(int[] arr) {
        int len = arr.length;
        List<Integer> res = new ArrayList<>();
        if (len < 2) {
            return res;
        }
        // 数组元素的值是在1到len之间
        // 所以可以用另外一个数组记录每个元素所在的位置，下标为元素，值为所在原数组的位置
        int[] record = new int[len + 1];
        for (int i = 0; i < len; i++) {
            record[arr[i]] = i;
        }
        // 这样record数组下标也是有序的了，我们先从最大的元素开始，一个个的调整到合适的位置
        for (int i = len; i >= 1; i--) {
            // 如果当前最大元素在合适的位置，就不做处理，直接操作下一个
            if (i == record[i] + 1) {
                continue;
            }
            // 当前最大元素所在的位置
            int curPosition = record[i];
            // 每个元素需要继续两步翻转
            //      第一步：翻转到0位置
            //      第二步：再翻转到当前最后的位置（i - 1）
            // 首先看是否在0的位置，是的话省去第一步
            if (curPosition == 0) {
                res.add(i);
                swap(arr, record, 0, i - 1);
            } else {
                // 第一步：翻转到0的位置
                res.add(record[i] + 1);
                swap(arr, record, 0, record[i]);
                // 加回来，下次遍历再来，走第二步
                i++;
            }
        }
        return res;
    }

    private void swap(int[] arr, int[] record, int left, int right) {
        while (left < right) {
            // 更新记录位置
            record[arr[left]] = right;
            record[arr[right]] = left;

            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 记录翻转操作序列
     */
    LinkedList<Integer> ans = new LinkedList<>();

    public List<Integer> pancakeSort(int[] cakes) {
        sort(cakes, cakes.length);
        return ans;
    }

    private void sort(int[] cakes, int n) {
        // base case
        if (n == 1) {
            return;
        }
        // 寻找最大饼的索引
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }
        // 第一次翻转，将最大饼翻到最上面
        reverse(cakes, 0, maxCakeIndex);
        ans.add(maxCakeIndex + 1);
        // 第二次翻转，将最大饼翻到最下面
        reverse(cakes, 0, n - 1);
        ans.add(n);
        // 递归调用
        sort(cakes, n - 1);
    }

	private void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
	}
}
// @lc code=end

