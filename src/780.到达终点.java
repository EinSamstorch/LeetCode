/*
 * @lc app=leetcode.cn id=780 lang=java
 *
 * [780] 到达终点
 *
 * https://leetcode-cn.com/problems/reaching-points/description/
 *
 * algorithms
 * Hard (27.92%)
 * Likes:    81
 * Dislikes: 0
 * Total Accepted:    2.3K
 * Total Submissions: 8.3K
 * Testcase Example:  '9\n5\n12\n8'
 *
 * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
 * 
 * 给定一个起点 (sx, sy) 和一个终点 (tx, ty)，如果通过一系列的转换可以从起点到达终点，则返回 True ，否则返回 False。
 * 
 * 
 * 示例:
 * 输入: sx = 1, sy = 1, tx = 3, ty = 5
 * 输出: True
 * 解释:
 * 可以通过以下一系列转换从起点转换到终点：
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * 
 * 输入: sx = 1, sy = 1, tx = 2, ty = 2
 * 输出: False
 * 
 * 输入: sx = 1, sy = 1, tx = 1, ty = 1
 * 输出: True
 * 
 * 
 * 
 * 注意:
 * 
 * 
 * sx, sy, tx, ty 是范围在 [1, 10^9] 的整数。
 * 
 * 
 */

import java.util.HashSet;
import java.util.Set;

// @lc code=start
class Solution {
    Set<Point> seen;
    int tx;
    int ty;

    /**  
     * 递归，超出时间限制
     */
    public boolean reachingPoints1(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) {
            return false;
        }
        if (sx == tx && sy == ty) {
            return true;
        }
        return reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sx + sy, tx, ty);
    }

    /**   
     * 为了避免重复计算，使用一个集合seen存储方法一中递归搜索到的子点
     */
    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        seen = new HashSet<>();
        this.tx = tx;
        this.ty = ty;
        search(new Point(sx, sy));
        return seen.contains(new Point(tx, ty));
    }

    private void search(Point point) {
        if (seen.contains(point)) {
            return;
        }
        if (point.x > tx || point.y > ty) {
            return;
        }
        seen.add(point);
        search(new Point(point.x + point.y, point.y));
        search(new Point(point.x, point.x + point.y));
    }

    /**
     * 回溯法 从起点到终点有很多路径，终点到起点就一个路径
     * 每个父点 (x, y) 都有两个子点 (x, x+y) 和 (x+y, y)。
     * 由于坐标不能为负，每个子点 (x, y) 只能有一个父点，
     * 当 x >= y 时父点为 (x-y, y)；当 y > x 时父点为 (x, y-x)。
     */
    public boolean reachingPoints3(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (sx == tx && sy == ty) {
                return true;
            }
            if (tx > ty) {
                tx -= ty;
            } else {
                ty -= tx;
            }
        }
        return false;
    }

    /**
     * 回溯法 取模变体
     * 当 tx > ty 时，求解父点的运算是 tx - ty，并且它的往上 tx = tx % ty 个父点都是减去 ty。
     * 当同时满足 tx > ty 和 ty <= sy 时，可以一次性执行所有的这些操作，直接令 tx %= ty。
     */
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == ty) {
                break;
            }
            if (tx > ty) {
                if (ty > sy) {
                    tx %= ty;
                } else {
                    return (tx - sx) % ty == 0;
                }
            } else {
                if (tx > sx) {
                    ty %= tx;
                } else {
                    return (ty - sy) % tx == 0;
                }
            }
        }
        return false;
    }
}

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x; 
        this.y = y;
    }
}
// @lc code=end

