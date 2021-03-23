import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1722 lang=java
 *
 * [1722] 执行交换操作后的最小汉明距离
 *
 * https://leetcode-cn.com/problems/minimize-hamming-distance-after-swap-operations/description/
 *
 * algorithms
 * Medium (51.68%)
 * Likes:    31
 * Dislikes: 0
 * Total Accepted:    4K
 * Total Submissions: 7.7K
 * Testcase Example:  '[1,2,3,4]\n[2,1,4,5]\n[[0,1],[2,3]]'
 *
 * 给你两个整数数组 source 和 target ，长度都是 n 。还有一个数组 allowedSwaps ，其中每个 allowedSwaps[i]
 * = [ai, bi] 表示你可以交换数组 source 中下标为 ai 和 bi（下标从 0 开始）的两个元素。注意，你可以按 任意 顺序 多次
 * 交换一对特定下标指向的元素。
 * 
 * 相同长度的两个数组 source 和 target 间的 汉明距离 是元素不同的下标数量。形式上，其值等于满足 source[i] !=
 * target[i] （下标从 0 开始）的下标 i（0 <= i <= n-1）的数量。
 * 
 * 在对数组 source 执行 任意 数量的交换操作后，返回 source 和 target 间的 最小汉明距离 。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
 * 输出：1
 * 解释：source 可以按下述方式转换：
 * - 交换下标 0 和 1 指向的元素：source = [2,1,3,4]
 * - 交换下标 2 和 3 指向的元素：source = [2,1,4,3]
 * source 和 target 间的汉明距离是 1 ，二者有 1 处元素不同，在下标 3 。
 * 
 * 
 * 示例 2：
 * 
 * 输入：source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
 * 输出：2
 * 解释：不能对 source 执行交换操作。
 * source 和 target 间的汉明距离是 2 ，二者有 2 处元素不同，在下标 1 和下标 2 。
 * 
 * 示例 3：
 * 
 * 输入：source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps =
 * [[0,4],[4,2],[1,3],[1,4]]
 * 输出：0
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * n == source.length == target.length
 * 1 <= n <= 10^5
 * 1 <= source[i], target[i] <= 10^5
 * 0 <= allowedSwaps.length <= 10^5
 * allowedSwaps[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int ans = 0;
        int len = source.length;
        UnionFind uf = new UnionFind(len);

        // 1. 将任意交互的节点对输入并查集
        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        // 2. 使用hash表映射target数组中每个元素和其位置，以便后续查找source中的元素在数组target中的位置 
        // map中的value是一个list，是因为target中可能有重复的元素
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.computeIfAbsent(target[i], key -> new LinkedList<>()).add(i);
        }

        for (int i = 0; i < len; i++) {
            // 如果该元素不在target数组中，则对汉明距离有贡献
            if (!map.containsKey(source[i])) {
                ans++;
                continue;
            }
            // 如果该元素在target数组中，通过哈希表找到其在target数组中的位置j，通过并查集查看i和j是否在一个连通分量
            // 如果不在一个连通分量，则对汉明距离有贡献
            List<Integer> list = map.get(source[i]);
            Iterator<Integer> it = list.iterator();
            boolean flag = false;
            while (it.hasNext()) {
                Integer index = it.next();
                if (uf.isConnected(i, index)) {
                    // 在本题中还要注意两个数组 source 和 target 可能存在相同的元素，所以哈希表映射数组 target时，要保存每个元素对应的位置列表。在用并查集查看是否连通时，要将连通的位置在列表中移除。
                    it.remove();
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ans++;
            }
        }
        return ans;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    int count;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }

        if (rank[rootX] == rank[rootY]) {
            parent[rootX] = rootY;
            // 此时以rootY为根结点的树的高度仅加了1
            rank[rootY]++;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
            // 此时以rootY为根节点的树的高度不变
        } else {
            parent[rootY] = rootX;
        }
        count--;
    }

    public int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean isConnected(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        return rootX == rootY;
    }
}
// @lc code=end

