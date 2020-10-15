import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=532 lang=java
 *
 * [532] 数组中的 k-diff 数对
 *
 * https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/description/
 *
 * algorithms
 * Medium (35.12%)
 * Likes:    98
 * Dislikes: 0
 * Total Accepted:    20.3K
 * Total Submissions: 57.9K
 * Testcase Example:  '[3,1,4,1,5]\n2'
 *
 * 给定一个整数数组和一个整数 k，你需要在数组里找到不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * 
 * 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件：
 * 
 * 
 * 0 
 * i != j
 * |nums[i] - nums[j]| == k
 * 
 * 
 * 注意，|val| 表示 val 的绝对值。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个1，但我们只应返回不同的数对的数量。
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1)。
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：nums = [1,2,4,4,3,3,0,9,2,3], k = 3
 * 输出：2
 * 
 * 
 * 示例 5：
 * 
 * 
 * 输入：nums = [-1,-2,-3], k = 1
 * 输出：2
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * -10^7 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {
    // 使用map统计数组元素出现的次数
    public int findPairs1(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        if (k < 0) {
            return count;
        }
        // 统计数组元素在数组中出现的次数
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i : map.keySet())  {
            if (k == 0) {
                if (map.get(i) > 1) {
                    count++;
                }
            } else if (k != 0) {
                if (map.containsKey(i + k)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int findPairs2(int[] nums, int k) {
        if (nums.length < 2) {
            return 0;
        } 
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - k > nums[i]) {
                    break;
                }
                if (nums[j] - k == nums[i]) {
                    count++;
                    break;
                }
            }

        }
        return count;
    }
}
// @lc code=end

