/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (76.20%)
 * Likes:    829
 * Dislikes: 0
 * Total Accepted:    167.1K
 * Total Submissions: 218K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 *
 */

import java.util.*;

// @lc code=start
class Solution {


    public List<List<Integer>> permute(int[] nums) {
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>(factorial(nums.length));
        if (nums.length == 0) {
            return res;
        }
        Deque<Integer> path = new ArrayDeque<>(nums.length);
        boolean[] used = new boolean[nums.length];

        backtrack(nums, path, used, res);

        return res;
    }

    /**
     * 计算阶乘，确定res所需要的空间
     *
     * @param n 数组长度
     * @return n的阶乘
     */
    private int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    /**
     * 选择列表：nums中不存在于track中的元素
     * 结束条件：nums中的元素全部都在track中出现
     *
     * @param nums 一组不重复的数字
     * @param path 记录的路径
     */
    private void backtrack(int[] nums, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        // 中止条件，dfs的层数等于nums.length
        if (path.size() == nums.length) {
            /*
             path 这个变量所指向的对象在递归的过程中只有一份，深度优先遍历完成以后，因为回到了根结点
            （因为我们之前说了，从深层结点回到浅层结点的时候，需要撤销之前的选择），因此 path 这个变量回到根结点以后都为空。
            在 Java 中，因为都是值传递，对象类型变量在传参的过程中，复制的都是变量的地址。
            这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，因此我们会看到 6 个空的列表对象。解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。
            */
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 使用used数组来标记是否访问过该元素，比直接用path.contains(num)效率高，用空间换时间
            if (!used[i]) {
                // 做选择
                path.addLast(nums[i]);
                used[i] = true;

                // 进入下一层决策树
                backtrack(nums, path, used, res);

                // 状态重置，取消选择，进入上一层决策树
                used[i] = false;
                path.removeLast();
            }
        }
    }
}
// @lc code=end

