/**
 * MergeTwoSortedLists_21.java, 2016—10-05.
 */
package training.Leetcode;

/**
 *
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * @author zhaowei
 */
public class MergeTwoSortedLists_21 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode now = null;
        ListNode head = null;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                if (now == null) {
                    now = p1;
                    head = now;
                } else {
                    now.next = p1;
                    now = now.next;
                }
                p1 = p1.next;
            } else {
                if (now == null) {
                    now = p2;
                    head = now;
                } else {
                    now.next = p2;
                    now = now.next;
                }
                p2 = p2.next;
            }
        }
        if (p1 == null && p2 != null) {
            now.next = p2;
        } else if (p2 == null && p1 != null) {
            now.next = p1;
        }
        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.val = 5;

        ListNode head2 = new ListNode(1);
        head2.val = 1;

        ListNode node1 = new ListNode(1);
        node1.val = 2;
        ListNode node2 = new ListNode(1);
        node2.val = 4;

        head2.next = node1;
        node1.next = node2;
        printList(head1);
        printList(head2);

        MergeTwoSortedLists_21 solution = new MergeTwoSortedLists_21();
        printList(solution.mergeTwoLists(head1, head2));
    }
}
