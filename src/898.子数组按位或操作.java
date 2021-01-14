import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=898 lang=java
 *
 * [898] 子数组按位或操作
 *
 * https://leetcode-cn.com/problems/bitwise-ors-of-subarrays/description/
 *
 * algorithms
 * Medium (30.42%)
 * Likes:    75
 * Dislikes: 0
 * Total Accepted:    3.9K
 * Total Submissions: 12.8K
 * Testcase Example:  '[0]'
 *
 * 我们有一个非负整数数组 A。
 * 
 * 对于每个（连续的）子数组 B = [A[i], A[i+1], ..., A[j]] （ i <= j），我们对 B
 * 中的每个元素进行按位或操作，获得结果 A[i] | A[i+1] | ... | A[j]。
 * 
 * 返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。）
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：[0]
 * 输出：1
 * 解释：
 * 只有一个可能的结果 0 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：[1,1,2]
 * 输出：3
 * 解释：
 * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
 * 产生的结果为 1，1，2，1，3，3 。
 * 有三个唯一值，所以答案是 3 。
 * 
 * 
 * 示例 3：
 * 
 * 输入：[1,2,4]
 * 输出：6
 * 解释：
 * 可能的结果是 1，2，3，4，6，以及 7 。
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= A.length <= 50000
 * 0 <= A[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subarrayBitwiseORs1(int[] arr) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        cur.add(0);

        for (int x : arr) {
            Set<Integer> cur2 = new HashSet<>();
            for (int y : cur) {
                cur2.add(x | y);
            }
            cur2.add(x);
            cur = cur2;
            ans.addAll(cur);
        }
        return ans.size();
    }

    // 暴力枚举  超时了
    public int subarrayBitwiseORs2(int[] arr) {
        int n = arr.length;
        Set<Integer> ans = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int temp = arr[i];
            ans.add(temp);
            for (int j = i + 1; j < n; j++) {
                temp |= arr[j];
                ans.add(temp);
            }
        }
        return ans.size();
    }

    public int subarrayBitwiseORs(int[] arr) {
        int n = arr.length;
        Set<Integer> ans = new HashSet<>();

        for (int i = 0; i < n; i++) {
            ans.add(arr[i]);
            for (int j = i - 1; j >= 0; j--) {
                // 剪枝
                if ((arr[i] | arr[j]) == arr[j]) {
                    break;
                }
                // 复用
                arr[j] |= arr[i];
                ans.add(arr[j]);
            }
        }
        return ans.size();
    }
}
// @lc code=end

