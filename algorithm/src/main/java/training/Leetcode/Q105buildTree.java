package training.Leetcode;

import apple.laf.JRSUIUtils;

import javax.sound.midi.SysexMessage;

/**
 *105. 从前序与中序遍历序列构造二叉树
 *
 *
 *
 *
 * 题目描述
 * 评论 (33)
 * 官方题解
 * 提交记录
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Q105buildTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return build(preorder, inorder, 0, preorder.length - 1, 0,
                inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int [] inorder,
                           int preleft, int preright, int inleft, int inright) {
        int index = findIndex(preorder[preleft], inorder, inleft, inright);
        if (index != -1) {
            TreeNode treeNode = new TreeNode(inorder[index]);
            //left tree
            int numLeftTree = index - inleft;
            if (numLeftTree > 0) {
                TreeNode leftRoot = build(preorder, inorder,
                        preleft + 1, preleft, inleft, index - 1);
                treeNode.left = leftRoot;
            }
            //right tree
            int numRightTree = inright - index;
            if (numRightTree > 0) {
                TreeNode rigthRoot = build(preorder, inorder,
                         preleft + numLeftTree + 1, preright, index + 1, inright);
                treeNode.right = rigthRoot;
            }
            return treeNode;
        }
        return null;
    }

    private int findIndex(int num, int[] inorder, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == num) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Q105buildTree test = new Q105buildTree();
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode treeNode = test.buildTree(preorder, inorder);
        System.out.println(treeNode.val);
    }
}