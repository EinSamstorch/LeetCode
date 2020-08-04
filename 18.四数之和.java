import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=18 lang=java
 *
 * [18] 四数之和
 */

// @lc code=start
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);

        // 遍历a
        for (int i = 0; i < nums.length - 3; i++) {
            // 去除重复元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 获取当前最小值，当最小值都大于target时，退出
            int min1 = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min1 > target) {
                break;
            }

            // 获取当前最大值，当前最大值都小于target时，忽略
            int max1 = nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];
            if (max1 < target) {
                continue;
            }

            // 遍历b
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                // 获取当前最小值，当最小值都大于target时，退出
                int min2 = nums[i] + nums[j] + nums[left] + nums[left + 1];
                if (min2 > target) {
                    break;
                }    
                
                // 获取当前最大值，当前最大值都小于target时，忽略
                int max2 = nums[i] + nums[j] + nums[right] + nums[right - 1];
                if (max2 < target) {
                    continue;
                }
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        // 去除重复元素
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return ans;

    }
}
// @lc code=end

