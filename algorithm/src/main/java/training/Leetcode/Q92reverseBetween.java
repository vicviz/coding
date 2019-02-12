package training.Leetcode;

/**
 * 92. 反转链表 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (40)
 * 官方题解
 * 提交记录
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class Q92reverseBetween {
      public static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

    public ListNode reverseBetween(ListNode head, int m, int n) {
          if (head == null || head.next == null || m == n) {
            return head;
          }
          ListNode now = head;
          ListNode backTail = null;
          for (int i = 1; i < m; i++) {
              if (m > 1 && i == m - 1) {
                  backTail = now;
              }
              now = now.next;
          }
          ListNode reverseHead = now;
          now = head;
          ListNode end = null;
          for (int i = 1; i <= n; i++) {
              now = now.next;
              if (i == n -1) {
                  end = now;
              }
          }
          ListNode reverseTail = now;
          end.next = null;

          ListNode afterReverse = reverse(reverseHead);
          now = afterReverse;
          while (now.next != null) {
              now = now.next;
          }
          now.next = reverseTail;
          if (m == 1) {
              return afterReverse;
          } else {
              backTail.next = afterReverse;
              return head;
          }
    }

    private ListNode reverse(ListNode head) {
          ListNode back = head;
          ListNode now = back.next;
          ListNode front = now.next;
          back.next = null;
          while (front != null) {
              now.next = back;
              back = now;
              now = front;
              front = front.next;
          }
          now.next = back;
          return now;
    }

    public static void main(String[] args) {
        Q92reverseBetween test = new Q92reverseBetween();
        ListNode head = new ListNode(1);
        ListNode now = head;

        now.next = new ListNode(2);
        now = now.next;

        now.next = new ListNode(3);
        now = now.next;
        now.next = new ListNode(4);
        now = now.next;
        now.next = new ListNode(5);
        now = head;
        while (now != null) {
            System.out.print(now.val + "->");
            now = now.next;
        }
        System.out.println();
        now = test.reverseBetween(head, 1, 5);
        while (now != null) {
            System.out.print(now.val + "->");
            now = now.next;
        }
    }
}