/**
 *
 * ReverseInteger_7.java, 2016—09-14.
 */
package training.Leetcode;

/**
 * https://leetcode.com/problems/reverse-integer/
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 *
 * notice:
 * 1. overflow(For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows)
 * 2. end with 0s
 * @author zhaowei
 */
public class ReverseInteger_7 {
    public int reverse(int x) {
        if (x == 0) {
            return x;
        }
        boolean isNeg = x < 0;
        if (isNeg) {
            x = x * -1;
        }
        long result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            x = x / 10;
            if (result > Integer.MAX_VALUE) {
                return 0;
            }
        }

        if (isNeg) {
            result = result * -1;
        }
        return (int)result;
    }

    public static void main(String[] args) {
        ReverseInteger_7 solution = new ReverseInteger_7();
        System.out.println(solution.reverse(-199980));
    }
}
