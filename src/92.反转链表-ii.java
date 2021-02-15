import java.util.List;

/*
 * @lc app=leetcode.cn id=92 lang=java
 *
 * [92] 反转链表 II
 *
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/description/
 *
 * algorithms
 * Medium (52.24%)
 * Likes:    670
 * Dislikes: 0
 * Total Accepted:    101.7K
 * Total Submissions: 194.6K
 * Testcase Example:  '[1,2,3,4,5]\n2\n4'
 *
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 
 * 示例:
 * 
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    ListNode successor = null;
    /**
     * 反转链表前n个元素
     */
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    // labuladong 递归的方法
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if (m == 1) {
            // 确定m的位置
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (m > 1) {

            prev = prev.next;
            m--;
            n--;
        }
        // 将head移到需要反转节点的首部
        head = prev.next;
        while (n > 1) {
            ListNode next = head;
            // head 节点连接next节点之后的链表部分，也就是向后移动一位
            head.next = head.next.next;
            next.next = prev.next;
            prev.next = next;
            n--;
        }
        return dummy.next;

    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        // move the two pointers untill they reach the preper starting point in the list
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }
        // 这两个指针用于修复最后的连接
        ListNode con = prev, tail = cur;
        // 迭代的反转节点
        ListNode third = null;
        // 迭代结束后，prev停留在了反转部分的末尾，cur是末尾的下一位
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }
        tail.next = cur;
        return head;
    }

}
// @lc code=end
