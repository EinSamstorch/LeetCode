import java.util.Random;

/*
 * @lc app=leetcode.cn id=1206 lang=java
 *
 * [1206] 设计跳表
 *
 * https://leetcode-cn.com/problems/design-skiplist/description/
 *
 * algorithms
 * Hard (60.53%)
 * Likes:    56
 * Dislikes: 0
 * Total Accepted:    4.1K
 * Total Submissions: 6.7K
 * Testcase Example:  '["Skiplist","add","add","add","search","add","search","erase","erase","search"]\r\n' +
  '[[],[1],[2],[3],[0],[4],[1],[0],[1],[1]]\r'
 *
 * 不使用任何库函数，设计一个跳表。
 * 
 * 跳表是在 O(log(n))
 * 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * 
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
 * 
 * 
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * 
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是
 * O(log(n))，空间复杂度是 O(n)。
 * 
 * 在本题中，你的设计应该要包含这些函数：
 * 
 * 
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num
 * ，删除其中任意一个即可。
 * 
 * 
 * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
 * 
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 * 
 * 样例:
 * 
 * Skiplist skiplist = new Skiplist();
 * 
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 * 
 * 
 * 约束条件:
 * 
 * 
 * 0 <= num, target <= 20000
 * 最多调用 50000 次 search, add, 以及 erase操作。
 * 
 * 
 */

// @lc code=start
class Skiplist {
    Node head;
    public Skiplist() {
        head = new Node(null, null, 0);
    }
    
    public boolean search(int target) {
        // 先往右再往下，缩小区间
        for (Node p = head; p != null; p = p.down) {
            while (p.right != null && p.right.val < target) {
                p = p.right;
            }
            if (p.right != null && p.right.val == target) {
                return true;
            }
        }
        return false;
    }
    
    Random rand = new Random();
    Node[] stack = new Node[64];
    
    public void add(int num) {
        int lv = -1;
        for (Node p = head; p != null; p = p.down) {
            while (p.right != null && p.right.val < num) {
                p = p.right;
            }
            stack[++lv] = p;
        }
    }
    
    public boolean erase(int num) {

    }
}

class Node {
    int val;
    Node right, down;

    public Node (Node right, Node down, int val) {
        this.right = right;
        this.down = down;
        this.val = val;
    }
}

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
// @lc code=end

