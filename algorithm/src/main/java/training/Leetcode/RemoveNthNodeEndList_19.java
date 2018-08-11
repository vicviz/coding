/**
 * RemoveNthNodeEndList_19.java, 2016—10-05.
 */
package training.Leetcode;

/**
 https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 Given a linked list, remove the nth node from the end of list and return its head.

 For example,

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:
 Given n will always be valid.

 需要注意的:删除的是当前这一个慢结点，需要记录一下前一个才方便删除
 * @author zhaowei
 */
public class RemoveNthNodeEndList_19 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fastP = head;
        ListNode slowP = head;
        ListNode slowPre = null;
        int i = 1;
        while (i < n) {
            fastP = fastP.next;
            i++;
        }
        while (fastP.next != null) {
            fastP = fastP.next;
            slowPre = slowP;
            slowP = slowP.next;
        }
        if (slowPre == null) {
            //remove head
            head = head.next;
        } else {
            slowPre.next = slowPre.next.next;
        }
        return head;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode now = head;
        for (int i = 2 ; i <= 2;i++) {
            ListNode node =new ListNode(i);
            now.next = node;
            now = node;
        }
        printList(head);
        System.out.println();
        RemoveNthNodeEndList_19 solution = new RemoveNthNodeEndList_19();
        printList(solution.removeNthFromEnd(head,2));
    }
}
