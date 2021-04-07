import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=653 lang=java
 *
 * [653] 两数之和 IV - 输入 BST
 *
 * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/description/
 *
 * algorithms
 * Easy (58.09%)
 * Likes:    229
 * Dislikes: 0
 * Total Accepted:    29.2K
 * Total Submissions: 50.2K
 * Testcase Example:  '[5,3,6,2,4,null,7]\n9'
 *
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * 
 * 案例 1:
 * 
 * 
 * 输入: 
 * ⁠   5
 * ⁠  / \
 * ⁠ 3   6
 * ⁠/ \   \
 * 2   4   7
 * 
 * Target = 9
 * 
 * 输出: True
 * 
 * 
 * 
 * 
 * 案例 2:
 * 
 * 
 * 输入: 
 * ⁠   5
 * ⁠  / \
 * ⁠ 3   6
 * ⁠/ \   \
 * 2   4   7
 * 
 * Target = 28
 * 
 * 输出: False
 * 
 * 
 * 
 * 
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // 自己写的,算的都是路径和了
    public boolean findTarget1(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        k -= root.val;
        if (k < root.val) {
            findTarget(root.left, k);
        } else if (k > root.val) {
            findTarget(root.right, k);
        } else {
            return true;
        }
        return false;
    }

    Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        if (set.contains(target - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, target) || findTarget(root.right, target);
    }
}
// @lc code=end

