import java.util.Arrays;
import java.util.Comparator;

/*
 * @lc app=leetcode.cn id=179 lang=java
 *
 * [179] 最大数
 *
 * https://leetcode-cn.com/problems/largest-number/description/
 *
 * algorithms
 * Medium (37.94%)
 * Likes:    470
 * Dislikes: 0
 * Total Accepted:    53K
 * Total Submissions: 139.7K
 * Testcase Example:  '[10,2]'
 *
 * 给定一组非负整数 nums，重新排列它们每个数字的顺序（每个数字不可拆分）使之组成一个最大的整数。
 * 
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：nums = [10,2]
 * 输出："210"
 * 
 * 示例 2：
 * 
 * 
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：nums = [1]
 * 输出："1"
 * 
 * 
 * 示例 4：
 * 
 * 
 * 输入：nums = [10]
 * 输出："10"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 
 * 
 * 
 */

// @lc code=start
class Solution {

    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }
    public String largestNumber(int[] nums) {
        int len = nums.length;
        String[] asStrs = new String[len];
        for (int i = 0; i < len; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }
        // 如果仅按降序排序，有相同的开头数字的时候会出现问题。
        //比方说，样例2按降序排序得到的数字是95343303，然而交换33和30的位置可以得到正确答案9534330。
        // 因此，每一对数在排序的比较过程中，我们比较两种连接顺序哪一种更好
        // Arrays.sort(asStrs, new LargerNumberComparator());
        Arrays.sort(asStrs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        if (asStrs[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String numAsStr : asStrs) {
            sb.append(numAsStr);
        }
        return sb.toString();

    }
}
// @lc code=end

