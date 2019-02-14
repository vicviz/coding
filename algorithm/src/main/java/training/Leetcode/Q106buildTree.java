package training.Leetcode;

/**
 *106. 从中序与后序遍历序列构造二叉树
 *
 *
 *
 *
 * 题目描述
 * 评论 (21)
 * 官方题解
 * 提交记录
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class Q106buildTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return build(postorder, inorder, 0, postorder.length - 1, 0,
                inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int [] inorder,
                           int postleft, int postright, int inleft, int inright) {
        int index = findIndex(postorder[postright], inorder, inleft, inright);
        if (index != -1) {
            TreeNode treeNode = new TreeNode(inorder[index]);
            int numLeftTree = index - inleft;
            if (numLeftTree > 0) {
                TreeNode leftRoot = build(postorder, inorder,
                        postleft, postleft + numLeftTree - 1, inleft, index - 1);
                treeNode.left = leftRoot;
            }
            //right tree
            int numRightTree = inright - index;
            if (numRightTree > 0) {
                TreeNode rigthRoot = build(postorder, inorder,
                        postleft + numLeftTree , postright - 1, index + 1, inright);
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
        Q106buildTree test = new Q106buildTree();
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode treeNode = test.buildTree(inorder, postorder);
        System.out.println(treeNode.val);
    }
}