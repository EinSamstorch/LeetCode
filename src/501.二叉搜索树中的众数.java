import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=501 lang=java
 *
 * [501] 二叉搜索树中的众数
 *
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/description/
 *
 * algorithms
 * Easy (50.07%)
 * Likes:    285
 * Dislikes: 0
 * Total Accepted:    50.3K
 * Total Submissions: 100.1K
 * Testcase Example:  '[1,null,2,2]'
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 
 * 假定 BST 有如下定义：
 * 
 * 
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 
 * 
 * 例如：
 * 给定 BST [1,null,2,2],
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  2
 * 
 * 
 * 返回[2].
 * 
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
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
    Map<Integer, Integer> map = new HashMap<>();

    public int[] findMode1(TreeNode root) {
        dfs1(root);
        int maxCount = Integer.MIN_VALUE;
        // 先找到最大出现次数
        for (int count : map.values()) {
            if (maxCount < count) {
                maxCount = count;
            }
        }
        // 根据value值获取到对应的所有key值
        List<Integer> keyList = new ArrayList<>();
        for (int getKey : map.keySet()) {
            if (map.get(getKey) == maxCount) {
                keyList.add(getKey);
            }
        }

        int[] mode = new int[keyList.size()];
        for (int i = 0; i < mode.length; i++) {
            mode[i] = keyList.get(i);
        }
        return mode;
    }

    /**
     * 二叉搜索树就要想到中序遍历
     */
    public void dfs1(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs1(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        dfs1(root.right);
    }

    List<Integer> ans = new ArrayList<>();
    int base, count, maxCount;
    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] mode = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            mode[i] = ans.get(i);
        }
        return mode;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        update(node.val);
        dfs(node.right);
    }

    public void update(int x) {
        if (x == base) {
            count++;
        } else {
            count = 1;
            base = x;
        }
        if (count == maxCount) {
            ans.add(base);
        }
        if (maxCount < count) {
            maxCount = count;
            ans.clear();
            ans.add(base);
        }
    }
}
// @lc code=end

