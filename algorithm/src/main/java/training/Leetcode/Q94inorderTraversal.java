package training.Leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 *
 *
 *
 *
 * 题目描述
 * 评论 (70)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Q94inorderTraversal {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        traversal(root, result);
        return result;
    }

    private void traversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            traversal(node.left, result);
        }
        result.add(node.val);
        if (node.right != null) {
            traversal(node.right, result);
        }
    }

    public static void main(String[] args) {
        Q94inorderTraversal test = new Q94inorderTraversal();
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode left = new TreeNode(3);
        root.right = right;
        right.left = left;

        List<Integer> result = test.inorderTraversal(root);
        System.out.println(result);
    }
}