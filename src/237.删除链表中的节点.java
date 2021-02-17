/*
 * @lc app=leetcode.cn id=237 lang=java
 *
 * [237] 删除链表中的节点
 *
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/description/
 *
 * algorithms
 * Easy (83.91%)
 * Likes:    840
 * Dislikes: 0
 * Total Accepted:    156.1K
 * Total Submissions: 186.1K
 * Testcase Example:  '[4,5,1,9]\n5'
 *
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
 * 
 * 
 * 
 * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
 * 
 * 
 * 
 * 
 * 
 * 示例 1：
 * 
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 
 * 
 * 示例 2：
 * 
 * 输入：head = [4,5,1,9], node = 1
 * 输出：[4,5,9]
 * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 * 
 * 
 * 
 * 
 * 提示：
 * 
 * 
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果。
 * 
 * 
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 对于A->B->C->D，我们知道指向B的指针，但是由于没有给出head节点我们无法找到A节点（单向链表的局限性），
     * 所以我们要删除B，只需要让B的值更新为C的值，同时让B->next指向D即node->next=node->next->next
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
// @lc code=end

