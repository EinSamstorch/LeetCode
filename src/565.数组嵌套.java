import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=565 lang=java
 *
 * [565] 数组嵌套
 *
 * https://leetcode-cn.com/problems/array-nesting/description/
 *
 * algorithms
 * Medium (59.46%)
 * Likes:    114
 * Dislikes: 0
 * Total Accepted:    8.6K
 * Total Submissions: 14.4K
 * Testcase Example:  '[5,4,0,3,1,6,2]'
 *
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]],
 * A[A[A[i]]], ... }且遵守以下的规则。
 *
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]...
 * 以此类推，不断添加直到S出现重复的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 *
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 *
 *
 *
 *
 * 提示：
 *
 *
 * N是[1, 20,000]之间的整数。
 * A中不含有重复的元素。
 * A中的元素大小在[0, N-1]之间。
 *
 *
 */

// @lc code=start
class Solution {
    // 自己想出来的解法，最后只通过了853个
    public int arrayNesting1(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<Integer> visited = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            visited.add(i);
            while (!list.containsAll(visited)) {
                int next = nums[i];
                list.add(next);
                i = next;
            }
            maxLen = Math.max(maxLen, list.size());
            list.clear();
            visited.clear();
        }
        return Math.max(maxLen, list.size());
    }

    // 暴力解法
    public int arrayNesting2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int next = nums[i];
            int count = 0;
            do {
                next = nums[next];
                count++;
            } while (next != nums[i]);
            res = Math.max(res, count);
        }
        return res;
    }

    // 使用visited数组, 使用额外空间
    public int arrayNesting3(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int next = nums[i];
                int count = 0;

                do {
                    next = nums[next];
                    count++;
                    visited[next] = true;
                } while (next != nums[i]);
                res = Math.max(res, count);
            }
        }
        return res;
    }

    // 不使用额外空间，但是修改原数组
    public int arrayNesting(int[] nums) {
        int maxSize = 0;
        // 取数组中为使用的元素开始寻找环
        for(int i=0;i<nums.length;i++){
            // 被使用过的元素直接忽略(其实不用这个判断也可以)
            if(nums[i]==-1){
                continue;
            }
            int index = i;
            int count = 0;

            // 取的下一数字已为-1，即A中已经没当前环的下继，可以结束寻找下一环
            while(nums[index]!=-1){
                // 集合B的size记录
                count++;
                // 取出当前数字
                int next = nums[index];
                // 取出的数字标记为-1
                nums[index] = -1;
                // 下一数字下标为当前数字值
                index = next;
            }
            // 记录最大环
            maxSize = Math.max(count,maxSize);
        }
        return maxSize;
    }

    // 自己用visited数组重新写一遍
    public int arrayNesting4(int[] nums) {
        int maxSize = 0;
        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                int index = i;
                int count = 0;

                while (!visited[index]) {
                    count++;
                    int next = nums[index];
                    visited[index] = true;
                    index = next;
                }
                maxSize = Math.max(maxSize, count);
            }
        }

        return maxSize;
    }
    
}
// @lc code=end

