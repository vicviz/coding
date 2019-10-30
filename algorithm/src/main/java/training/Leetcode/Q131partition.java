package training.Leetcode;

import java.util.*;

/**
 * 131. 分割回文串
 *
 *
 *
 *
 * 题目描述
 * 评论 (79)
 * 题解
 * 提交记录
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class Q131partition {

    private boolean isPalindrome(String s, int i, int j, Map<String, Boolean> palindromeRecord) {
        String key = concat(i, j);
        if (palindromeRecord.containsKey(key)) {
            return palindromeRecord.get(key);
        }
        if (i == j) {
            palindromeRecord.put(key, true);
            return true;
        }
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                palindromeRecord.put(key, false);
                return false;
            }
        }
        palindromeRecord.put(key, true);
        return true;
    }

    private String concat(int i, int j) {
        return i + "_" + j;
    }

    List<List<String>> partition(String s, int i, int j,
                                 Map<String, Boolean> palindromeRecord ,
                                 Map<String, List<List<String>>> records) {
        List<List<String>> result = new ArrayList<>();
        for (int index = j; index >=0; index --) {
            if (isPalindrome(s, index, j, palindromeRecord)) {
                String surfix = s.substring(index, j + 1);
                List<List<String>> prePart = partition(s, i, index - 1, palindromeRecord, records);
                if (prePart.size() != 0) {
                    for (List<String> list: prePart) {
                        list.add(surfix);
                    }
                    result.addAll(prePart);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(surfix);
                    result.add(list);
                }
            }
        }
        return result;
    }

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }
        Map<String, Boolean> palindromeRecord = new HashMap<>();
        Map<String, List<List<String>>> records = new HashMap<>();
        List<List<String>> result = partition(s, 0, s.length() - 1, palindromeRecord, records);
        return result;
    }

    public static void main(String[] args) {
        Q131partition test = new Q131partition();
        System.out.println(test.partition("abbac"));
    }
}
