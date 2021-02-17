/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 *
 * https://leetcode-cn.com/problems/sort-list/description/
 *
 * algorithms
 * Medium (67.59%)
 * Likes:    993
 * Dislikes: 0
 * Total Accepted:    138.7K
 * Total Submissions: 205.2K
 * Testcase Example:  '[4,2,1,3]'
 *
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 
 * 进阶：
 * 
 * 
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 
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
     * 另一种递归方法
     */
    public ListNode sortList(ListNode head) {
        // 1.递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2.找到链表中间节点并断开链表，递归下探
        ListNode mid = getMedian(head);
        ListNode rightHead = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 3.合并有序链表
        return merge(left, right);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        // 哨兵节点，用于返回合并后的链表
        ListNode dummyHead = new ListNode(0);
        ListNode pre = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return dummyHead.next;
    }

    /**
     * 返回的是中间偏左的节点
     */
    public ListNode getMedian(ListNode head)  {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
// @lc code=end

