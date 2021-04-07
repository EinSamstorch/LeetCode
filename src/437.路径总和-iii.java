import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
 *
 * https://leetcode-cn.com/problems/path-sum-iii/description/
 *
 * algorithms
 * Medium (56.59%)
 * Likes:    791
 * Dislikes: 0
 * Total Accepted:    71K
 * Total Submissions: 125.4K
 * Testcase Example:  '[10,5,-3,3,2,null,11,3,-2,null,1]\n8'
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 
 * 找出路径和等于给定数值的路径总数。
 * 
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * 
 * 示例：
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 * ⁠     10
 * ⁠    /  \
 * ⁠   5   -3
 * ⁠  / \    \
 * ⁠ 3   2   11
 * ⁠/ \   \
 * 3  -2   1
 * 
 * 返回 3。和等于 8 的路径有:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
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
    // key为前缀和,value是大小为key的前缀和出现的次数
    Map<Integer, Integer> prefixSumCount = new HashMap<>();

    public int pathSum1(TreeNode root, int sum) {
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归思路
        return recursionPathSum(root, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    public int recursionPathSum(TreeNode node, int target, int currSum) {
        if (node == null) {
            return 0;
        }
        // 本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        // 核心代码
        // 看看root到当前节点的这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推,有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum - target相当于找路径的起点,起点的sum + target = currSum,当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        // 进入下一层
        res += recursionPathSum(node.left, target, currSum);
        res += recursionPathSum(node.right, target, currSum);

        // 回到本层,恢复状态,去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }

    /**
     * 方法2: 双重递归
     * 第一重递归:遍历树的每一个节点
     * 第二重递归:计算从每一个节点出发一共有多少路径
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        res += countPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        return res;
    }

    /**
     * 计算从某一个节点出发一共能有多少路径 
     */
    public int countPath(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        if (root.val == sum) {
            count++;
        }
        int leftCount = countPath(root.left, sum - root.val);
        int rightCount = countPath(root.right, sum - root.val);
        count += leftCount + rightCount;

        return count;
    }
}
// @lc code=end

