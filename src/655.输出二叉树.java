import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box.Filler;

/*
 * @lc app=leetcode.cn id=655 lang=java
 *
 * [655] 输出二叉树
 *
 * https://leetcode-cn.com/problems/print-binary-tree/description/
 *
 * algorithms
 * Medium (59.23%)
 * Likes:    97
 * Dislikes: 0
 * Total Accepted:    7.7K
 * Total Submissions: 13K
 * Testcase Example:  '[1,2]'
 *
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 * 
 * 
 * 行数 m 应当等于给定二叉树的高度。
 * 列数 n 应当总是奇数。
 * 
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 * 
 * 
 * 示例 1:
 * 
 * 
 * 输入:
 * ⁠    1
 * ⁠   /
 * ⁠  2
 * 输出:
 * [["", "1", ""],
 * ⁠["2", "", ""]]
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入:
 * ⁠    1
 * ⁠   / \
 * ⁠  2   3
 * ⁠   \
 * ⁠    4
 * 输出:
 * [["", "", "", "1", "", "", ""],
 * ⁠["", "2", "", "", "", "3", ""],
 * ⁠["", "", "4", "", "", "", ""]]
 * 
 * 
 * 示例 3:
 * 
 * 
 * 输入:
 * ⁠     1
 * ⁠    / \
 * ⁠   2   5
 * ⁠  / 
 * ⁠ 3 
 * ⁠/ 
 * 4 
 * 输出:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 * ⁠["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 * ⁠["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 * ⁠["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * 
 * 
 * 注意: 二叉树的高度在范围 [1, 10] 中。
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
    public List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for (String[] arr : res) {
            Arrays.fill(arr, "");
        }
        
        fill(res, root, 0, 0, res[0].length);
        
        List<List<String>> ans = new ArrayList<>();
        for (String[] arr : res) {
            ans.add(Arrays.asList(arr));
        }
        return ans;
    }

    public void fill(String[][] res, TreeNode root, int i, int left, int right) {
        if (root == null) {
            return;
        }
        // 当前节点所在的列
        int j = (left + right) / 2;
        res[i][j] = "" + root.val;
        fill(res, root.left, i + 1, left, j);
        fill(res, root.right, i + 1, j + 1, right);
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(right, left) + 1;
    }
}
// @lc code=end

