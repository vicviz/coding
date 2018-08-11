/**
 * SwapNodesinPairs_24.java, 2016—10-05.
 * <p>
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package training.Leetcode;

/**

 https://leetcode.com/problems/swap-nodes-in-pairs/
 Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 * @author zhaowei
 */
public class SwapNodesinPairs_24 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

private void swap(ListNode l1, ListNode l2) {
        ListNode temp = l1;
        l1.next = l2.next;
        l2.next = l1;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //swap top2 nodes
        ListNode first = head;
        ListNode newHead = first.next;
        first.next = newHead.next;
        newHead.next = first;

        ListNode p1 = first;
        ListNode p2 = first.next;

        while (p1 != null && p2 != null && p2.next != null) {
            p1.next = p2.next;
            p2.next = p2.next.next;
            p1.next.next = p2;
            p1 = p1.next.next;
            p2 = p1.next;
        }
        return newHead;
    }
}
