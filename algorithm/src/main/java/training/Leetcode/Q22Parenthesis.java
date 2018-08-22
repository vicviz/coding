package training.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class Q22Parenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> s = new ArrayList<>();
        char[] temp = new char[2 * n];
        gen(s, n, n, temp, 0);
        return s;
    }

    public void gen(List<String> result, int left, int right, char[] s, int index) {
        if (left == 0 && right == 0) {
            result.add(String.valueOf(s));
            return;
        }

        if (left > 0) {
            s[index] = '(';
            gen(result, left - 1, right, s, index + 1);
        }
        if (right > 0 && right > left) {
            s[index] = ')';
            gen(result, left, right - 1, s, index + 1);
        }
    }

    public static void main(String[] args) {
        Q22Parenthesis q22Parenthesis = new Q22Parenthesis();
        List<String> ss = q22Parenthesis.generateParenthesis(2);
        for (String s: ss) {
            System.out.println(s);
        }
    }
}
