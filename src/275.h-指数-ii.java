/*
 * @lc app=leetcode.cn id=275 lang=java
 *
 * [275] H 指数 II
 *
 * https://leetcode-cn.com/problems/h-index-ii/description/
 *
 * algorithms
 * Medium (40.91%)
 * Likes:    90
 * Dislikes: 0
 * Total Accepted:    17.7K
 * Total Submissions: 43.3K
 * Testcase Example:  '[0,1,3,5,6]'
 *
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照 升序排列 。编写一个方法，计算出研究者的 h 指数。
 * 
 * h 指数的定义: “h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h
 * 篇论文分别被引用了至少 h 次。（其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: citations = [0,1,3,5,6]
 * 输出: 3 
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 * 由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 * 
 * 
 * 
 * 说明:
 * 
 * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
 * 
 * 
 * 
 * 进阶：
 * 
 * 
 * 这是 H 指数 的延伸题目，本题中的 citations 数组是保证有序的。
 * 你可以优化你的算法到对数时间复杂度吗？
 * 
 * 
 */

// @lc code=start
class Solution {
    // 我们需要找到的是第一篇文章citations[i] > n - i;
    public int hIndex1(int[] citations) {
        int len = citations.length;
        if (len == 0 || citations[len - 1] == 0) {
            return 0;
        }
        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (len - mid > citations[mid]) {
                // 说明此时mid太小，导致len - mid 太大
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return len - left;
    }

    /**
     * 暴力法
     */
    public int hIndex(int[] citations) {
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n - i) {
                return n - i;
            }
        }
        return 0;
    }

}
// @lc code=end

