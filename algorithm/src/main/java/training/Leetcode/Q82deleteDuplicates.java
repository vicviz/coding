package training.Leetcode;

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
public class Q82deleteDuplicates {
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return delete(head, false, 0);
    }

    public ListNode delete(ListNode head, boolean isInDeleting, int val) {
        if (head == null) {
            return head;
        }
        if (isInDeleting) {
            while (head != null && head.val == val) {
                head = head.next;
            }
            if (head == null || head.next == null) {
                return head;
            } else {
                return delete(head, false, 0);
            }
        } else {
            if (head != null && head.next != null && head.val == head.next.val) {
                return delete(head, true, head.val);
            }
            head.next = delete(head.next, false, 0);
            return head;
        }
    }

    public static void main(String[] args) {
        Q82deleteDuplicates test = new Q82deleteDuplicates();
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
