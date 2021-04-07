import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=671 lang=java
 *
 * [671] 二叉树中第二小的节点
 *
 * https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/description/
 *
 * algorithms
 * Easy (46.29%)
 * Likes:    139
 * Dislikes: 0
 * Total Accepted:    22.7K
 * Total Submissions: 49.2K
 * Testcase Example:  '[2,2,5,null,null,5,7]'
 *
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或
 * 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。
 * 
 * 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
 * 
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [2,2,5,null,null,5,7]
 * 输出：5
 * 解释：最小的值是 2 ，第二小的值是 5 。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [2,2,2]
 * 输出：-1
 * 解释：最小的值是 2, 但是不存在第二小的值。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点数目在范围 [1, 25] 内
 * 1 
 * 对于树中每个节点 root.val == min(root.left.val, root.right.val)
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
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    /**
     * 方法1:暴力
     */
    public int findSecondMinimumValue1(TreeNode root) {
        dfs(root);
        int min1 = minHeap.poll();
        int min2;
        while (!minHeap.isEmpty()) {
            min2 = minHeap.poll();
            if (min2 != min1) {
                return min2;
            }
        }
        
        return -1;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        minHeap.offer(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    /**
     * 方法2:利用题目给的条件,根节点是最小的
     */
    public int findSecondMinimumValue(TreeNode root) {
        return help(root, root.val);
    }

    public int help (TreeNode root, int min) {
        if (root == null) {
            return -1;
        }
        if (root.val > min) {
            return root.val;
        }
        int left = help(root.left, min);
        int right = help(root.right, min);
        // 根节点的左节点返回-1,说明左半部分的节点和根节点一样,则第二小为右节点
        if (left == -1) {
            return right;
        }
        // 根节点的右节点返回-1,说明右半部分的节点和根节点一样,则第二小为左节点
        if (right == -1) {
            return left;
        }
        return Math.min(left, right);
    }
}


// @lc code=end

