package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Q25reverseLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private List<ListNode> reverseNowPart(ListNode head, ListNode tail) {
        ListNode now = head;
        ListNode next = now.next;
        while (now != tail) {
            ListNode nextnext = next.next;
            next.next = now;
            now = next;
            next = nextnext;
        }

        List<ListNode> result = new ArrayList<>(2);
        result.add(tail);
        result.add(head);
        return result;
    }

    private ListNode reverse(ListNode headNode, int k) {
        if (headNode == null) {
            return headNode;
        }
        ListNode now = headNode;
        for (int i = 0 ; i < k - 1; i++) {
            now = now.next;
            if (now == null) {
                return headNode;
            }
        }
        ListNode suffix = reverse(now.next, k);
        List<ListNode> headAndTail = reverseNowPart(headNode, now);
        headAndTail.get(1).next = suffix;
        return headAndTail.get(0);
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1 || head == null) {
            return head;
        }
        return reverse(head, k);
    }

    private ListNode buildListNode() {
        ListNode head = new ListNode(1);
        ListNode now = head;
        for (int i = 2; i < 10; i++) {
            ListNode newNode = new ListNode(i);
            now.next = newNode;
            now = newNode;
        }
        return head;
    }

    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Q25reverseLinkedList q25 = new Q25reverseLinkedList();
        ListNode listNode = q25.buildListNode();
        printList(listNode);
        listNode = q25.reverseKGroup(listNode, 23);
        printList(listNode);
    }
}
