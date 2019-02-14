package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *109. 有序链表转换二叉搜索树
 *
 *
 *
 *
 * 题目描述
 * 评论 (24)
 * 官方题解
 * 提交记录
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class Q109sortedListToBST {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        return build(list, 0, list.size() - 1);
    }

    private TreeNode build(List<ListNode> nums, int start, int end) {
        int index = end - (end - start) / 2;
        TreeNode node = new TreeNode(nums.get(index).val);
        if (index - start > 0) {
            node.left = build(nums, start, index - 1);
        }
        if (end - index > 0) {
            node.right = build(nums, index + 1, end);
        }
        return node;
    }

    public static void main(String[] args) {
        Q109sortedListToBST test = new Q109sortedListToBST();
    }
}