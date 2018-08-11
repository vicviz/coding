/**
 *
 * PalindromeNumber_9.java, 2016—09-15.
 *
 */
package training.Leetcode;

/**
 *
 * https://leetcode.com/problems/palindrome-number/
 * Determine whether an integer is a palindrome. Do this without extra space.
 * negative is not palindrome number
 * @author zhaowei
 */
public class PalindromeNumber_9 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        else {
            String s = String.valueOf(x);
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {

                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromeNumber_9 palindromeNumber_9 = new PalindromeNumber_9();
        System.out.println(palindromeNumber_9.isPalindrome(12321));
        System.out.println(palindromeNumber_9.isPalindrome(-1221));
        System.out.println(palindromeNumber_9.isPalindrome(-2147483648));
        System.out.println(palindromeNumber_9.isPalindrome(-2147447412));
    }
}
