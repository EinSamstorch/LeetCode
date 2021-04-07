import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.tree.TreeNode;

import jdk.internal.jimage.ImageReader.Node;

/*
 * @lc app=leetcode.cn id=863 lang=java
 *
 * [863] 二叉树中所有距离为 K 的结点
 *
 * https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/description/
 *
 * algorithms
 * Medium (54.30%)
 * Likes:    261
 * Dislikes: 0
 * Total Accepted:    11.7K
 * Total Submissions: 21.6K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]\n5\n2'
 *
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 * 
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * 
 * 
 * 
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
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
    Map<TreeNode, TreeNode> parent = new HashMap<>();

    /**
     * 如果节点有指向父节点的引用,也就知道了距离该节点距离为1的所有节点.之后就可以从target节点开始进行广度优先搜索
     * @param root
     * @param target
     * @param K
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        // 加null是为了每一层停下来,计算当前距离
        queue.add(null);
        queue.add(target);

        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        seen.add(null);

        int distance = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (distance == K) {
                    List<Integer> ans = new ArrayList<>();
                    for (TreeNode n : queue) {
                        ans.add(n.val);
                    }
                    return ans;
                }
                queue.offer(null);
                distance++;
            } else {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }
        return new ArrayList<>();
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node == null) {
            return;
        }
        parent.put(node, par);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}
// @lc code=end

