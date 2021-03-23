import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=1202 lang=java
 *
 * [1202] 交换字符串中的元素
 *
 * https://leetcode-cn.com/problems/smallest-string-with-swaps/description/
 *
 * algorithms
 * Medium (49.92%)
 * Likes:    217
 * Dislikes: 0
 * Total Accepted:    24.5K
 * Total Submissions: 49.1K
 * Testcase Example:  '"dcab"\n[[0,3],[1,2]]'
 *
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0
 * 开始）。
 * 
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * 
 * 
 * 
 * 示例 1:
 * 
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释： 
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 
 * 
 * 示例 2：
 * 
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 
 * 示例 3：
 * 
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 * 
 * 
 */

// @lc code=start
class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0) {
            return s;
        }

        // 1 将任意交换的节点对输入并查集
        int len = s.length();
        UnionFind uf = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            uf.union(index1, index2);
        }

        // 2 构建映射关系
        char[] charArray = s.toCharArray();
        // key:连通分量的代表元，value：同一个连通分量的字符集合(保存在一个优先队列里)
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int root = uf.find(i);
            // if (map.containsKey(root)) {
            //     map.get(root).offer(charArray[i]);
            // } else {
            //     PriorityQueue<Character> minHeap = new PriorityQueue<>();
            //     minHeap.offer(charArray[i]);
            //     map.put(root, minHeap);
            // }
            // 上面六行代码等价于下面一行代码
            map.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }

        // 3 分组排序  重组字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = uf.find(i);
            sb.append(map.get(root).poll());
        }
        return sb.toString();
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
}
// @lc code=end

