package training.Leetcode;

/**
 * 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 */
public class Q61rotateRight {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
  }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 1;
        ListNode node = head;
        ListNode orgHead = head;
        while (node != null) {
            len++;
            node = node.next;
            if (node.next == null) {
                node.next = orgHead;
                break;
            }
        }
        k = k % len;
        int newHeadStep = len - k;
        int i = 0;
        ListNode newTail = orgHead;
        while (i < newHeadStep - 1) {
            newTail = newTail.next;
            i++;
        }
        head = newTail.next;
        newTail.next = null;
        return head;
    }

    public static void main(String[] args) {
        Q61rotateRight test = new Q61rotateRight();
        ListNode head = new ListNode(1);
        ListNode now = head;
        for (int i = 0; i < 4; i++) {
            ListNode node = new ListNode(i + 2);
            now.next = node;
            now = node;
        }
        head = test.rotateRight(head, 6);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
