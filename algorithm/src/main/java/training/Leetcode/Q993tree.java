package training.Leetcode;

/**
 *116. 填充每个节点的下一个右侧节点指针
 *
 *
 *
 *
 * 题目描述
 * 评论 (18)
 * 官方题解
 * 提交记录
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class Q993tree {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        if (root.val == x || root.val == y) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return false;
        }
        TreeNode recordx = new TreeNode(-1);
        TreeNode recordy = new TreeNode(-1);
        int depthx = findx(root, x, null, recordx, 0);
        if (depthx == -1) {
            return false;
        }
        int depthy = findx(root, y, null, recordy, 0);
        if (depthy == -1) {
            return false;
        }

        if (depthx != depthy || recordx.val == recordy.val) {
            return false;
        }
        return true;
    }

    private int findx(TreeNode root, int x, TreeNode father, TreeNode fatherRecord, int depth) {
        if (root == null) {
            return -1;
        }
        if (x == root.val) {
            fatherRecord.val = father.val;
            return depth;
        }
        if (root.left != null) {
            int depthNow = findx(root.left, x, root, fatherRecord, depth + 1);
            if (depthNow != -1) {
                return depthNow;
            }
        }
        if (root.right != null) {
            int depthNow = findx(root.right, x, root, fatherRecord, depth + 1);
            if (depthNow != -1) {
                return depthNow;
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        Q993tree test = new Q993tree();

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
//        TreeNode n5 = new TreeNode(5);

        n1.left = n2;
        n1.right = n3;

        n2.left = n4;
        System.out.println(test.isCousins(n1, 2, 3));
    }
}