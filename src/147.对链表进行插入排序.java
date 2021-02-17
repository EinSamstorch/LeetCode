/*
 * @lc app=leetcode.cn id=147 lang=java
 *
 * [147] 对链表进行插入排序
 *
 * https://leetcode-cn.com/problems/insertion-sort-list/description/
 *
 * algorithms
 * Medium (67.19%)
 * Likes:    354
 * Dislikes: 0
 * Total Accepted:    75.5K
 * Total Submissions: 112.3K
 * Testcase Example:  '[4,2,1,3]'
 *
 * 对链表进行插入排序。
 * 
 * 
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * 
 * 
 * 
 * 插入排序算法：
 * 
 * 
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 
 * 
 * 示例 2：
 * 
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode() {} ListNode(int val) { this.val = val; } ListNode(int val,
 * ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        // 1.首先判断给定的链表是否为空，若为空，则不需要进行排序
        if (head == null) {
            return head;
        }
        // 2.链表初始化操作
        ListNode dummyHead = new ListNode(0, head);
        // 维护lastSorted为链表已经排好序的最后一个节点并初始化
        ListNode lastSorted = head;
        // 维护cur为待插入元素并初始化
        ListNode cur = head.next;

        // 3.插入排序
        while (cur != null) {
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                // 从链表头开始遍历，pre是插入节点cur位置的前一个节点
                ListNode pre = dummyHead;
                while (pre.next.val <= cur.val) {
                    pre = pre.next;
                }
                // 以下三行是为了完成对cur的插入
                lastSorted.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = lastSorted.next;
        }
        return dummyHead.next;
    }
}
// @lc code=end
