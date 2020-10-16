/*
 * @lc app=leetcode.cn id=605 lang=java
 *
 * [605] 种花问题
 *
 * https://leetcode-cn.com/problems/can-place-flowers/description/
 *
 * algorithms
 * Easy (32.22%)
 * Likes:    184
 * Dislikes: 0
 * Total Accepted:    36.9K
 * Total Submissions: 114.6K
 * Testcase Example:  '[1,0,0,0,1]\n1'
 *
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n
 * 朵花？能则返回True，不能则返回False。
 * 
 * 示例 1:
 * 
 * 
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * 
 * 
 * 示例 2:
 * 
 * 
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * 
 * 
 * 注意:
 * 
 * 
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * (1)首元素:首元素尾0,它的相邻下一个元素为0 
     * (2)中间元素:中简元素为零，它的左右两个相邻元素为0 
     * (3)尾元素:尾元素为0，它的上一个相邻元素为0
     * 
     */
    public boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int i = 0;
        int count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }

    // 自己想的就是这个方法，但是却没有用编程语言描述出来
    public boolean canPlaceFlowers(int[] flowered, int n) {
        // 不种花，没花坛也没关系
        if (n == 0) {
            return true;
        }
        // 要种花，没花坛种不了
        if (flowered == null) {
            return false;
        }
        int len = flowered.length;
        int flower = n;
        
        for (int i = 0; i < len; i++) {
            if (flowered[i] == 0 && legal(flowered,i)) {
                // 种花
                flowered[i] = 1;
                flower--;
                // 下一个位置肯定不能种了，跳过
                i++;
                // 提前完工，撒花
                if (flower == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断种花位置的合法性
     */
    private boolean legal(int[] flowered, int pos) {
        // 判断false的条件比true简单得多，所以先判断false就可以
        if (pos - 1 >= 0 && flowered[pos - 1] == 1) {
            return false;
        }
        if (pos + 1 < flowered.length && flowered[pos + 1] == 1){
            return false;
        }
        return true;
    }
}
// @lc code=end
