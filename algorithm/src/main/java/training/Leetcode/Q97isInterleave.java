package training.Leetcode;

/**
 *97. 交错字符串
 *
 *
 *
 *
 * 题目描述
 * 评论 (7)
 * 官方题解
 * 提交记录
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class Q97isInterleave {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null) {
            return s2.equals(s3);
        }
        if (s2 == null) {
            return s1.equals(s3);
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return isInter(s1.toCharArray(), s1.length() - 1,
                s2.toCharArray(), s2.length() - 1, s3.toCharArray(), s3.length() - 1);
    }

    private boolean isInter(char[] s1, int l1, char[] s2, int l2, char[] s3, int l3) {
        int remain = 0;
        if (l1 == -1 || l2 == -1) {
            if (l1 == -1 && l2 != -1) {
                remain = l2;
                for (int i = remain; i >= 0; i--) {
                    if (s2[i] != s3[l3--]) {
                        return false;
                    }
                }
            } else if (l1 != -1 && l2 == -1) {
                remain = l1;
                for (int i = remain; i >= 0; i--) {
                    if (s1[i] != s3[l3--]) {
                        return false;
                    }
                }
            }
            return l3 == -1;
        } else {
            if (s1[l1] == s3[l3]) {
                boolean isMatch = isInter(s1, l1 - 1, s2, l2, s3, l3 - 1);
                if (isMatch) {
                    return isMatch;
                }
            }
            if (s2[l2] == s3[l3]) {
                boolean isMatch = isInter(s1, l1, s2, l2 - 1, s3, l3 - 1);
                if (isMatch) {
                    return isMatch;
                }
            }
            return false;
        }
    }


    public static void main(String[] args) {
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
//        String s1 = "abc", s2 = "def", s3 = "abcdef";
        Q97isInterleave test = new Q97isInterleave();
        System.out.println(test.isInterleave(s1, s2, s3));
    }
}