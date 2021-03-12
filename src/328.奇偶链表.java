import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=328 lang=java
 *
 * [328] 奇偶链表
 *
 * https://leetcode-cn.com/problems/odd-even-linked-list/description/
 *
 * algorithms
 * Medium (65.66%)
 * Likes:    379
 * Dislikes: 0
 * Total Accepted:    94.7K
 * Total Submissions: 144.3K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 
 * 
 * 示例 2:
 * 
 * 输入: 2->1->3->5->6->4->7->NULL 
 * 输出: 2->3->6->7->1->5->4->NULL
 * 
 * 说明:
 * 
 * 
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * 虽然繁琐
     * 但是，是自己做出来得啊！
     * @param head
     * @return
     */
    public ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        List<ListNode> list = new ArrayList<>();
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode preHead = dummy;
        for (int i = 0; i < list.size(); i += 2) {
            dummy.next = list.get(i);
            dummy = dummy.next;
        }
        for (int i = 1; i < list.size(); i += 2) {
            dummy.next = list.get(i);
            dummy = dummy.next;
        }
        dummy.next = null;
        return preHead.next;
    }

    /**
     * 用两个链表分别储存，然后拼接
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null) {
            // odd.next = even.next;
            // odd = odd.next;
            // even.next = odd.next;
            // even = even.next;
            // 皮一下，用连等
            odd = odd.next = even.next;
            even = even.next = odd.next;
        }
        // 扫描结束时，奇偶节点是分开的
        odd.next = evenHead;
        return head;
    }
}
// @lc code=end

