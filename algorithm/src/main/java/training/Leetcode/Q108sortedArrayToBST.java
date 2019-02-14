package training.Leetcode;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *108. 将有序数组转换为二叉搜索树
 *
 *
 *
 *
 * 题目描述
 * 评论 (46)
 * 官方题解
 * 提交记录
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class Q108sortedArrayToBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int start, int end) {
        int index = end - (end - start) / 2;
        TreeNode node = new TreeNode(nums[index]);
        if (index - start > 0) {
            node.left = build(nums, start, index - 1);
        }
        if (end - index > 0) {
            node.right = build(nums, index + 1, end);
        }
        return node;
    }


    public static void main(String[] args) {
        Q108sortedArrayToBST test = new Q108sortedArrayToBST();
        int[] m = new int[]{-10,-3,0,5,9};
        TreeNode node = test.sortedArrayToBST(m);
        System.out.print(node.val);
    }
}