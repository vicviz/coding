package training.Leetcode;

import java.util.List;

/**
 * 82. 删除排序链表中的重复元素 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (35)
 * 官方题解
 * 提交记录
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class Q83deleteDuplicates {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode now = pre.next;
        while (now != null) {
            if (pre.val == now.val) {
                pre.next = now.next;
                now = pre.next;
            } else {
                pre = now;
                now = now.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Q83deleteDuplicates test = new Q83deleteDuplicates();
        ListNode head = new ListNode(1);
        ListNode now = head;
        now.next = new ListNode(1);
        now = now.next;
        now.next = new ListNode(2);
        now = now.next;
        now.next = new ListNode(3);
        now = now.next;
        now.next = new ListNode(3);
        now = now.next;
        now.next = new ListNode(4);
        now = now.next;
        now.next = new ListNode(4);
        now = now.next;
        now.next = new ListNode(5);
        now = now.next;
        now = test.deleteDuplicates(head);
        while (now != null) {
            System.out.print(now.val + "->");
            now = now.next;
        }
    }
}
