/**
 *
 * RegularExpressionMatching_10.java, 2016—09-19.
 *
 * Copyright 2016 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package training.Leetcode;

/**
 * https://leetcode.com/problems/regular-expression-matching/


 '.' Matches any single character.
 '*' Ma   tches zero or more of the preceding element.

    The matching should cover the entire input string (not partial).

    The function prototype should be:
    bool isMatch(const char *s, const char *p)

    Some examples:
    isMatch("aa","a") → false
    isMatch("aa","aa") → true
    isMatch("aaa","aa") → false
    isMatch("aa", "a*") → true
    isMatch("aa", ".*") → true
    isMatch("ab", ".*") → true
    isMatch("aab", "c*a*b") → true

            * @author zhaowei
    */
    public class RegularExpressionMatching_10 {

        public boolean isMatch(char[] s, char[] p, int sLen, int pLen, int sStart, int pStart) {
        if (isMatchAnything(p, pLen, pStart)) {
            return true;
        }

        if (sStart == sLen) {
            if (pStart == pLen) {
                return true;
            } else {
                if (couldBeEmpty(p, pLen, pStart)) {
                    return true;
                }
                return false;
            }
        } else if (pStart == pLen) {
            return false;
        }


        char sChar = s[sStart];
        char pChar = p[pStart];
        if (pChar == '.') {
            //if .*
            if ( (pStart + 1) < pLen) {
                char pNextChar = p[pStart + 1];
                if (pNextChar == '*') {

                    if ( (pStart + 2) < pLen) {
                        char pNextNextChar = p[pStart + 2];
                        if (sChar == pNextNextChar) {
                            return isMatch(s, p, sLen, pLen, sStart + 1, pStart + 2);
                        }
                    }
                    return isMatch(s, p, sLen, pLen, sStart + 1, pStart);
                }
            }
            return isMatch(s, p, sLen, pLen, sStart + 1, pStart + 1);
        }
        if (sChar == pChar) {
            if ( (pStart + 1) < pLen) {
                char pNextChar = p[pStart + 1];
                if (pNextChar == '*') {
                    return isMatch(s, p, sLen, pLen, sStart + 1, pStart);
                }
            }
            return isMatch(s, p, sLen, pLen, sStart + 1, pStart + 1);
        } else {
            if ( (pStart + 1) < pLen) {
                char pNextChar = p[pStart + 1];
                if (pNextChar == '*') {
                    return isMatch(s, p, sLen, pLen, sStart, pStart + 2);
                }
            }
        }
        return false;
    }

    private static boolean couldBeEmpty(char[]p, int pLen, int pStart) {
        while (pStart + 1 < pLen - 1) {
            if (p[pStart + 1] != '*') {
                return false;
            }
            pStart += 2;
        }
        if (pStart + 1  == pLen -1) {
            if (p[pStart + 1] == '*') {
                return true;
            }
        }
        return false;
    }

    private static boolean isMatchAnything(char[]p, int pLen, int pStart) {
        if (pStart + 1 == pLen - 1) {
            if (p[pStart] == '.' && p[pStart + 1] == '*') {
                return true;
            }
        }
        return false;
    }

    public boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        int sLen = s.length();
        int pLen = p.length();
        if (sLen == 0 && pLen == 0) {
            return true;
        }
        return isMatch(ss, pp, sLen, pLen, 0, 0);
    }

    public static void main(String[] args) {
        RegularExpressionMatching_10 test = new RegularExpressionMatching_10();
        System.out.println(test.isMatch("aa", "a*"));
        System.out.println(test.isMatch("aa", "a*b*"));
        System.out.println(test.isMatch("aa", "c*a*b*"));
        System.out.println(test.isMatch("ab", ".*c"));//false
        System.out.println(test.isMatch("aa", "."));//false
    }
}
