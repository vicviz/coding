package training.Leetcode;

/**
 *111. 二叉树的最小深度
 *
 *
 *
 *
 * 题目描述
 * 评论 (74)
 * 官方题解
 * 提交记录
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 */
public class Q111minDepth {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode min = new TreeNode(Integer.MAX_VALUE);
        minDepth(root, min, 1);
        return min.val;
    }

    private void minDepth(TreeNode root, TreeNode min, int nowDepth) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (nowDepth < min.val) {
                min.val = nowDepth;
            }
        } else {
            if (root.left != null) {
                minDepth(root.left, min, nowDepth + 1);
            }
            if (root.right != null) {
                minDepth(root.right, min, nowDepth + 1);
            }
        }
    }


    public static void main(String[] args) {
        Q111minDepth test = new Q111minDepth();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);

        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        System.out.print(test.minDepth(n1));
    }
}