package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *103. 二叉树的锯齿形层次遍历
 *
 *
 *
 *
 * 题目描述
 * 评论 (33)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class Q103zigzagLevelOrder {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<TreeNode> queue1 = new LinkedList<>();
        LinkedList<TreeNode> queue2 = new LinkedList<>();
        queue1.add(root);
        List<List<Integer>> result = new ArrayList<>();
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            List<Integer> newList = new ArrayList<>();
            if (!queue1.isEmpty()) {
                while (!queue1.isEmpty()) {
                    TreeNode node = queue1.pollFirst();
                    newList.add(node.val);
                    if (node.left != null) {
                        queue2.add(node.left);
                    }
                    if (node.right != null) {
                        queue2.add(node.right);
                    }
                }
                result.add(newList);
            } else if (!queue2.isEmpty()) {
                while (!queue2.isEmpty()) {
                    TreeNode node = queue2.pollFirst();
                    newList.add(node.val);
                    if (node.left != null) {
                        queue1.add(node.left);
                    }
                    if (node.right != null) {
                        queue1.add(node.right);
                    }
                }
                result.add(reverse(newList));
            }
        }
        return result;
    }

    private List<Integer> reverse(List<Integer> l) {
        List<Integer> result = new ArrayList<>(l.size());
        for (int i = l.size() - 1; i >=0; i--) {
            result.add(l.get(i));
        }
        return result;
    }


    public static void main(String[] args) {
        Q103zigzagLevelOrder test = new Q103zigzagLevelOrder();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(4);
        TreeNode n1 = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(5);
        right.left = n1;
        List<List<Integer>> result = test.zigzagLevelOrder(root);
        for (List<Integer> list: result) {
            System.out.println(list);
        }
    }
}