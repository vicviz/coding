/**
 *
 * StringToInteger_8.java, 2016—09-14.
 *
 */
package training.Leetcode;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 * Implement atoi to convert a string to an integer.

 Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

 Requirements for atoi:
 The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

 The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

 If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

 If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

 中文写一下规则吧：
 1. 数字和+-号开始前，全是空格
 2. 数字后面可以有别的字符，但是就此截断例如123a1，输出是123而不是1231
 *
 * @author zhaowei
 */
public class StringToInteger_8 {

    private boolean isNum(char c) {
        if (c <= '9' && c >= '0') {
            return true;
        }
        return false;
    }
    private boolean isSign(char c) {
        if (c == '+' || c == '-') {
            return true;
        }
        return false;
    }

    private boolean isOutOfRang(long result, boolean isNav) {
        if (isNav) {
            if (result * -1 < Integer.MIN_VALUE) {
                return true;
            }
        } else if (result > Integer.MAX_VALUE) {
            return true;
        }
        return false;
    }

    private int returnResult(long result, boolean isNav) {
        if (isNav) {
            return (int) (result * -1);
        } else {
            return (int) result;
        }
    }

    public int myAtoi(String str) {
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        boolean isNav = false;
        long result = 0;
        int isSeqStart = -1;// -1: not start; 0:startFirstChar 1:index after firstChar
        for (int i = 0 ;i < str.length();i++) {
            char c = str.charAt(i);
            if (isSeqStart == -1) {
                if ( !(isSign(c) || isNum(c)) ) {
                    return 0;
                }
            } else if (isSeqStart == 0) {
                if (!isNum(c)) {
                    return 0;
                }
            } else {
                if (!isNum(c)) {
                    if (isOutOfRang(result, isNav)) {
                        if (isNav) {
                            return Integer.MIN_VALUE;
                        } else {
                            return Integer.MAX_VALUE;
                        }
                    } else {
                        return returnResult(result, isNav);
                    }
                }
            }
            if ( c == '-') {
                isNav = true;
                isSeqStart = 0;
            } else if (c == '+') {
                isNav = false;
                isSeqStart = 0;
            } else if (isNum(c)) {
                if (isSeqStart == 0 || isSeqStart == -1) {
                    isSeqStart = 1;
                }
                result = result * 10 + c - '0';
                if (isOutOfRang(result, isNav)) {
                     if (isNav) {
                         return Integer.MIN_VALUE;
                     } else {
                         return Integer.MAX_VALUE;
                     }
                }
            }
        }
        return returnResult(result, isNav);
    }

    public static void main(String[] args) {
        System.out.print((new StringToInteger_8()).myAtoi("  1"));
    }
}
