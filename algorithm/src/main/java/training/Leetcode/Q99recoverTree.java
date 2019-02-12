package training.Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *99. 恢复二叉搜索树
 *
 *
 *
 *
 * 题目描述
 * 评论 (7)
 * 官方题解
 * 提交记录
 * 二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 *
 * 输入: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 *
 * 输入: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * 进阶:
 *
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 */
public class Q99recoverTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public void recoverTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        innerTrav(root, list);
        List<Integer> errorNums = findInverNums(list);
        int error1 = 0, error2 = 0;
        if (errorNums.size() == 2) {
            error1 = errorNums.get(0);
            error2 = errorNums.get(1);
        } else if (errorNums.size() == 4) {
            error1 = errorNums.get(0);
            error2 = errorNums.get(3);
        }

        List<TreeNode> erroNodes = new ArrayList<>();
        findError(error1, error2, root, erroNodes);
        if (erroNodes.size() == 2) {
            TreeNode node1 = erroNodes.get(0);
            if (node1.val == error1) {
                node1.val = error2;
                erroNodes.get(1).val = error1;
            } else {
                node1.val = error1;
                erroNodes.get(1).val = error2;
            }
        }
    }

    private void findError(int error1, int error2, TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        } else {
            if (root.val == error1 || root.val == error2) {
                list.add(root);
            }
            if (list.size() == 2) {
                return;
            }
            findError(error1, error2, root.left, list);
            findError(error1, error2, root.right, list);
        }
    }



    private void innerTrav(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        innerTrav(root.left, list);
        list.add(root.val);
        innerTrav(root.right, list);
    }

    private List<Integer> findInverNums(List<Integer> list) {
        List<Integer> result = new ArrayList<>(4);
        for (int i = 0 ;i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                result.add(list.get(i));
                result.add(list.get(i + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Q99recoverTree test = new Q99recoverTree();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(3);
        TreeNode n1 = new TreeNode(2);
        root.left = left;
        root.right = null;
        left.right = n1;

        test.recoverTree(root);
        System.out.print(root.val);
    }
}