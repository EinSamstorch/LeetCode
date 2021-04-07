/*
 * @lc app=leetcode.cn id=623 lang=java
 *
 * [623] 在二叉树中增加一行
 *
 * https://leetcode-cn.com/problems/add-one-row-to-tree/description/
 *
 * algorithms
 * Medium (54.03%)
 * Likes:    86
 * Dislikes: 0
 * Total Accepted:    8.6K
 * Total Submissions: 15.9K
 * Testcase Example:  '[4,2,6,3,1,5]\n1\n2'
 *
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 * 
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 * 
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 * 
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 * 
 * 示例 1:
 * 
 * 
 * 输入: 
 * 二叉树如下所示:
 * ⁠      4
 * ⁠    /   \
 * ⁠   2     6
 * ⁠  / \   / 
 * ⁠ 3   1 5   
 * 
 * v = 1
 * 
 * d = 2
 * 
 * 输出: 
 * ⁠      4
 * ⁠     / \
 * ⁠    1   1
 * ⁠   /     \
 * ⁠  2       6
 * ⁠ / \     / 
 * ⁠3   1   5   
 * 
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: 
 * 二叉树如下所示:
 * ⁠     4
 * ⁠    /   
 * ⁠   2    
 * ⁠  / \   
 * ⁠ 3   1    
 * 
 * v = 1
 * 
 * d = 3
 * 
 * 输出: 
 * ⁠     4
 * ⁠    /   
 * ⁠   2
 * ⁠  / \    
 * ⁠ 1   1
 * ⁠/     \  
 * 3       1
 * 
 * 
 * 注意:
 * 
 * 
 * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 * 输入的二叉树至少有一个节点。
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
    /**
     * 根据题目的意思一步一步的模拟
     */
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            TreeNode n = new TreeNode(val);
            n.left = root;
            return n;
        }
        insert(root, val, 1, depth);
        return root;
    }

    /**
     * 前序遍历
     */
    public void insert(TreeNode root, int val, int curDepth, int depth) {
        if (root == null) {
            return;
        }
        if (curDepth == depth - 1) {
            TreeNode t = root.left;
            root.left = new TreeNode(val);
            root.left.left = t;
            t = root.right;
            root.right = new TreeNode(val);
            root.right.right = t;
        } else {
            insert(root.left, val, curDepth + 1, depth);
            insert(root.right, val, curDepth + 1, depth);
        }
    }
}
// @lc code=end

