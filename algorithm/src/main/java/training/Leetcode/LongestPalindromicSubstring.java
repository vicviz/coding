/**
 * @(#)Solution.java
 */
package training.Leetcode;

/**
 * Longest Palindromic Substring
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 * 2016/03/16
 * @author zhaowei
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        int length = s.length();
        boolean[][] maxMatrix = new boolean[1000][1000];
        int max = 1;
        int start = 0;
        for (int i = 0 ; i < s.length();i++) {
            maxMatrix[i][i] = true;
        }
        for (int i = 0 ; i < length - 1;i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                maxMatrix[i][i+1] = true;
                max = 2;
                start = i;
            } else {
                maxMatrix[i][i+1] = false;
            }
        }

        for (int len = 3; len <= length;len++) {
            for (int i = 0 ; i <= (length - len);i++) {
                if (maxMatrix[i + 1][i + len - 2] == true) {
                    if (s.charAt(i) == s.charAt(i + len - 1)) {
                        maxMatrix[i][i+len - 1] = true;
                        if (len > max) {
                            max = len;
                            start = i;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + max );
    }
    public static void main(String[] args) {
       LongestPalindromicSubstring solution = new LongestPalindromicSubstring();
       System.out.println(solution.longestPalindrome("12321"));
    }
}
