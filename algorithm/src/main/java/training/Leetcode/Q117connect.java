package training.Leetcode;

/**
 *117. 填充每个节点的下一个右侧节点指针 II
 *
 *
 *
 *
 * 题目描述
 * 评论 (67)
 * 官方题解
 * 提交记录
 * 给定一个二叉树
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
public class Q117connect {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                Node rootNext = root.next;
                while (rootNext != null) {
                    if (rootNext.left != null) {
                        root.left.next = rootNext.left;
                        break;
                    } else if (rootNext.right != null) {
                        root.left.next = rootNext.right;
                        break;
                    } else {
                        rootNext = rootNext.next;
                    }
                }
            }
        }
        if (root.right != null) {
            Node rootNext = root.next;
            while (rootNext != null) {
                if (rootNext.left != null) {
                    root.right.next = rootNext.left;
                    break;
                } else if (rootNext.right != null) {
                    root.right.next = rootNext.right;
                    break;
                } else {
                    rootNext = rootNext.next;
                }
            }
        }
        connect(root.right);
        connect(root.left);
        return root;
    }

    public static void main(String[] args) {
        Q117connect test = new Q117connect();
    }
}