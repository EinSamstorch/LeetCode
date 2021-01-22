/*
 * @lc app=leetcode.cn id=96 lang=java
 *
 * [96] 不同的二叉搜索树
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees/description/
 *
 * algorithms
 * Medium (69.23%)
 * Likes:    961
 * Dislikes: 0
 * Total Accepted:    100.7K
 * Total Submissions: 145.5K
 * Testcase Example:  '3'
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * 
 * 示例:
 * 
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * 
 * ⁠  1         3     3      2      1
 * ⁠   \       /     /      / \      \
 * ⁠    3     2     1      1   3      2
 * ⁠   /     /       \                 \
 * ⁠  2     1         2                 3
 * 
 */

// @lc code=start
class Solution {
    public int numTrees(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        ans[1] = 0;
        // 当i是根节点时，其左子树的节点为i - 1， 右子树的节点为n - i
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                ans[i] = ans[j - 1] * ans[i - j];
            }
        }
        return ans[n];
    }
}
// @lc code=end

