/**
 *
 * ValidParentheses.java, 2016—10-05.
 *
 */
package training.Leetcode;

import java.util.Stack;

/**
 *
 https://leetcode.com/problems/valid-parentheses/
 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * @author zhaowei
 */
public class ValidParentheses_20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c: s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if ( (c == ')' && top == '(') || (c == '}' && top == '{') || (c == ']' && top == '[')) {
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        ValidParentheses_20 solution = new ValidParentheses_20();
        System.out.println(solution.isValid("{{{}"));
        System.out.println(solution.isValid("{[()]}"));
    }
}
