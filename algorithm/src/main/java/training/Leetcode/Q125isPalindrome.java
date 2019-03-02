package training.Leetcode;

/**
 *124. 二叉树中的最大路径和
 *
 *
 *
 *
 * 题目描述
 * 评论 (30)
 * 官方题解
 * 提交记录
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 */
public class Q125isPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] ss = s.toCharArray();
        int i = 0;
        int j = ss.length - 1;
        while (i<=j) {
            char c1 = ss[i];
            if (!isValidChar(c1)) {
                i++;
                continue;
            }
            char c2 = ss[j];
            if (!isValidChar(c2)) {
                j--;
                continue;
            }
            i++;
            j--;
            if (big2Small(c1) != big2Small(c2)) {
                return false;
            }
        }
        return true;
    }
    private boolean isValidChar(char c) {
        if (
                (c >= 'A' && c <= 'Z')
                        || (c >= 'a' && c <= 'z')
                        || (c >= '0' && c <= '9')
                ) {
            return true;
        }
        return false;
    }

    private char big2Small(char c) {
        if (c >= 'A' && c <= 'Z') {
            c = (char)(c - 'A' + 'a');
        }
        return c;
    }
}