import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=15 lang=java
 *
 * [15] 三数之和
 */

// @lc code=start
class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 对数组进行排序
        Arrays.sort(nums);

        // 遍历a
        for (int i = 0; i < len; i++) {
            // 如果第一个数大于0，后面得肯定都大于0
            if (nums[i] > 0) {
                break;
            }
            // 去掉重复情况
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            int target = -nums[i];
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    ans.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
                    // 去掉重复的
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                } 
            }
        }

        return ans;
    }
}
// @lc code=end

