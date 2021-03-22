import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=973 lang=java
 *
 * [973] 最接近原点的 K 个点
 *
 * https://leetcode-cn.com/problems/k-closest-points-to-origin/description/
 *
 * algorithms
 * Medium (63.44%)
 * Likes:    231
 * Dislikes: 0
 * Total Accepted:    58.6K
 * Total Submissions: 92.3K
 * Testcase Example:  '[[1,3],[-2,2]]\n1'
 *
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * 
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释： 
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 
 * 
 * 示例 2：
 * 
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 使用一个大顶堆实时维护k个最小的距离平方
     */
    public int[][] kClosest1(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((array1, array2) -> array2[0] - array1[0]);
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }

        int rows = points.length;
        for (int i = k; i < rows; i++) {
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (distance < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[]{distance, i});
            }
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }

    public int[][] kClosest2(int[][] points, int k) {
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] point1, int[] point2) {
                return (point1[0] * point1[0] + point1[1] * point1[1]) - (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, k);
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

        for (int[] point : points) {
            if (pq.size() < k) {
                pq.offer(point);
            }else if (pq.comparator().compare(point, pq.peek()) > 0) {
                pq.poll();
                pq.offer(point);
            }
        }

        int[][] res = new int[k][2];
        int index = 0;
        for (int[] point : pq) {
            res[index++] = point;
        }
        return res;
    }

}
// @lc code=end

