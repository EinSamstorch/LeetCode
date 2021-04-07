import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;
import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=513 lang=java
 *
 * [513] 找树左下角的值
 *
 * https://leetcode-cn.com/problems/find-bottom-left-tree-value/description/
 *
 * algorithms
 * Medium (72.45%)
 * Likes:    161
 * Dislikes: 0
 * Total Accepted:    32.1K
 * Total Submissions: 44.2K
 * Testcase Example:  '[2,1,3]'
 *
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * 
 * 示例 1:
 * 
 * 
 * 输入:
 * 
 * ⁠   2
 * ⁠  / \
 * ⁠ 1   3
 * 
 * 输出:
 * 1
 * 
 * 
 * 
 * 
 * 示例 2: 
 * 
 * 
 * 输入:
 * 
 * ⁠       1
 * ⁠      / \
 * ⁠     2   3
 * ⁠    /   / \
 * ⁠   4   5   6
 * ⁠      /
 * ⁠     7
 * 
 * 输出:
 * 7
 * 
 * 
 * 
 * 
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
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
        private int curMaxDepth = -1;
        private int curVal = 0;
        public int findBottomLeftValue1(TreeNode root) {
            dfs(root, 0);
            return curVal;
        }

        public void dfs(TreeNode root, int depth) {
            if (root == null) {
                return;
            }
            if (depth > curMaxDepth) {
                curMaxDepth = depth;
                curVal = root.val;
            }
            dfs(root.left, depth + 1);
            dfs(root.right, depth + 1);
        }

        /**
         * bfs
         */
        public int findBottomLeftValue(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            TreeNode node = null;
            while (!queue.isEmpty()) {
                node = queue.poll();
                // 先右后左
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
            return node.val;
        }
    }
// @lc code=end

