package training.Leetcode;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 */
public class Q23CombinKList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                priorityQueue.add(lists[i]);
            }
        }
        ListNode head = null;
        ListNode pre = null;
        while (!priorityQueue.isEmpty()) {
            ListNode now = priorityQueue.poll();
            if (head == null) {
                head = now;
                pre = head;
            } else {
                pre.next = now;
                pre = now;
            }
            if (now.next != null) {
                priorityQueue.add(now.next);
            }
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
        ListNode listNode1 = new ListNode(0);
        ListNode now = listNode1;
        for (int i = 1 ; i < 5;i++) {
            ListNode newNode = new ListNode(i * 2);
            now.next = newNode;
            now = newNode;
        }
        ListNode listNode2 = new ListNode(0);
        ListNode now2 = listNode2;
        for (int i = 1 ; i < 5;i++) {
            ListNode newNode = new ListNode(i * 2 + 1);
            now2.next = newNode;
            now2 = newNode;
        }
        ListNode listNode3 = new ListNode(-1);
        ListNode now3 = listNode3;
        for (int i = 1 ; i < 5;i++) {
            ListNode newNode = new ListNode(2);
            now3.next = newNode;
            now3 = newNode;
        }
        printList(listNode1);
        printList(listNode2);
        printList(listNode3);
        ListNode[] list = new ListNode[5];
        list[0] = listNode1;
        list[1] = listNode2;
        list[2] = listNode3;
        list[3] = null;
        list[4] = null;
        Q23CombinKList q23CombinKList = new Q23CombinKList();
        ListNode combin = q23CombinKList.mergeKLists(list);
        printList(combin);
    }
}
