package training.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *113. 路径总和 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (36)
 * 官方题解
 * 提交记录
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class Q113pathSum {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> parent = new LinkedList<>();
        hasPathSum(root, sum, result, parent);
        return result;
    }

    public void hasPathSum(TreeNode root, int sum,
                              List<List<Integer>> result, LinkedList<Integer> parent) {
        if (root == null) {
            return;
        }
        parent.addLast(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            result.add(new ArrayList<>(parent));
            parent.removeLast();
            return;
        }
        if (root.left != null) {
            hasPathSum(root.left, sum - root.val, result, parent);
        }
        if (root.right != null) {
            hasPathSum(root.right, sum - root.val, result, parent);
        }
        parent.removeLast();
    }


    public static void main(String[] args) {
        Q113pathSum test = new Q113pathSum();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(27);

        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        System.out.print(test.pathSum(n1, 30));
    }
}