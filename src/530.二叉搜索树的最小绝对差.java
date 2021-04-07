import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=530 lang=java
 *
 * [530] 二叉搜索树的最小绝对差
 *
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/description/
 *
 * algorithms
 * Easy (61.01%)
 * Likes:    236
 * Dislikes: 0
 * Total Accepted:    58.9K
 * Total Submissions: 96.5K
 * Testcase Example:  '[1,null,3,2]'
 *
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * 
 * 
 * 
 * 示例：
 * 
 * 输入：
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    3
 * ⁠   /
 * ⁠  2
 * 
 * 输出：
 * 1
 * 
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 * 相同
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
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    /**
    * 好像不需要用优先队列,对二叉搜索树进行中序遍历后,所遍历到的值本来就是升序的,所以用个普通的list就可以
    */ 
    public int getMinimumDifference1(TreeNode root) {
        dfs1(root);

        int pre = pq.poll();
        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int curNum = pq.poll();
            min = Math.min(min, curNum - pre);
            pre = curNum;
        }
        return min;
    }

    public void dfs1(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs1(node.left);
        pq.offer(node.val);
        dfs1(node.right);
    }

    int pre = - 1;
    int ans = Integer.MAX_VALUE;
    /**
     * 不用优先队列的方法
     */
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        // pre初始化为-1是为了标识第一个节点
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}
// @lc code=end

