/**
 * @(#)Solution.java
 */
package training.Leetcode;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 *              which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 * 2016/03/04
 * @author zhaowei
 */
public class longestSubstring_3 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        int[] replicateMap = new int[256];
        int end = 0;
        int start = 0;
        int maxLength = 0;
        int nowMax = 0;
        while (start < s.length() && end < s.length()) {
            char nowChar = s.charAt(end);
            if (replicateMap[nowChar] == 0) {
                replicateMap[nowChar] = 1;
            } else {
                while (start < s.length() && s.charAt(start) != nowChar) {
                    replicateMap[s.charAt(start)] = 0;
                    start++;
                }
                if (start == s.length()) {
                    break;
                }
                replicateMap[s.charAt(start)] = 0;
                start++;
                nowMax = end - start;
                continue;
            }
            end ++;
            nowMax ++;
            if (nowMax > maxLength) {
                maxLength = nowMax;
            }
        }
        return  maxLength;
    }

    public static void main(String[] args) {
        String s = "";
        longestSubstring_3 solution = new longestSubstring_3();
        System.out.println(solution.lengthOfLongestSubstring(s));
    }
}
