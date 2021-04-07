import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/*
 * @lc app=leetcode.cn id=113 lang=java
 *
 * [113] 路径总和 II
 *
 * https://leetcode-cn.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (61.74%)
 * Likes:    449
 * Dislikes: 0
 * Total Accepted:    124.9K
 * Total Submissions: 202K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 
 * 叶子节点 是指没有子节点的节点。
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 
 * -1000 
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
    List<List<Integer>> res = new LinkedList<>();
    List<Integer> list = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, 0, targetSum);
        return res;
    }

    public void dfs(TreeNode root, int sum, int targetSum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        list.add(root.val);
        if (sum == targetSum && root.left == null && root.right == null) {
            res.add(new LinkedList<>(list));
        }
        dfs(root.left, sum, targetSum);
        dfs(root.right, sum, targetSum);
        // 两次回溯只需要一次pop，因为遍历完左孩子之后还要遍历右孩子才能pop
        list.remove(list.size() - 1);
        // sum原始数据类型，传递到方法中进行更改，当方法返回时，参数消失，对他们的任何更改都将丢失，因此这边sum不需要重置
    }
}
// @lc code=end

