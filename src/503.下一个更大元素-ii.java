import java.util.Arrays;
import java.util.Stack;

/*
 * @lc app=leetcode.cn id=503 lang=java
 *
 * [503] 下一个更大元素 II
 */

// @lc code=start
class Solution {
        // public static int[] nextGreaterElements2(int[] nums) {
        //     int len = nums.length;
        //     int[] doubleArr = new int[len * 2];

        //     System.arraycopy(nums, 0, doubleArr, 0, len);
        //     System.arraycopy(nums, 0, doubleArr, len, len);

        //     List<Integer> list = new ArrayList<>();
        //     for (int i = 0; i < len; i++) {
        //         int cur = doubleArr[i];
        //         boolean find = false;
        //         for (int j = i + 1; j < len * 2; j++) {
        //             if (doubleArr[j] > cur) {
        //                 list.add(doubleArr[j]);
        //                 find = true;
        //                 break;
        //             }
        //         }
        //         if(!find){
        //             list.add(-1);
        //         }
        //     }

        //     return list.stream().mapToInt(Integer::intValue).toArray();
        // }

        public static int[] nextGreaterElements(int[] nums) {
            Stack<Integer> stack = new Stack<>();
        
            int[] res = new int[nums.length];
            Arrays.fill(res, -1);
    
            for(int i = 0; i < 2 * nums.length ; i++) {
                // 取模，实现循环数组
                int index = i % nums.length;
                // 找到下一个更大元素
                while(!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                    // 栈中保存的是索引
                    res[stack.pop()] = nums[index];
                }
                // 如果栈为空 且栈顶大于下一个要加入的元素，将该元素入栈
                stack.push(index);
            }
    
            return res;
        }
    


}
// @lc code=end

