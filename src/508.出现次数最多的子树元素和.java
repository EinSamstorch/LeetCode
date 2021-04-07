import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=508 lang=java
 *
 * [508] 出现次数最多的子树元素和
 *
 * https://leetcode-cn.com/problems/most-frequent-subtree-sum/description/
 *
 * algorithms
 * Medium (66.02%)
 * Likes:    107
 * Dislikes: 0
 * Total Accepted:    11.4K
 * Total Submissions: 17.2K
 * Testcase Example:  '[5,2,-3]'
 *
 * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * 
 * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * 
 * 
 * 
 * 示例 1：
 * 输入:
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -3
 * 
 * 
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 * 
 * 示例 2：
 * 输入：
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -5
 * 
 * 
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 * 
 * 
 * 
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        findSum(root);

        int maxCount = 0;
        // 找到出现最多的次数
        for (int count : map.values()) {
            if (maxCount < count) {
                maxCount = count;
            }
        }
        // 找到出现最多次数的value对应的keys
        List<Integer> keyList = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == maxCount) {
                keyList.add(key);
            }
        }
        int[] ans = new int[keyList.size()];
        for (int i = 0; i < keyList.size(); i++) {
            ans[i] = keyList.get(i);
        }
        return ans;
    }

    /**
     * 错误示范:自己写成前序遍历了
     */
    public int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum += root.val;
        sum += dfs(root.left, sum);
        sum += dfs(root.right, sum);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }

    /**
     * 其实就是后序遍历
     */
    public int findSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = findSum(root.left);
        int right = findSum(root.right);
        int sum = root.val + left + right;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
// @lc code=end

