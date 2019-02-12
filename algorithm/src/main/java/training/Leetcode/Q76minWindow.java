package training.Leetcode;

/**
 *76. 最小覆盖子串
 *
 *
 *
 *
 * 题目描述
 * 评论 (13)
 * 官方题解
 * 提交记录
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class Q76minWindow {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int start = 0;
        int end = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        int i = 0;
        boolean find = false;
        while (l < s.length()) {
            if (!isContain(s, t, l, r) && r + 1 < s.length()) {
                r++;
            } else {
                if ((end - start) > r-l && isContain(s, t, l, r)) {
                    if (r - l < end - start) {
                        start = l;
                        end = r;
                    }
                    find = true;
                }
                l++;
            }
        }
        if (find) {
            return s.substring(start, end + 1);
        } else {
            return "";
        }
    }

    private boolean isContain(String s, String t, int l, int r) {
        int[] temp = new int[128];
        String sub = s.substring(l, r + 1);
        for (int i = 0; i < sub.length(); i++) {
            char c = sub.charAt(i);
            temp[c]++;
        }
        for (int i = 0 ; i < t.length(); i++) {
            char c = t.charAt(i);
            temp[c]--;
            if (temp[c] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Q76minWindow test = new Q76minWindow();
        System.out.println(test.minWindow("cabwefgewcwaefgcf", "cae"));
    }
}
