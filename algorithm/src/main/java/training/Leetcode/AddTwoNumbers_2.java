package training.Leetcode;

/**
 https://leetcode.com/problems/add-two-numbers/


 You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8

 * Created by vicviz on 2015/2/12.
 */
/**
 * Definition for singly-linked list.
 */

public class AddTwoNumbers_2 {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    private ListNode newTailNode(ListNode nowNode) {
        ListNode nextNode = new ListNode(0);
        nowNode.next = nextNode;
        return nextNode;
    }

    private int computeCarryNum(ListNode l3) {
        int carry = 0;
        if (l3.val > 9) {
            l3.val = l3.val % 10;
            carry = 1;
        } else {
            carry = 0;
        }
        return carry;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode l3 = null;
        ListNode resultHead = null;
        while (l1 != null && l2 != null) {
            if (l3 == null) {
                l3 = new ListNode(0);
                resultHead = l3;
            } else {
                l3 = newTailNode(l3);
            }
            l3.val = l1.val + l2.val + carry;
            carry = computeCarryNum(l3);
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 != null) {
            while (l1 != null) {
                ListNode nextNode = new ListNode(0);
                l3.next = nextNode;
                l3 = nextNode;
                l3.val = l1.val + carry;
                carry = computeCarryNum(l3);
                l1 = l1.next;
            }
        } else if (l2 != null) {
            while (l2 != null) {
                ListNode nextNode = new ListNode(0);
                l3.next = nextNode;
                l3 = nextNode;
                l3.val = l2.val + carry;
                carry = computeCarryNum(l3);
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            l3 = newTailNode(l3);
            l3.val = carry;
        }
        if (l3.val == 10) {
            l3.val = 0;
            ListNode nextNode = new ListNode(1);
            l3.next = nextNode;
        }
        return resultHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(8);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);

        ListNode l2 = new ListNode(2);
        AddTwoNumbers_2 solution = new AddTwoNumbers_2();
        ListNode result = solution.addTwoNumbers(l1, l2);

        ListNode l3 = new ListNode(5);
        ListNode l4 = new ListNode(5);

        ListNode result2 = solution.addTwoNumbers(l3, l4);

        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
        System.out.println();
        while (result2 != null) {
            System.out.print(result2.val + "->");
            result2 = result2.next;
        }
    }
}
