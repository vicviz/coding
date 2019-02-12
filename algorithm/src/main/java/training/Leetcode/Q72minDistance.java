package training.Leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *72. 编辑距离
 *
 *
 *
 *
 * 题目描述
 * 评论 (21)
 * 官方题解
 * 提交记录
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 *
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class Q72minDistance {
//    public int minDistance(String word1, String word2) {
//        if (word1 == null && word2 == null) {
//            return 0;
//        }
//        if (word1 == null || word1.length() == 0) {
//            return word2.length();
//        }
//        if (word2 == null || word2.length() == 0) {
//            return word1.length();
//        }
//        Map<String, Integer> record = new HashMap<>();
//        return editDistance(word2, word1, record);
//    }
//
//    private int editDistance(String word1, String word2, Map<String, Integer> records) {
//        String key = word1 + "_" + word2;
//        if (records.containsKey(key)) {
//            return records.get(key);
//        }
//        if (word1.length() == 0) {
//            return word2.length();
//        } else if (word2.length() == 0) {
//            return word1.length();
//        }
//
//        int min = Integer.MAX_VALUE;
//        for (int i = 0 ; i < word2.length(); i++) {
//            String newInstance = null;
//            if (i == 0) {
//                newInstance = word2.substring(1);
//            } else {
//                newInstance = word2.substring(0, i) + word2.substring(i + 1, word2.length());
//            }
//            int distance = editDistance(word1, newInstance, records) + 1;
//            if (min > distance) {
//                min = distance;
//            }
//        }
//        for (int i = 0 ; i < word1.length(); i++) {
//            String newInstance = null;
//            if (i == 0) {
//                newInstance = word1.substring(1);
//            } else {
//                newInstance = word1.substring(0, i) + word1.substring(i + 1, word1.length());
//            }
//            int distance = editDistance(word2, newInstance, records) + 1;
//            if (min > distance) {
//                min = distance;
//            }
//        }
//        if (word1.charAt(0) == word2.charAt(0)) {
//            int distance = editDistance(word1.substring(1), word2.substring(1), records);
//            if (min > distance) {
//                min = distance;
//            }
//        } else {
//            int distance = 1 + editDistance(word1.substring(1), word2.substring(1), records);
//            if (min > distance) {
//                min = distance;
//            }
//        }
//        records.put(key, min);
//        return min;
//    }


//    public int minDistance(String s1, String s2) {
//        if ( (s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0) ) {
//            return 0;
//        } else if (s1 == null || s1.length() == 0) {
//            return s2.length();
//        } else if (s2 == null || s2.length() == 0) {
//            return s1.length();
//        }
//        if (s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {
//            return minDistance(s1.substring(0, s1.length() - 1),
//                    s2.substring(0, s2.length() - 1));
//        } else {
//            int distance1 = minDistance(s1.substring(0, s1.length() - 1), s2) + 1;
//            int distance2 = minDistance(s1, s2.substring(0, s2.length() - 1)) + 1;
//            int distance3 = minDistance(s1.substring(0, s1.length() - 1), s2.substring(0, s2.length() - 1)) + 1;
//            if (distance1 > distance2) {
//                distance1 = distance2;
//            }
//            if (distance1 > distance3) {
//                distance1 = distance3;
//            }
//            return distance1;
//        }
//    }
    public int minDistance(String s1, String s2) {
        if ((s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0)) {
            return 0;
        } else if (s1 == null || s1.length() == 0) {
            return s2.length();
        } else if (s2 == null || s2.length() == 0) {
            return s1.length();
        }

        int len1 = s1.length();
        int len2 = s2.length();
        int[][] d = new int[len1 + 1][len2 + 1];
        int i, j;

        for (i = 0; i <= len1; i++) {
            d[i][0] = i;
        }
        for (j = 0; j <= len2; j++) {
            d[0][j] = j;
        }

        for (i = 1; i <= len1; i++) {
            for (j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + 1);
                }
            }
        }
        return d[len1][len2];
    }
    private int min(int a, int b, int c) {
        if (a > b) {
            a = b;
        }
        if (a > c) {
            a = c;
        }
        return a;
    }
    public static void main(String[] args) {
        Q72minDistance test = new Q72minDistance();
        System.out.println(test.minDistance("horse", "ros"));
    }
}
