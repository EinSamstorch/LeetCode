import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import jdk.jshell.TypePrinter.AnonymousTypeKind;

/*
 * @lc app=leetcode.cn id=460 lang=java
 *
 * [460] LFU 缓存
 *
 * https://leetcode-cn.com/problems/lfu-cache/description/
 *
 * algorithms
 * Hard (43.54%)
 * Likes:    344
 * Dislikes: 0
 * Total Accepted:    22.1K
 * Total Submissions: 50.7K
 * Testcase Example:  '["LFUCache","put","put","get","put","get","get","put","get","get","get"]\n' +
  '[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]'
 *
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 
 * 实现 LFUCache 类：
 * 
 * 
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value) -
 * 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除
 * 最久未使用 的键。
 * 
 * 
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * 
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put
 * 操作，使用计数器的值将会递增。
 * 
 * 
 * 
 * 示例：
 * 
 * 
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get",
 * "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * 
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lFUCache = new LFUCache(2);
 * lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lFUCache.get(1);      // 返回 1
 * ⁠                     // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 * ⁠                     // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lFUCache.get(2);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 * ⁠                     // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 * ⁠                     // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lFUCache.get(1);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 * ⁠                     // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lFUCache.get(4);      // 返回 4
 * ⁠                     // cache=[3,4], cnt(4)=2, cnt(3)=3
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 0 
 * 最多调用 10^5 次 get 和 put 方法
 * 
 * 
 * 
 * 
 * 进阶：你可以为这两种操作设计时间复杂度为 O(1) 的实现吗？
 * 
 */

// @lc code=start
class LFUCache {
    // 缓存容量，时间戳
    int capacity, time;
    Map<Integer, Node> map;
    TreeSet<Node> set;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.time = 0;
        map = new HashMap<Integer, Node>();
        set = new TreeSet<Node>();
    }
    
    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        // 如果哈希表中没有键 key，返回 -1
        if (!map.containsKey(key)) {
            return -1;
        }
        // 从哈希表中得到旧的缓存
        Node cache = map.get(key);
        // 从平衡二叉树中删除旧的缓存
        set.remove(cache);
        // 将旧缓存更新
        cache.cnt += 1;
        cache.time = ++time;
        // 将新缓存重新放入哈希表和平衡二叉树中
        set.add(cache);
        map.put(key, cache);
        return cache.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!map.containsKey(key)) {
            // 如果到达缓存容量上限
            if (map.size() == capacity) {
                // 从哈希表和平衡二叉树中删除最近最少使用的缓存
                map.remove(set.first().key);
                set.remove(set.first());
            }
            // 创建新的缓存
            Node cache = new Node(1, ++time, key, value);
            // 将新缓存放入哈希表和平衡二叉树中
            map.put(key, cache);
            set.add(cache);
        } else {
            // 这里和 get() 函数类似
            Node cache = map.get(key);
            set.remove(cache);
            cache.cnt += 1;
            cache.time = ++time;
            cache.value = value;
            set.add(cache);
            map.put(key, cache);
        }
    }
}

class Node implements Comparable<Node> {
    int cnt, time, key, value;

    Node(int cnt, int time, int key, int value) {
        this.cnt = cnt;
        this.time = time;
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Node) {
            Node node = (Node) anObject;
            return this.cnt == node.cnt && this.time == node.time;
        }
        return false;
    }

    public int compareTo(Node node) {
        return cnt == node.cnt ? time - node.time : cnt - node.cnt;
    }

    public int hashCode() {
        return cnt * 1000000007 + time;
    }
}



/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

