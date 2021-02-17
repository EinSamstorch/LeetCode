/*
 * @lc app=leetcode.cn id=82 lang=java
 *
 * [82] 删除排序链表中的重复元素 II
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/description/
 *
 * algorithms
 * Medium (50.22%)
 * Likes:    451
 * Dislikes: 0
 * Total Accepted:    85.1K
 * Total Submissions: 169.4K
 * Testcase Example:  '[1,2,3,3,4,4,5]'
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 
 * 示例 1:
 * 
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 
 * 
 * 示例 2:
 * 
 * 输入: 1->1->1->2->3
 * 输出: 2->3
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
     * 自己写的，还是狗屁不同
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(0, head);
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            if (cur.val == next.val) {
                pre.next = next.next;
                cur = next.next;
            } else {
                pre = cur;
                cur = next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            // 初始化的时候，pre指向的是哑节点，所以比较逻辑是pre的下一个节点和cur的下一个节点
            if (pre.next.val != cur.next.val) {
                pre = pre.next;
                cur = cur.next;
            } else {
                // 如果pre和cur指向的节点值相等，就不断的移动cur，直到值不等
                while (cur.next != null && pre.next.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return dummy.next;
    }


}
// @lc code=end

