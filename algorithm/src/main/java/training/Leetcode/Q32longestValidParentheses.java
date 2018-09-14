package training.Leetcode;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**

 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

 示例 1:

 输入: "(()"
 输出: 2
 解释: 最长有效括号子串为 "()"
 示例 2:

 输入: ")()())"
 输出: 4
 解释: 最长有效括号子串为 "()()"
 *
 */
public class Q32longestValidParentheses {
    private Stack<Integer> stack = new Stack<>();
    public int longestValidParentheses(String s) {
        int max = 0;
        int begin = 0;
        for (int i = 0 ; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    int top = stack.pop();
                    if (stack.isEmpty()) {
                        max = Math.max(max, i - begin + 1);
                    } else {
                        top = stack.peek();
                        max = Math.max(max, i - top);
                    }
                } else {
                    begin = i + 1;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Q32longestValidParentheses test = new Q32longestValidParentheses();
        System.out.println(test.longestValidParentheses("()()"));
    }
}
