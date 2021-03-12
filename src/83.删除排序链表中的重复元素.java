/*
 * @lc app=leetcode.cn id=83 lang=java
 *
 * [83] 删除排序链表中的重复元素
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/description/
 *
 * algorithms
 * Easy (51.98%)
 * Likes:    469
 * Dislikes: 0
 * Total Accepted:    181.5K
 * Total Submissions: 349.1K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 
 * 示例 1:
 * 
 * 输入: 1->1->2
 * 输出: 1->2
 * 
 * 
 * 示例 2:
 * 
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
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
     * 自己写的啥狗屁东西
     */
    public ListNode deleteDuplicates1(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = new ListNode(0, head);
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            if (cur.val == next.val) {
                pre.next = next;
                cur = next;
            } else {
                cur = cur.next;
            }
        }
        return pre.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            if (cur.val == next.val) {
                // 相等指向下下个
                cur.next = next.next;
            } else {
                // 不相等，下一个
                cur = cur.next;
            }
        }
        return head;
    }
}
// @lc code=end

