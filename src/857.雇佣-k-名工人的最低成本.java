import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * @lc app=leetcode.cn id=857 lang=java
 *
 * [857] 雇佣 K 名工人的最低成本
 *
 * https://leetcode-cn.com/problems/minimum-cost-to-hire-k-workers/description/
 *
 * algorithms
 * Hard (46.90%)
 * Likes:    90
 * Dislikes: 0
 * Total Accepted:    2K
 * Total Submissions: 4.3K
 * Testcase Example:  '[10,20,5]\n[70,50,30]\n2'
 *
 * 有 N 名工人。 第 i 名工人的工作质量为 quality[i] ，其最低期望工资为 wage[i] 。
 * 
 * 现在我们想雇佣 K 名工人组成一个工资组。在雇佣 一组 K 名工人时，我们必须按照下述规则向他们支付工资：
 * 
 * 
 * 对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。
 * 工资组中的每名工人至少应当得到他们的最低期望工资。
 * 
 * 
 * 返回组成一个满足上述条件的工资组至少需要多少钱。
 * 
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入： quality = [10,20,5], wage = [70,50,30], K = 2
 * 输出： 105.00000
 * 解释： 我们向 0 号工人支付 70，向 2 号工人支付 35。
 * 
 * 示例 2：
 * 
 * 输入： quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * 输出： 30.66667
 * 解释： 我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 1 <= K <= N <= 10000，其中 N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * 与正确答案误差在 10^-5 之内的答案将被视为正确的。
 * 
 * 
 */

// @lc code=start
class Solution {
    /**
     * 显然，当我们选择 K 名工人时，会只要有一名工人恰好拿到了他的最低期望工资。因此，我们可以枚举是哪一名工人恰好拿到了他的最低期望工资，并检查在当前的“工资/质量”比值下，其他工人拿到的工资是否不少于他们的最低期望工资。如果有至少 K - 1 名工人满足条件，那么我们就在这些工人中选出 K - 1 名拿到工资最低的，加上枚举的那一名工人的最低期望工资，就得到了一个答案。在所有的答案中，返回最小
     */
    public double mincostToHireWorkers1(int[] quality, int[] wage, int K) {
        int N = quality.length;
        double ans = Double.MAX_VALUE;

        for (int captain = 0; captain < N; captain++) {
            double factor = (double) wage[captain] / quality[captain];
            double[] prices = new double[N];
            int t = 0;
            for (int worker = 0; worker < N; worker++) {
                double price = factor * quality[worker];
                // 要找到拿最低期望工资的
                if (price < wage[worker]) {
                    continue;
                }
                prices[t++] = price;
            }
            if (t < K) {
                continue;
            }
            Arrays.sort(prices, 0, t);
            double cand = 0;
            for (int i = 0; i < K; i++) {
                cand += prices[i];
            }
            ans = Math.min(ans, cand);
        }
        return ans;
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Worker[] workers = new Worker[N];

        for (int i = 0; i < N; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        // 按照工人的价值，进行升序排序
        Arrays.sort(workers);

        double ans = Double.MAX_VALUE;
        int sumq = 0;
        // // 默认的优先队列是从小到大排序，首先最小的元素出列
        // PriorityQueue<Integer> pool = new PriorityQueue<>();

        // 按照从大到小排序
        PriorityQueue<Integer> pool = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (Worker worker : workers) {
            pool.offer(worker.quality);
            sumq += worker.quality;
            // 每添加进来一个新员工，那么就要从原K个员工中删掉一个质量最大的，所以要维护一个大顶堆
            if (pool.size() > K) {
                sumq -= pool.poll();
            }
            if (pool.size() == K) {
                ans = Math.min(ans, sumq * worker.ratio());
            }
        }
        return ans;
    }
}

class Worker implements Comparable<Worker> {
    
    public int quality, wage;

    public Worker(int quality, int wage) {
        this.quality = quality;
        this.wage = wage;
    }

    public double ratio() {
        return (double) wage / quality;
    }

    @Override
    public int compareTo(Worker o) {
        return Double.compare(ratio(), o.ratio());
    }
    
}
// @lc code=end

