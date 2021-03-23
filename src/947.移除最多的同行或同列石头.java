import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=947 lang=java
 *
 * [947] 移除最多的同行或同列石头
 *
 * https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column/description/
 *
 * algorithms
 * Medium (61.37%)
 * Likes:    197
 * Dislikes: 0
 * Total Accepted:    23.4K
 * Total Submissions: 38.1K
 * Testcase Example:  '[[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]'
 *
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子
 * 的最大数量。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 
 * 示例 2：
 * 
 * 
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 
 * 示例 3：
 * 
 * 
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 
 * 0 i, yi 
 * 不会有两块石头放在同一个坐标点上
 * 
 * 
 */

// @lc code=start
class Solution {
    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind();
        
        for(int[] stone : stones) {
            // 为了区分横坐标和纵坐标相同的情况  0 <= x, y <= 10000
            uf.union(stone[0] + 10001, stone[1]);
        }
        // 一定可以把一个连通分量中的顶点根据规则删除到只剩一个
        // 最多可以移除的石头的个数 = 所有石头的个数 - 连通分量的个数
        return stones.length - uf.getCount();
    }
}

class UnionFind {
    /**
     * 并查集底层元素的数量取决于stone[][]数组中不用的横坐标，纵坐标的数目
     * 由于这种不确定，将并查集的底层设置为一个hash表
     */
    private Map<Integer, Integer> parent;
    private int count;

    public int getCount() {
        return count;
    }

    public UnionFind() {
        parent = new HashMap<>();
        count = 0;
    }

    public int find(int x) {
        if (!parent.containsKey(x)) {
            parent.put(x, x);
            // 并查集中新加入一个节点，节点的父亲节点是它自己，所以连通分量的总数+1
            count++;
        }
        if (x != parent.get(x)) {
            parent.put(x, find(parent.get(x)));
        }
        return parent.get(x);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        parent.put(rootX, rootY);
        // 两个连通分量合并为1个，连通分量的总数-1
        count--;
    }

}
// @lc code=end

