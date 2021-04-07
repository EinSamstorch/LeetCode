import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=743 lang=java
 *
 * [743] 网络延迟时间
 *
 * https://leetcode-cn.com/problems/network-delay-time/description/
 *
 * algorithms
 * Medium (47.15%)
 * Likes:    241
 * Dislikes: 0
 * Total Accepted:    23.6K
 * Total Submissions: 50K
 * Testcase Example:  '[[2,1,1],[2,3,1],[3,4,1]]\n4\n2'
 *
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点，
 * wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 *
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 *
 *
 * 示例 2：
 *
 *
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 *
 *
 * 示例 3：
 *
 *
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1
 * 1
 * times[i].length == 3
 * 1 i, vi
 * ui != vi
 * 0 i
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 *
 */

// @lc code=start
class Solution {
    /**
     * dist[node] 记录的是信号最早到达 node 的时间。当我们访问 node 时，若经过了传递时间这个信号是最早到达该节点的，则我们广播这个信号
     */
    Map<Integer, Integer> dist = new HashMap<>();

    /**
     * 深度优先搜索
     */
    public int networkDelayTime1(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            // if (!graph.containsKey(edge[0])) {
            //     graph.put(edge[0], new ArrayList<>());
            // }
            // graph.get(edge[0]).add(new int[]{edge[2], edge[1]});
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new int[]{edge[2], edge[1]});
        }
        for (int node : graph.keySet()) {
            Collections.sort(graph.get(node), (a, b) -> a[0] - b[0]);
        }
        for (int node = 1; node <= n; node++) {
            dist.put(node, Integer.MAX_VALUE);
        }

        dfs(graph, k, 0);
        int ans = 0;

        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;

    }

    private void dfs(Map<Integer, List<int[]>> graph, int node, int elapsed) {
        // 为了加快速度，在访问每个节点时，若传递该信号的时间比已有信号到达的时间长，则我们退出该信号。
        if (elapsed >= dist.get(node)) {
            return;
        }
        dist.put(node, elapsed);
        if (graph.containsKey(node)) {
            for (int[] info : graph.get(node)) {
                dfs(graph, info[1], elapsed + info[0]);
            }
        }
    }

    /**
     * 单源dijkstra算法
     * Dijkstra's 算法是每次扩展一个距离最短的点，更新与其相邻点的距离。
     */
    public int networkDelayTime2(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        for (int node = 1; node <= n; node++) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(k, 0);
        boolean[] seen = new boolean[n + 1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                if (!seen[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }

            if (candNode < 0) {
                break;
            }
            seen[candNode] = true;
            if (graph.containsKey(candNode)) {
                for (int[] info : graph.get(candNode)) {
                    dist.put(info[0], Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));    
                }
            }
        }
        int ans = 0;
        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, cand);
        }
        return ans;
    }

    public int networkDelayTime3(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, k});

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) {
                continue;
            }
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei)) {
                        heap.offer(new int[]{d+d2, nei});
                    }
                }
        }

        if (dist.size() != n) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }

    /**
     * Dijkstra's 算法是每次扩展一个距离最短的点，更新与其相邻点的距离。
     * 堆优化之后就不用松弛操作了,因为每次都去最近的
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        // 虽然这边可以把list换成set来保证节点不会重复进入集合,但是,arraylist性能比hashset快的多,所以还是arraylist快一点
        Map<Integer, List<int[]>> map = new HashMap<>();
        // 初始化邻接表
        for (int[] edge : times) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
        }

        // 初始化dis数组和vis数组
        int[] dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N + 1];

        // 起点的dis为0，但是别忘记0也要搞一下，因为它是不参与的，我计算结果的时候直接用了stream，所以这个0也就要初始化下了
        dis[K] = 0;
        dis[0] = 0;

        // new一个小堆出来，按照dis升序排，一定要让它从小到大排，省去了松弛工作
        // PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> dis[o1] - dis[o2]);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> dis[o]));
        // 把起点放进去
        queue.offer(K);

        while (!queue.isEmpty()) {
            // 当队列不空，拿出一个源出来
            Integer cand = queue.poll();
         		if(visited[cand]) {
                    continue; 
                 } 
            // 把它标记为访问过
            visited[cand] = true;
            // 遍历它的邻居们，当然可能没邻居，这里用getOrDefault处理就很方便
            List<int[]> list = map.getOrDefault(cand, Collections.emptyList());
            for (int[] arr : list) {
                int next = arr[0];
                // 如果这个邻居访问过了，继续
                if (visited[next]) continue;
                // 更新到这个邻居的最短距离，看看是不是当前poll出来的节点到它更近一点
                dis[next] = Math.min(dis[next], dis[cand] + arr[1]);
                queue.offer(next);
            }
        }
        // 拿到数组中的最大值比较下，返回结果
        int res = Arrays.stream(dis).max().getAsInt();
        return res == Integer.MAX_VALUE ? -1 : res;
    }
} 
// @lc code=end

