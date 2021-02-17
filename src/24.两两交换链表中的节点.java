/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
 *
 * algorithms
 * Medium (69.07%)
 * Likes:    811
 * Dislikes: 0
 * Total Accepted:    219.9K
 * Total Submissions: 318.3K
 * Testcase Example:  '[1,2,3,4]'
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 
 * 
 * 示例 1：
 * 
 * 
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 
 * 
 * 示例 2：
 * 
 * 
 * 输入：head = []
 * 输出：[]
 * 
 * 
 * 示例 3：
 * 
 * 
 * 输入：head = [1]
 * 输出：[1]
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 
 * 
 * 
 * 
 * 
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
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
     * 递归要关注的有三点，1. 返回值  2. 调用单元做了什么  3. 终止条件
     */
    public ListNode swapPairs2(ListNode head) {
        // 当链表中没有节点了，或者链表中只有一个节点，没法交换
        if (head == null || head.next == null) {
            return head;
        }
        // 调用单元，设需要交换的两个节点为head和next
        // head连接后面交换完成的子链表
        // next连接head
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        // 返回值，交换完成的链表
        return next;
    }

    /**
     * 原来自己也想到这个解法了，但是图画个不够清楚
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while (temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }
}
// @lc code=end

