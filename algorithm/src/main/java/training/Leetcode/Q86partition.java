package training.Leetcode;

import java.util.Stack;

/**
 * 86. Partition List
 *
 *
 *
 *
 * 题目描述
 * 评论 (31)
 * 官方题解
 * 提交记录
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class Q86partition {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = null;
        ListNode large = null;
        ListNode largeHead = null;
        ListNode smallHead = null;

        ListNode now = head;
        while (now != null) {
            if (now.val >= x) {
                if (large == null) {
                    large = now;
                    largeHead = now;
                } else {
                    large.next = now;
                    large = large.next;
                }
            } else {
                if (small == null) {
                    smallHead = now;
                    small = now;
                } else {
                    small.next = now;
                    small = small.next;
                }
            }
            now = now.next;
        }
        if (large != null) {
            large.next = null;
        }
        if (small != null) {
            small.next = largeHead;
            return smallHead;
        } else {
            return largeHead;
        }
    }


    public static void main(String[] args) {
        Q86partition test = new Q86partition();
        ListNode head = new ListNode(1);
        ListNode now = head;

        now.next = new ListNode(4);
        now = now.next;

        now.next = new ListNode(3);
        now = now.next;
        now.next = new ListNode(2);
        now = now.next;
        now.next = new ListNode(5);
        now = now.next;
        now.next = new ListNode(2);

        now = head;
        while (now != null) {
            System.out.print(now.val + "->");
            now = now.next;
        }
        System.out.println();
        now = test.partition(head, 3);
        while (now != null) {
            System.out.print(now.val + "->");
            now = now.next;
        }
    }
}